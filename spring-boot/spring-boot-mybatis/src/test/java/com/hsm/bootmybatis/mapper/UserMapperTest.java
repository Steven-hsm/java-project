package com.hsm.bootmybatis.mapper;

import com.hsm.bootmybatis.entity.po.UserPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/8/4 15:39
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback
    public void test() throws Exception {
        userMapper.insert("AAA", 20);
        UserPO u = userMapper.findByName("AAA");
        Assert.assertEquals(20, u.getAge().intValue());
    }

    @Test
    @Rollback
    public void test2() throws Exception {
        userMapper.insert("AAA", 20);
        UserPO u = userMapper.findByName("AAA");
        Assert.assertEquals(20, u.getAge().intValue());
    }
}
