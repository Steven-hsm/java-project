package com.hsm.single;

public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法");
    }

    /**
     * 双重检测机制 DCL(double lock check)
     *  存在指令重排序.可能也不是一定是安全的
     * @return
     */
    public static SingletonDemo getInstance() {
        //指令重排 instance可能分配了地址,但是没有初始化数据
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
