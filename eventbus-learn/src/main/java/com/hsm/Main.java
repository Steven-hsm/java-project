package com.hsm;

import com.hsm.entity.User;
import com.hsm.observer.DemoObserver1;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: Main
 * @description: TODO
 * @date 2020/11/10 15:39
 */
public class Main {
    public static void main(String[] args) {

        EventBusCenter.post("我是一个字符串信息");
        DemoObserver1 demoObserver1 = new DemoObserver1();
        EventBusCenter.registry(demoObserver1);

        EventBusCenter.post(new User());


        DemoObserver1 demoObserver2 = new DemoObserver1();
        EventBusCenter.registry(demoObserver2);
        EventBusCenter.unRegistry(demoObserver1);
        EventBusCenter.unRegistry(demoObserver2);

    }
}
