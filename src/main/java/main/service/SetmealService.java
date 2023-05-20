package main.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import main.dto.SetmealDto;
import main.entity.Setmeal;

import java.util.List;


public interface SetmealService extends IService<Setmeal> {

    /**
     * Add a new set menu, and you need to save the correlation between the set menu and the dishes
     *
     * @param setmealDto Set meal data transfer object
     */
    void saveWithDishes(SetmealDto setmealDto);

    /**
     * Deleting a set menu and the associated data of the set menu and the dish
     *
     * @param ids List of set menu ids
     */
    void removeWithDishes(List<Long> ids);

    /**
     * Modify package status to down if on sale, or on sale if off sale
     *
     * @param ids List of package ids
     * @param status The status to be modified
     */
    void changeStatus(List<Long> ids, Integer status);

    /**
     * Get the basic information of a set menu and the dishes included in the set menu based on the id
     *
     * @param id set meal id
     */
    SetmealDto getByIdWithDishes(Long id);

    /**
     * Update the set menu information and also update the association between the set menu and the dishes
     *
     * @param setmealDto Set meal data transfer object
     */
    void updateWithDishes(SetmealDto setmealDto);

    /**
     * Get the basic information of all packages and the dishes included in the package
     *
     * @param setmeal setmeal search criteria
     */
    List<SetmealDto> listWithDishes(Setmeal setmeal);

    /**
     * Paging for package information
     *
     * @param page Current page
     * @param pageSize Number of items per page
     * @param name Package name
     */
    Page<SetmealDto> page(int page, int pageSize, String name);
}
