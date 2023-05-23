package main.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Shopping Cart
 */
@Data
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //Name
    private String name;

    //User id
    private Long userId;

    //Dish id
    private Long dishId;

    //Combo id
    private Long setmealId;

    //Flavor
    private String dishFlavor;

    //Quantity
    private Integer number;

    //Money amount
    private BigDecimal amount;

    //Image
    private String image;

    //@TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
