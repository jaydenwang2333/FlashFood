package main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import main.common.CustomException;
import main.entity.Category;
import main.entity.Dish;
import main.entity.Setmeal;
import main.mapper.CategoryMapper;
import main.service.CategoryService;
import main.service.DishService;
import main.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;


    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count = dishService.count(dishLambdaQueryWrapper);

        if (count > 0) {
            throw new CustomException("Dishes exist under this category and therefore cannot be deleted");
        }


        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        count = setmealService.count(setmealLambdaQueryWrapper);

        if (count > 0) {
            throw new CustomException("Packages exist under this category and therefore cannot be deleted");
        }

        super.removeById(id);
    }
}
