package com.hsm.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/9/27 15:36
 */
public class DLXProducer {
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
        //声明 dlx
        channel.exchangeDeclare("exchage.dlx", "direct");
        channel.queueDeclare("queue.dlx", true, false, false, null);
        channel.queueBind("queue.dlx", "exchage.dlx", "dlx_key");

        //声明普通队列和dlx绑定
        Map<String, Object> mapArgs = new HashMap<>();
        mapArgs.put("x-message-ttl", 10000);
        mapArgs.put("x-dead-letter-exchange", "exchage.dlx");
        mapArgs.put("x-dead-letter-routing-key", "dlx_key");

        channel.exchangeDeclare("exchage.normal", "direct");
        channel.queueDeclare("queue.normal", true, false, false, mapArgs);
        channel.queueBind("queue.normal", "exchage.normal", "normal_key");

        String message = "dlx_message";

        channel.basicPublish("exchage.normal", "normal_key", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

        channel.close();
        connection.close();
    }
}
