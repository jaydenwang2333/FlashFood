package main.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /** user name */
    private String username;

    /** user name */
    private String name;

    /** password */
    private String password;

    /** phone number */
    private String phone;

    /** gender */
    private String sex;


    private String idNumber;

    /** employee status：1:employee otherwise employess */
    private Integer status;

    /** creation time */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** updating time */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

}
