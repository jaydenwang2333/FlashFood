//package main.controller;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import main.common.R;
//import main.dto.SetmealDto;
//import main.entity.Setmeal;
//import main.service.CategoryService;
//import main.service.SetmealDishService;
//import main.service.SetmealService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//@RequestMapping("/setmeal")
//public class SetmealController {
//
//    /**
//     * setmeal service
//     */
//    @Autowired
//    private SetmealService setmealService;
//
//    /**
//     * setmeal dish service
//     */
//    @Autowired
//    private SetmealDishService setmealDishService;
//
//    /**
//     * category service
//     */
//    @Autowired
//    private CategoryService categoryService;
//
//
//    /**
//     * <h2>Add new setmeal<h2/>
//     */
//    @PostMapping
//    public R<String> save(@RequestBody SetmealDto setmealDto) {
//        log.info("Add setmeal:{}", setmealDto);
//        setmealService.saveWithDishes(setmealDto);
//
//        return R.success("Added setmeal success");
//    }
//
//
//    /**
//     * <h2>Search setmeal<h2/>
//     */
//    @GetMapping("/page")
//    public R<Page> page(int page, int pageSize, String name) {
//
//        Page<SetmealDto> setmealDtoPage = setmealService.page(page, pageSize, name);
//
//        return R.success(setmealDtoPage);
//    }
//
//
//    /**
//     * <h2>Delete setmeal<h2/>
//     */
//    @DeleteMapping
//    public R<String> delete(@RequestParam List<Long> ids) {
//        log.info("deleted setmeal ids:{}", ids);
//        setmealService.removeWithDishes(ids);
//
//        return R.success("Deleted setmeal success!");
//    }
//
//
//    /**
//     * <h2>Change setmeal status<h2/>
//     */
//    @PostMapping("/status/{status}")
//    public R<String> changeStatus(@RequestParam List<Long> ids, @PathVariable Integer status) {
//        log.info("Setmeal ids:{}, status:{}", ids, status);
//        setmealService.changeStatus(ids, status);
//
//        return R.success("Changed status success!");
//    }
//
//
//    /**
//     * <h2>Search setmeal by id<h2/>
//     */
//    @GetMapping("/{id}")
//    public R<SetmealDto> get(@PathVariable Long id) {
//        log.info("Setmeal id:{}", id);
//        SetmealDto setmealDto = setmealService.getByIdWithDishes(id);
//
//        return R.success(setmealDto);
//    }
//
//
//    /**
//     * <h2>Edit setmeal<h2/>
//     */
//    @PutMapping
//    public R<String> put(@RequestBody SetmealDto setmealDto) {
//        log.info("Edit setmeal:{}", setmealDto);
//        setmealService.updateWithDishes(setmealDto);
//
//        return R.success("Edited setmeal success!");
//    }
//
//
//    /**
//     * <h2>Search setmeal<h2/>
//     */
//    @GetMapping("/list")
//    public R<List<SetmealDto>> list(Setmeal setmeal) {
//        log.info("Search setmeal:{}", setmeal);
//        List<SetmealDto> setmealDtoList = setmealService.listWithDishes(setmeal);
//
//        return R.success(setmealDtoList);
//    }
//
//
//
//    @GetMapping("/dish/{id}")
//    public R<SetmealDto> dish(@PathVariable Long id) {
//        log.info("Search setmeal id:{}", id);
//        SetmealDto dishes = setmealService.getByIdWithDishes(id);
//
//        return R.success(dishes);
//    }
//}