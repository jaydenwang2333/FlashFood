package main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import main.entity.Category;

public interface CategoryService extends IService<Category> {

    /**
     * Delete category
     *
     * @param id category id
     */
    void remove(Long id);

}
