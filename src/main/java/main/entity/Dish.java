package main.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Dish
 */
@Data
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //Dish name
    private String name;


    //Category id
    private Long categoryId;


    //Dish price
    private BigDecimal price;


    //Dish code
    private String code;


    //Dish image
    private String image;


    //Dish description
    private String description;


    //Status 0 stop_sale 1 begin_sale
    private Integer status;


    //Sequence
    private Integer sort;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    //Check whether deleted
    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;

}
