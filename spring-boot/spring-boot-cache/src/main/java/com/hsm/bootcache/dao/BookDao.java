package com.hsm.bootcache.dao;

import com.hsm.bootcache.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Random;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: BookDao
 * @description: TODO
 * @date 2020/10/21 15:35
 */
@Repository
@Slf4j
public class BookDao {

    public Book findAllByUid(int id) {
        int i = new Random().nextInt(100);
        log.info("随机数字:{}", i);
        if (i % 2 == 0) {
            return new Book(id, "1231", "Some book2");
        } else {
            return new Book(id, "332", "Some book3");
        }

    }
}
