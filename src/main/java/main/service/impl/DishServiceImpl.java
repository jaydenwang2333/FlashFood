package main.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import main.controller.CommonController;
import main.dto.DishDto;
import main.entity.Category;
import main.entity.Dish;
import main.entity.DishFlavor;
import main.mapper.DishMapper;
import main.service.CategoryService;
import main.service.DishFlavorService;
import main.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
@EnableTransactionManagement
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    /**
     * Add a dish and insert the flavour data at the same time
     * <p>@Transactional transaction annotation, if adding a dish fails, the flavour data will not be added either.
     *
     * @param dishDto dish data
     */
    @Transactional
    @Override
    public void saveWithFlavors(DishDto dishDto) {

        this.save(dishDto);

        Long id = dishDto.getId();

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().peek(item -> {
            item.setDishId(id);
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);



        Set<Object> keys = redisTemplate.keys("dish_*");
        assert keys != null;
        redisTemplate.delete(keys);

    }


    /**
     * Modify the dish and also include the flavour data
     * <p>@Transactional transaction annotation, if adding a dish fails, the flavour data will not be added either.
     *
     * @param dishDto dish data
     */
    @Transactional
    @Override
    public void updateWithFlavors(DishDto dishDto) {


        this.updateById(dishDto);

        Long id = dishDto.getId();

        dishFlavorService.remove(new LambdaQueryWrapper<DishFlavor>().eq(DishFlavor::getDishId, id));

        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors = flavors.stream().peek(item -> {

            item.setDishId(id);
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);

        Set<Object> keys = redisTemplate.keys("dish_*");
        assert keys != null;
        redisTemplate.delete(keys);

    }


    /**
     * Get data on dishes and flavors based on id
     *
     * @param id dish id
     * @return dish data (with taste data)
     */
    @Override
    public DishDto getByIdWithFlavors(Long id) {

        Dish dish = this.getById(id);


        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);

        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, id);
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);

        dishDto.setFlavors(flavors);

        return dishDto;
    }


    /**
     * Get data on all dishes and flavours
     *
     * @return dish data List (with taste data)
     */
    @Override
    public List<DishDto> listWithFlavors(Dish dish) {
        List<DishDto> dtoList = null;
        String key = "dish_" + dish.getCategoryId() + "_" + dish.getStatus();
        dtoList = (List<DishDto>) redisTemplate.opsForValue().get(key);

        if (dtoList != null) {
            return dtoList;
        }

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Dish::getStatus, 1);
        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        List<Dish> list = this.list(queryWrapper);

        dtoList = list.stream().map((item) -> {


            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);


            Long DishId = item.getId();
            LambdaQueryWrapper<DishFlavor> dishFlavorQueryWrapper = new LambdaQueryWrapper<>();
            dishFlavorQueryWrapper.eq(DishFlavor::getDishId, DishId);


            List<DishFlavor> dishFlavorList = dishFlavorService.list(dishFlavorQueryWrapper);


            dishDto.setFlavors(dishFlavorList);

            return dishDto;

        }).collect(Collectors.toList());


        redisTemplate.opsForValue().set(key, dtoList, 1, TimeUnit.DAYS);

        return dtoList;
    }

    /**
     * Modify the status of the dish, if it is on sale then change it to down, if it is down then change it to on sale
     *
     * @param ids dish id
     * @param status The status that needs to be changed to
     */
    @Override
    public void updateDishStatus(String ids, Integer status) {


        String[] idArray = ids.split(",");


        for (String id : idArray) {
            LambdaUpdateWrapper<Dish> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(Dish::getId, id);
            wrapper.set(Dish::getStatus, status);
            this.update(wrapper);
        }


        Set<Object> keys = redisTemplate.keys("dish_*");
        assert keys != null;
        redisTemplate.delete(keys);
    }


    /**
     * Delete dish (logical deletion)
     *
     * @param ids dish ids passed in from the front end, may be one or more, multiple data are comma separated
     */
    @Override
    public void deleteByIds(String ids) {
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            LambdaUpdateWrapper<Dish> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(Dish::getId, id);
            wrapper.set(Dish::getStatus, 0);
            wrapper.set(Dish::getIsDeleted, 1);
            this.update(wrapper);
        }

        Set<Object> keys = redisTemplate.keys("dish_*");
        assert keys != null;
        redisTemplate.delete(keys);
    }


    /**
     * Paging for dishes
     * <p>where the image of the dish is provided by {@link CommonController} for downloading to the page.
     *
     * @param page The paging parameter passed in from the front end, a one-time pass to the current page number
     * @param pageSize The paging parameter passed in from the front end, the number of items to be displayed on each page is passed in at once.
     * @param name Query condition, if name is empty, then query all dishes
     * @return Page object, the paging object provided by mybatis-plus, contains all the information for paging
     */
    public Page<DishDto> page(int page, int pageSize, String name) {

        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();

        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, Dish::getName, name);
        wrapper.orderByDesc(Dish::getUpdateTime);
        this.page(pageInfo, wrapper);
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");
        List<Dish> records = pageInfo.getRecords();

        List<DishDto> dishDtoRecordsList = records.stream().map((item) -> {

            DishDto dishDto = new DishDto();

            BeanUtils.copyProperties(item, dishDto);

            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }

            return dishDto;
        }).collect(Collectors.toList());


        dishDtoPage.setRecords(dishDtoRecordsList);

        return dishDtoPage;
    }

}
