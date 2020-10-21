package com.hsm.bootcache.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: User
 * @description: TODO
 * @date 2020/10/21 18:01
 */
@Data
@Accessors(chain = true)
public class User {
    /**
     * 使用 @MongoID 能更清晰的指定 _id 主键
     */
    @MongoId
    private String id;
    private String name;
    private String sex;
    private Integer salary;
    private Integer age;
    @JsonFormat( pattern ="yyyy-MM-dd", timezone ="GMT+8")
    private Date birthday;
    private String remake;
    private Status status;


}

