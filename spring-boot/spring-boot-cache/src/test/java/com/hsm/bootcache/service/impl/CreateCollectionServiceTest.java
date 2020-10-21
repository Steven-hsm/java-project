package com.hsm.bootcache.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateCollectionServiceTest {
    @Autowired
    private CreateCollectionService createCollectionService;

    @Test
    public void createCollection() {
        Object collection = createCollectionService.createCollection();
        System.out.println(collection.toString());
    }

    @Test
    public void createCollectionFixedSize() {
    }

    @Test
    public void createCollectionValidation() {
    }
}