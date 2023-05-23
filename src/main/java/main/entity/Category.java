package main.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Category
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //Type 1 Dish_type 2 Combo_type
    private Integer type;


    //Category name
    private String name;


    //Sequence
    private Integer sort;


    //Established time
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    //Updated time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    //User who established
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    //User who update
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    //private Integer isDeleted;

}
