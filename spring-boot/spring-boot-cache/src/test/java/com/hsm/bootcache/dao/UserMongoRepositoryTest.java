package com.hsm.bootcache.dao;

import com.hsm.bootcache.entity.UserMongoPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMongoRepositoryTest {

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Test
    public void insert(){
        UserMongoPO userMong = new UserMongoPO();
        userMong.setId(1);
        userMong.setName("张三同学");
        userMong.setAge(29);

        UserMongoPO insert = userMongoRepository.insert(userMong);

        System.out.println(insert.toString());
    }

    @Test
    public void fiandAll(){
        List<UserMongoPO> all = userMongoRepository.findAll();
        System.out.println(all.toString());
    }

    @Test
    public void save(){
        UserMongoPO userMong = new UserMongoPO();
        userMong.setId(1);
        userMong.setName("李四同学");
        userMong.setAge(29);
        UserMongoPO save = userMongoRepository.save(userMong);
        System.out.println(save.toString());
    }
    @Test
    public void saveAll(){
        List<UserMongoPO> dataList = new ArrayList<>();
        dataList.add(new UserMongoPO(2,"小红",12));
        dataList.add(new UserMongoPO(3,"小白",15));
        dataList.add(new UserMongoPO(4,"小钱",20));
        List<UserMongoPO> userMongoPOS = userMongoRepository.saveAll(dataList);
        System.out.println(userMongoPOS.toString());
    }

}