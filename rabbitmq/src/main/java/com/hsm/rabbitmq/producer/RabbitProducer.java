package com.hsm.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/9/27 14:00
 */
public class RabbitProducer {
    public static final String EXCHANGE_NAME = "exchange_demo";
    public static final String ROUTING_KEY = "key_demo";
    public static final String QUEUE_NAME = "queue_demo";

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 5672;
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(USER_NAME);
        connectionFactory.setPassword(PASSWORD);

        //创建连接
        Connection connection = connectionFactory.newConnection();
        //创建渠道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct",true,false,null);
        //声明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

        String message = "hello world";

        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

        channel.close();
        connection.close();
    }
}


