package com.hsm.bootcache.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: Status
 * @description: TODO
 * @date 2020/10/21 18:07
 */
@Data
@Accessors(chain = true)
public class Status {

    private Integer weight;
    private Integer height;

}