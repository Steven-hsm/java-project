package com.hsm.rabbitmq.comsumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/9/27 14:21
 */
public class RabbitConsumer {
    public static final String QUEUE_NAME = "queue_demo";
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 5672;
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Address[] addresses = new Address[]{new Address(HOST, PORT)};

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(USER_NAME);
        connectionFactory.setPassword(PASSWORD);

        Connection connection = connectionFactory.newConnection(addresses);

        Channel channel = connection.createChannel();
        //设置客户端最多接收未被ack的消息的个数
        channel.basicQos(64);


        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("获取的消息:" + new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(QUEUE_NAME, consumer);
        TimeUnit.SECONDS.sleep(1);

        channel.close();
        connection.close();

    }
}
