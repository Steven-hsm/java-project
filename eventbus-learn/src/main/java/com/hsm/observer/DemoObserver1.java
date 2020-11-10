package com.hsm.observer;

import com.google.common.eventbus.Subscribe;
import com.hsm.entity.User;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: DemoObserver1
 * @description: TODO
 * @date 2020/11/10 15:32
 */
public class DemoObserver1 {

    @Subscribe
    public void func(String msg){
        System.out.println("从enventbus 中获取字符串信息为:" + msg);
    }

    @Subscribe
    public void funUser(User user){
        System.out.println("从enventbus 中获取得到的用户信息:" + user.toString());
    }
}
