package main.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long orderId;

    private Long dishId;

    private Long setmealId;

    private String dishFlavor;


    // number of order
    private Integer number;

    //price
    private BigDecimal amount;

    // picture of dish
    private String image;
}