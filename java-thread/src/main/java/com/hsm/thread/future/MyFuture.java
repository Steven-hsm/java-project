package com.hsm.thread.future;

import com.hsm.thread.entity.User;

import java.util.concurrent.*;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: MyFuture
 * @description: 加单获取线程的值
 * @date 2020/12/13 10:45
 */
public class MyFuture {
    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(10);
        User user = new User();
        executors.submit(() -> {
            System.out.println("123213");
            //return new User("小玲", 30);
        }, user);

        Future<User> result = executors.submit(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return new User("小玲", 30);
            }
        });
        User user1 = null;
        try {
            user1 = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(user1);
    }
}
