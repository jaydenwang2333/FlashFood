package main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import main.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService extends IService<ShoppingCart> {

    /**
     * Add a dish or a set menu to the cart, if you add the same one, the quantity is cumulative
     *
     * @param shoppingCart The dish to add
     * @return the shopping cart after adding
     */
    ShoppingCart addToCart(ShoppingCart shoppingCart);

    /**
     * Reduce the dish or set menu to the cart and cancel the dish or set menu if it is the last one
     *
     * @param shoppingCart The dish to be reduced
     * @return the shopping cart after reduction
     */
    ShoppingCart subInCart(ShoppingCart shoppingCart);

    /**
     * Show cart
     *
     * @return shopping cart list
     */
    List<ShoppingCart> showCart();

    /**
     * Emptying the shopping cart
     */
    void cleanCart();
}
