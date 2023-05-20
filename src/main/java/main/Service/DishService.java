package main.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import main.dto.DishDto;
import main.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {
    void addDish(DishDto dishDto);
    DishDto edit(Long id);
    void updateDish(DishDto dishDto);
    void deleteDish(String ids);
    void offSaleDish(String ids);
    void startSaleDish(String ids);
    List<DishDto> getDishesByCategoryId(Dish dish);
    Page page(int page, int pageSize, String name);
    Dish getDishById(Long id);

}
