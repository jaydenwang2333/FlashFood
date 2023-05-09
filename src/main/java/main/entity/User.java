package main.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * User information
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //Name
    private String name;


    //Email
    private String email;


    //Gender
    private String sex;


    //Id number
    private String idNumber;


    //User avatar
    private String avatar;


    //Status 0:prohibited，1:normal
    private Integer status;
}
