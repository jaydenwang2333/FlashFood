package main.entity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order
 */
@Data
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //Order number
    private String number;

    //Order status, 1 payment processing，2 waiting delivery，3 delivered，4 completed，5 cancelled
    private Integer status;


    //User id
    private Long userId;

    //Address id
    private Long addressBookId;


    //Order time
    private LocalDateTime orderTime;


    //Purchased time
    private LocalDateTime checkoutTime;


    //Payment method 1 cash? 2 Paypal?
    private Integer payMethod;


    //Payment amount
    private BigDecimal amount;

    //Remark
    private String remark;

    //User name
    private String userName;

    //Phone number
    private String phone;

    //Address
    private String address;

    //Consignee
    private String consignee;
}
