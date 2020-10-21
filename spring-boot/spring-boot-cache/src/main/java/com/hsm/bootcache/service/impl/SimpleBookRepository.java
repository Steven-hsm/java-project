package com.hsm.bootcache.service.impl;

import com.hsm.bootcache.dao.BookDao;
import com.hsm.bootcache.entity.Book;
import com.hsm.bootcache.service.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: SimpleBookRepository
 * @description: TODO
 * @date 2020/10/21 15:20
 */
@Service
public class SimpleBookRepository implements BookRepository {
    @Autowired
    private BookDao bookDao;
    @Override
    @Cacheable("book")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(1,isbn, "Some book");
    }

    @Cacheable(value = "emp" ,key = "targetClass")
    @Override
    public Book queryAll(int id) {
        return bookDao.findAllByUid(id);
    }

    @CachePut(value = "emp" ,key = "targetClass")
    @Override
    public Book update(int i) {
        return new Book(1,"update value", "Some book");
    }

    @CacheEvict(value = "emp" ,key = "targetClass")
    @Override
    public Book clear(int i) {
        return new Book(1,"clear value", "Some book");
    }


    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
