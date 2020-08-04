package com.hsm.bootweb.entity;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/8/4 14:33
 */

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserVO {
    @NotEmpty(message = "用户名不能为空")
    private String name;
    private int age;
}
