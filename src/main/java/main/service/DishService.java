package main.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import main.controller.CommonController;
import main.dto.DishDto;
import main.entity.Dish;

import java.util.List;


public interface DishService extends IService<Dish> {

    //Adding dishes while inserting data on flavours
    void saveWithFlavors(DishDto dishDto);


    //Updating the menu with data on flavours
    void updateWithFlavors(DishDto dishDto);

    //Getting data on dishes and tastes based on id
    DishDto getByIdWithFlavors(Long id);

    //Getting data on all dishes and flavours
    List<DishDto> listWithFlavors(Dish dish);


    //Change the status of the dish to down if it is on sale, or on sale if it is off the shelf
    void updateDishStatus(String ids, Integer status);


    /**
     * Delete dish (logical deletion)
     *
     * @param ids dish ids passed in from the front end, may be one or more, multiple data are comma separated
     */
    void deleteByIds(String ids);


    /**
     * Paging for dishes
     * <p>where the image of the dish is provided by {@link CommonController} for downloading to the page.
     *
     * @param page The paging parameter passed in from the front end, a one-time pass to the current page number
     * @param pageSize The paging parameter passed in from the front end, the number of items to be displayed on each page is passed in at once.
     * @param name Query condition, if name is empty, then query all dishes
     * @return Page object, the paging object provided by mybatis-plus, contains all the information for paging
     */
    Page<DishDto> page(int page, int pageSize, String name);
}
