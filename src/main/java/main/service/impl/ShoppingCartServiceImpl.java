package main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import main.common.BaseContext;
import main.entity.ShoppingCart;
import main.mapper.ShoppingCartMapper;
import main.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

    /**
     * Add a dish or set menu to your cart, if you add the same one, the quantity will be added up
     *
     * @param shoppingCart The dish to add
     * @return the shopping cart after adding
     */
    public ShoppingCart addToCart(ShoppingCart shoppingCart) {


        long UserId = BaseContext.getCurrentId();
        shoppingCart.setUserId(UserId);

        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, UserId);

        // If you can find the dishId, it means that the shopping cart is sending the dish, otherwise it is a set menu
        if (dishId != null) {
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
        } else {
            queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }

        ShoppingCart one = this.getOne(queryWrapper);
        if (one != null) {
            Integer number = one.getNumber();
            one.setNumber(number + 1);
            one.setCreateTime(LocalDateTime.now());
            this.updateById(one);
        } else {
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            this.save(shoppingCart);
            one = shoppingCart;
        }

        return one;
    }


    /**
     * Reduce the dish or set menu to the cart and cancel the dish or set menu if it is the last one
     *
     * @param shoppingCart The dish to be reduced
     * @return the shopping cart after reduction
     */
    public ShoppingCart subInCart(ShoppingCart shoppingCart) {

        long UserId = BaseContext.getCurrentId();
        shoppingCart.setUserId(UserId);


        Long dishId = shoppingCart.getDishId();


        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, UserId);

        if (dishId != null) {
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
        } else {
            queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }

        ShoppingCart one = this.getOne(queryWrapper);

        if (one.getNumber() >= 2) {
            Integer number = one.getNumber();
            one.setNumber(number - 1);
            this.updateById(one);
        } else {
            one.setNumber(0);
            this.remove(queryWrapper);
        }

        return one;
    }


    /**
     * Check the shopping cart
     *
     * @return shopping cart list
     */
    @Override
    public List<ShoppingCart> showCart() {

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);
        return this.list(queryWrapper);
    }

    @Override
    public void cleanCart() {

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());

        this.remove(queryWrapper);
    }
}
