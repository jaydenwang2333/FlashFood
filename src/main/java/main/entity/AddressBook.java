package main.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Address
 */
@Data
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //USER ID
    private Long userId;


    //Consignee
    private String consignee;


    //Phone number
    private String phone;


    //Gender 0 female 1 male
    private String sex;


    //Zip code
    private String zipCode;


    //State
    private String stateName;


    //City
    private String cityName;

    //Address detail
    private String detail;

    //label
    private String label;

    //default? 0 no 1 yes
    private Integer isDefault;

    //Established time
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    //Updated time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    //user established
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    //user update
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    //whether deleted?
    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;
}
