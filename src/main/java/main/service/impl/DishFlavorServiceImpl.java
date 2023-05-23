package main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import main.entity.DishFlavor;
import main.mapper.DishFlavorMapper;
import main.service.DishFlavorService;
import org.springframework.stereotype.Service;


@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {

}
