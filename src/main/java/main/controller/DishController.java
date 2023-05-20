package main.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import main.dto.DishDto;
import main.common.R;
import main.entity.Dish;
import main.service.CategoryService;
import main.service.DishFlavorService;
import main.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 */
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {

    /**
     * dish service
     */
    @Autowired
    private DishService dishService;
    /**
     * dish flavor service
     */
    @Autowired
    private DishFlavorService dishFlavorService;
    /**
     * category controller
     */
    @Autowired
    private CategoryService categoryService;


    /**
     * <h2>add dish<h2/>
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        log.info("add dish，dishDto: {}", dishDto.toString());
        // save dish
        dishService.saveWithFlavors(dishDto);

        return R.success("Add new dish success");
    }


    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("search dish，page: {}, pageSize: {}, name: {}", page, pageSize, name);

        // search dishDtoPage object
        Page<DishDto> dishDtoPage = dishService.page(page, pageSize, name);

        // return dishDtoPage object
        return R.success(dishDtoPage);
    }


    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id) {
        // search dishDto
        DishDto dishDto = dishService.getByIdWithFlavors(id);

        return R.success(dishDto);
    }


    /**
     * <h2>edit dish<h2/>
     */
    @PutMapping
    public R<String> put(@RequestBody DishDto dishDto) {
        log.info("Edit dish，dishDto: {}", dishDto.toString());       // Slf4j的日志输出

        // save
        dishService.updateWithFlavors(dishDto);

        return R.success("Edited success");
    }


    /**
     * <h2>change dish status<h2/>
     */
    @PostMapping("/status/{status}")
    public R<String> changeStatus(String ids, @PathVariable Integer status) {
        log.info("Edit dish status，id: {}, status: {}", ids, status);

        dishService.updateDishStatus(ids, status);

        return R.success("Edited success");
    }


    /**
     * <h2>Delete dish<h2/>
     */
    @DeleteMapping
    public R<String> delete(String ids) {
        log.info("Delete dish，id: {}", ids);

        dishService.deleteByIds(ids);

        return R.success("Deleted success");
    }

    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish) {
        log.info("Search dish list，dish: {}", dish);

        List<DishDto> dishDtoList = dishService.listWithFlavors(dish);

        return R.success(dishDtoList);
    }

}