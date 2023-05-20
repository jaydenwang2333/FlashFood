package main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import main.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
