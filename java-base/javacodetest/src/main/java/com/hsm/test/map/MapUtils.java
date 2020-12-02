package com.hsm.test.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: MapUtils
 * @description: map 存储工具
 * @date 2020/11/12 11:17
 */
public class MapUtils {
    //多个线程put的时候造成了某个key值Entry key List的死循环
    private final static Map<String, String> generalMap = new HashMap();

    private final static Map<String, String> concurrentMap = new ConcurrentHashMap<>();

    public static void addGeneralMap(String key, String value) {
        MapUtils.generalMap.put(key, value);
    }

    public static void removeGeneralMap(String key) {
        MapUtils.generalMap.remove(key);
    }

    public static void getGeneralMap(String key) {
        MapUtils.generalMap.get(key);
    }

    public static Map<String, String> getGeneral() {
        return MapUtils.generalMap;
    }


    public static void addConcurrentMap(String key, String value) {
        MapUtils.concurrentMap.put(key, value);
    }

    public static void removeConcurrentMap(String key) {
        MapUtils.concurrentMap.remove(key);
    }

    public static void getConcurrentMap(String key) {
        MapUtils.concurrentMap.get(key);
    }

    public static Map<String, String> getConcurrent() {
        return MapUtils.concurrentMap;
    }

}
