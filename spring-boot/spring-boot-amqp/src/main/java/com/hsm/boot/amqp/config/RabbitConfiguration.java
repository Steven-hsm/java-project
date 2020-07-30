package com.hsm.boot.amqp.config;

import com.hsm.boot.amqp.listener.MyConnectionListener;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.ArrayList;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/7/24 09:55
 */
@Configuration
public class RabbitConfiguration {
    /** 设置连接工厂*/
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        ArrayList<ConnectionListener> connectionListeners = new ArrayList<>();
        connectionListeners.add(new MyConnectionListener());
        cachingConnectionFactory.setUsername("doyd");
        cachingConnectionFactory.setPassword("doyd1913");
        cachingConnectionFactory.setHost("192.168.4.31");
        cachingConnectionFactory.setVirtualHost("hsm_temp");
        cachingConnectionFactory.setPort(5672);

        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() throws Exception{
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());

        //设置重试方式
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(500);
        backOffPolicy.setMultiplier(10.0);
        backOffPolicy.setMaxInterval(10000);
        retryTemplate.setBackOffPolicy(backOffPolicy);
//        retryTemplate.execute(new RetryCallback<Object, Throwable>() {
//            @Override
//            public Object doWithRetry(RetryContext retryContext) throws Throwable {
//                return null;
//            }
//        }, new RecoveryCallback<Object>() {
//            @Override
//            public Object recover(RetryContext retryContext) throws Exception {
//                return null;
//            }
//        });

        rabbitTemplate.setRetryTemplate(retryTemplate);

        return rabbitTemplate;
    }

    @Bean
    public Queue myQueue() {
        return new Queue("myqueue");
    }
}
