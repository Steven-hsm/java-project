package com.hsm.boot.amqp.listener;

import com.rabbitmq.client.ShutdownSignalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/7/24 10:24
 */
@Slf4j
public class MyConnectionListener implements ConnectionListener {
    @Override
    public void onClose(Connection connection) {

    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {

    }

    @Override
    public void onCreate(Connection connection) {
        log.info("创建一个连接,{}" ,connection.toString());
    }
}
