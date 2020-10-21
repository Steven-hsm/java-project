package com.hsm.bootcache.controller;

import com.hsm.bootcache.service.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: CacheController
 * @description: TODO
 * @date 2020/10/21 15:40
 */
@RestController
public class CacheController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public Object getbook(){
        return bookRepository.queryAll(1);
    }

    @GetMapping("/update")
    public Object update(){
        return bookRepository.update(1);
    }

    @GetMapping("/clear")
    public Object clear(){
        return bookRepository.clear(1);
    }

}
