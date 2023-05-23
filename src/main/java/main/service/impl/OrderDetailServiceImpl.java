package main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import main.entity.OrderDetail;
import main.mapper.OrderDetailMapper;
import main.service.OrderDetailService;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
