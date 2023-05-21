package main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import main.common.CustomException;
import main.dto.SetmealDto;
import main.entity.Category;
import main.entity.Setmeal;
import main.entity.SetmealDish;
import main.mapper.SetmealMapper;
import main.service.CategoryService;
import main.service.SetmealDishService;
import main.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@EnableTransactionManagement
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {



    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    /**
     * Add a new set menu, and you need to save the correlation between the set menu and the dishes
     *
     * @param setmealDto Set meal data transfer object
     */
    @Override
    @Transactional
    public void saveWithDishes(SetmealDto setmealDto) {

        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().peek(item -> item.setSetmealId(setmealDto.getId())).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);


        Set<Object> keys = redisTemplate.keys("setmeal_*");
        assert keys != null;
        redisTemplate.delete(keys);

    }


    /**
     * Deleting a set menu, and the data associated with the set menu and the dish, only the set menu that is no longer available for sale can be deleted.
     * The rollbackFor attribute of the <p>@Transactional annotation is used to specify which exceptions need to be rolled back and which ones don't. By default, only runtime exceptions will be rolled back.
     *
     * @param ids set of package ids
     */
    @Override
    @Transactional
    public void removeWithDishes(List<Long> ids) {

        LambdaQueryWrapper<Setmeal> setmealQueryWrapper = new LambdaQueryWrapper<>();
        setmealQueryWrapper.in(Setmeal::getId, ids).eq(Setmeal::getStatus, 1);
        int count = this.count(setmealQueryWrapper);

        if (count > 0) {
            throw new CustomException("Set meals are on sale and cannot be deleted");
        }

        this.removeByIds(ids);

        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(setmealDishLambdaQueryWrapper);


        Set<Object> keys = redisTemplate.keys("setmeal_*");
        assert keys != null;
        redisTemplate.delete(keys);

    }


    /**
     * Modify package status to down if on sale, or on sale if off sale
     *
     * @param ids List of package ids
     * @param status The status to be modified
     */
    @Override
    public void changeStatus(List<Long> ids, Integer status) {
        for (Long id : ids) {
            LambdaUpdateWrapper<Setmeal> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Setmeal::getId, id);
            updateWrapper.set(Setmeal::getStatus, status);
            this.update(updateWrapper);
        }


        Set<Object> keys = redisTemplate.keys("setmeal_*");
        assert keys != null;
        redisTemplate.delete(keys);

    }


    /**
     * Get the basic information of a set menu and the dishes included in the set menu based on the id
     *
     * @param id set meal id
     */
    @Override
    public SetmealDto getByIdWithDishes(Long id) {

        Setmeal setmeal = this.getById(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal, setmealDto);
        LambdaQueryWrapper<SetmealDish> setmealDishQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishQueryWrapper.eq(SetmealDish::getSetmealId, id);
        List<SetmealDish> dishes = setmealDishService.list(setmealDishQueryWrapper);

        setmealDto.setSetmealDishes(dishes);


        return setmealDto;
    }



    @Transactional
    @Override
    public void updateWithDishes(SetmealDto setmealDto) {

        this.updateById(setmealDto);

        Long setmealId = setmealDto.getId();

        setmealDishService.remove(new LambdaQueryWrapper<SetmealDish>().eq(SetmealDish::getSetmealId, setmealId));

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();

        setmealDishes = setmealDishes.stream().peek(item -> {
            item.setSetmealId(setmealId);
        }).collect(Collectors.toList());


        setmealDishService.saveBatch(setmealDishes);



        Set<Object> keys = redisTemplate.keys("setmeal_*");
        assert keys != null;
        redisTemplate.delete(keys);

    }


    /**
     * Check the list of set menus and also check the information of the dishes included in the set menus
     *
     * @param setmeal Query criteria
     * @return setmeal list
     */
    @Override
    public List<SetmealDto> listWithDishes(Setmeal setmeal) {

        List<SetmealDto> setmealDtoList = null;


        String key = "setmeal_" + setmeal.getCategoryId() + "_" + setmeal.getStatus();


        setmealDtoList = (List<SetmealDto>) redisTemplate.opsForValue().get(key);


        if (setmealDtoList != null) {
            return setmealDtoList;
        }

        // If there is no data in redis, query from the database
        // Create the constructor
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(setmeal.getCategoryId() != null, Setmeal::getCategoryId, setmeal.getCategoryId())
                .eq(Setmeal::getStatus, 1)
                .orderByDesc(Setmeal::getUpdateTime);

        List<Setmeal> setmealList = this.list(queryWrapper);


        setmealDtoList = setmealList.stream().map(item -> {

            SetmealDto setmealDto = new SetmealDto();


            BeanUtils.copyProperties(item, setmealDto);


            LambdaQueryWrapper<SetmealDish> setmealDishQueryWrapper = new LambdaQueryWrapper<>();
            setmealDishQueryWrapper.eq(SetmealDish::getSetmealId, item.getId());

            List<SetmealDish> setmealDishList = setmealDishService.list(setmealDishQueryWrapper);

            setmealDto.setSetmealDishes(setmealDishList);

            return setmealDto;

        }).collect(Collectors.toList());

        redisTemplate.opsForValue().set(key, setmealDtoList);


        return setmealDtoList;
    }

    /**
     * Paging for package information
     *
     * @param page Current page
     * @param pageSize Number of items per page
     * @param name Package name
     */
    public Page<SetmealDto> page(int page, int pageSize, String name) {

        Page<Setmeal> setmealPage = new Page<>(page, pageSize);
        Page<SetmealDto> setmealDtoPage = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(name != null, Setmeal::getName, name);

        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        this.page(setmealPage, queryWrapper);
        BeanUtils.copyProperties(setmealPage, setmealDtoPage, "records");

        List<Setmeal> records = setmealPage.getRecords();


        List<SetmealDto> setmealDtoRecordsList = records.stream().map(item -> {
            SetmealDto setmealDto = new SetmealDto();

            BeanUtils.copyProperties(item, setmealDto);

            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }

            return setmealDto;
        }).collect(Collectors.toList());

        setmealDtoPage.setRecords(setmealDtoRecordsList);

        return setmealDtoPage;
    }
}
