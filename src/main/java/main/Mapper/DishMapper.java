package main.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import main.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
