package main.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import main.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}