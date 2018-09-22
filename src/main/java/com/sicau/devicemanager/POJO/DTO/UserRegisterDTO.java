package com.sicau.devicemanager.POJO.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

/**
 * 注册用户传输的数据
 * @author BeFondOfTaro
 * Created at 15:11 2018/5/14
 */
@Data
public class UserRegisterDTO {

    /**
     * 用户名
     */
    @NotNull
    private String username;

    /**
     * 密码
     */
    @NotNull
    private String password;

    /**
     * 真实姓名
     */
    @NotNull
    private String realName;

    /**
     * 邮箱
     */
    @Email
    private String email;

    /**
     * 电话
     */
    @NotNull
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 角色id
     */
    @NotNull
    private String roleId;
}
