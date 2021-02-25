package com.hsm.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁:是指尝试获取锁的线程不会立即阻塞,而是采用循环的方式去尝试获取锁
 * 优点:可以减少线程上下文切换的开销 缺点:增加CPU的消耗
 */
public class SpinLock {
    AtomicReference<Thread> atomicReference = new AtomicReference();
    public static void main(String[] args) {

        SpinLock spinLock = new SpinLock();
        new Thread(()->{
            spinLock.mylock();
            spinLock.unlock();
        },"AA");


        new Thread(()->{
            spinLock.mylock();
            spinLock.unlock();
        },"BB");

    }

    public void mylock(){
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null,thread)){
        }
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
    }
}
