package com.hsm.bootcache.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryCollectionServiceTest {
    @Autowired
    private QueryCollectionService queryCollectionService;

    @Test
    public void getCollectionNames() {
        Object collectionNames = queryCollectionService.getCollectionNames();
        System.out.println(collectionNames);
    }

    @Test
    public void collectionExists() {
        Object collectionNames = queryCollectionService.collectionExists();
        System.out.println(collectionNames);
    }
}