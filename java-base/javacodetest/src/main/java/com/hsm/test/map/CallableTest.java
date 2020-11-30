package com.hsm.test.map;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: CallableTest
 * @description: TODO
 * @date 2020/11/24 10:23
 */
public class CallableTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            int j = i;
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    
                    System.out.println(String.format("时间%s:%s", j, System.currentTimeMillis()));
                    return "";
                }
            };
            executorService.submit(callable);
        }


    }
}
