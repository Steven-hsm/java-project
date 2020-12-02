package com.hsm;

import com.google.common.eventbus.EventBus;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: EventBusCenter
 * @description: eventBus管理中心
 * @date 2020/11/10 15:25
 */
public class EventBusCenter {

    private static EventBus eventBus = new EventBus();

    private EventBusCenter() {
    }

    public static void registry(Object obj){
        eventBus.register(obj);
    }

    public static void unRegistry(Object obj){
        eventBus.register(obj);
    }

    public static void post(Object obj) {
        eventBus.post(obj);
    }

}
