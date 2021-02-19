package com.hsm.thread.entity;

import lombok.Data;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: User
 * @description: TODO
 * @date 2020/12/13 10:56
 */
@Data
public class User {
    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }
}
