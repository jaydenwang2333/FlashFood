package main.controller;

import main.common.R;
import main.entity.ShoppingCart;
import main.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    /**
     * ShoppingCart Service
     */
    @Autowired
    private ShoppingCartService shoppingCartService;


    /**
     * <h2>View shoppingcart<h2/>
     *
     * @return shoppingcart list
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        log.info("View Cart");
        List<ShoppingCart> shoppingCarts = shoppingCartService.showCart();

        return R.success(shoppingCarts);
    }


    /**
     * <h2>Add item to shopping cart<h2/>
     *
     * @param shoppingCart adding dish to shopping cart，@RequestBody from json data
     * @return shoppingCart
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
        log.info("Add cart:{}", shoppingCart);
        ShoppingCart shoppingCartOne = shoppingCartService.addToCart(shoppingCart);

        return R.success(shoppingCartOne);
    }

    /**
     * <h2>remove item from shopping cart<h2/>
     *
     * @param shoppingCart remove dish，@RequestBody from json data
     * @return shoppingCart
     */
    @PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart) {
        log.info("ShoppingCart:{}", shoppingCart);
        ShoppingCart shoppingCartOne = shoppingCartService.subInCart(shoppingCart);

        return R.success(shoppingCartOne);
    }


    /**
     * <h2>clean shopping cart<h2/>
     *
     * @return message
     */
    @DeleteMapping("/clean")
    public R<String> clean() {
        log.info("clean shopping cart");
        shoppingCartService.cleanCart();

        return R.success("Clean shopping cart success");
    }

}
