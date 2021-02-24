package com.hsm.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.UnSafe是CAS的核心类 由于Java 方法无法直接访问底层 ,需要通过本地(native)方法来访问,UnSafe相当于一个后门,
 *  基于该类可以直接操作特定的内存数据.UnSafe类在于sun.misc包中,其内部方法操作可以向C的指针一样直接操作内存,
 *  因为Java中CAS操作的助兴依赖于UNSafe类的方法.
 * 注意UnSafe类中所有的方法都是native修饰的,也就是说UnSafe类中的方法都是直接调用操作底层资源执行响应的任务
 */
public class MyUnsafe {
    public static void main(String[] args) {
        AtomicInteger atomicInteger  = new AtomicInteger();
        int andIncrement = atomicInteger.getAndIncrement();
    }
    
}
