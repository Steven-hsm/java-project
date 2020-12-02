package com.hsm.test.map;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: TestConcurrentHashMap
 * @description: TODO
 * @date 2020/11/12 11:15
 */
public class TestConcurrentHashMap {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000000; i++) {
            final String j = i + "";
            new Thread(() -> {
                MapUtils.addGeneralMap(j, j);

            }).start();
            new Thread(() -> {
                MapUtils.addConcurrentMap(j, j);
            }).start();
            MapUtils.addGeneralMap(i + "main", i + "main");
            MapUtils.addConcurrentMap(i + "main", i + "main");
        }
        Thread.sleep(60000);
        System.out.println(MapUtils.getGeneral().size());
        System.out.println(MapUtils.getConcurrent().size());
    }
}
