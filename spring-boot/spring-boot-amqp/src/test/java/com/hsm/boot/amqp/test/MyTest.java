package com.hsm.boot.amqp.test;

import org.junit.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/7/24 09:34
 */

public class MyTest {
    @Test
    public void test(){
        //设置连接池
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("192.168.4.31");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("doyd");
        connectionFactory.setPassword("doyd1913");

        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        admin.declareQueue(new Queue("myqueue"));
        AmqpTemplate template = new RabbitTemplate(connectionFactory);
        template.convertAndSend("myqueue", "foo");
        String foo = (String) template.receiveAndConvert("myqueue");

        System.out.println(foo);
    }
}
