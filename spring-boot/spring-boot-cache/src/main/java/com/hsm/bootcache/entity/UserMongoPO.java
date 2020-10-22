package com.hsm.bootcache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: UserMongPO
 * @description: TODO
 * @date 2020/10/22 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMongoPO {
    @Id
    private int id;

    private String name;

    private int age;
}
