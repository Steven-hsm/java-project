package com.hsm.bootcache.service;

import com.hsm.bootcache.entity.Book;

public interface BookRepository {

    Book getByIsbn(String isbn);

    Book queryAll(int id);

    Book update(int i);

    Book clear(int i);
}
