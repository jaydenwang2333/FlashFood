package main.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import main.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
