package main.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * address book
 */
@Data
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //userId
    private Long userId;


    //consignee
    private String consignee;


    //phone
    private String phone;


    //Gender 0 Female 1 Male
    private String sex;


    //provinceCode
    private String provinceCode;


    //provinceName
    private String provinceName;


    //cityCode
    private String cityCode;


    //cityName
    private String cityName;


    //districtCode
    private String districtCode;


    //districtName
    private String districtName;


    //detail Add
    private String detail;


    //label
    private String label;

    //Whether the default is 0 no 1 yes
    private Integer isDefault;

    //createTime
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    //updateTime
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    //createUser
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    //updateUser
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    //isDeleted
    private Integer isDeleted;
}
