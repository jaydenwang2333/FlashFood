package main.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import main.common.CustomException;
import main.common.R;
import main.entity.Category;
import main.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * <h2>New dish category<h2/>
     *
     * @param category Dishes classification entity class，
     * @return return R object
     */
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("New dish category，category={}", category);
        categoryService.save(category);

        return R.success("added successfully");
    }


    /**
     * <h2>Show menu list<h2/>
     *
     * @param page     The pagination parameters passed by the front end
     * @param pageSize The pagination parameters passed by the front end
     * @return R object
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {

        Page<Category> pageInfo = new Page<>();

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByAsc(Category::getSort);

        categoryService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }


    /**
     * <h2>Delete dish classification according to id<h2/>
     * <p>If there are still dishes in the category or package, it is not allowed to delete,
     * throw{@link CustomException}
     *
     * @param id
     * @return return result
     */
    @DeleteMapping
    public R<String> delete(Long id) {
        log.info("Delete dish category，id={}", id);
        categoryService.remove(id);

        return R.success("Category information deleted successfully");
    }


    /**
     * <h2>Modify dish classification information<h2/>
     *
     * @param category Dishes classification entity class，@RequestBody\
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category) {
        log.info("Modify dish classification information：{}", category);
        categoryService.updateById(category);

        return R.success("Modify information successfully");
    }


    /**
     * <h2>Obtain a list of dish classifications or a list of package classifications according to type<h2/>
     *
     * @param category The entity class is used to receive the parameters passed by the front end. When the front-end needs to obtain a classification information list to add dishes,
     *                 the parameter in the entity class is type = 1 (1 is the dish, 2 is the set meal).
     *       * At the same time, this method can also have other similar functions,
     *           and the return is a List. Since the input in the previous section is not json data, there is no need to use the @RequestBody annotation.
     * @return return category List
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category) {

        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(category.getType() != null, Category::getType, category.getType());

        lambdaQueryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(lambdaQueryWrapper);
        return R.success(list);
    }


}
