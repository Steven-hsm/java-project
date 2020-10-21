package com.hsm.bootcache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: Book
 * @description: TODO
 * @date 2020/10/21 15:19
 */
@Data
@AllArgsConstructor
public class Book implements Serializable {

    private int id;
    private String isbn;
    private String title;
}
