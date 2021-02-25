package com.hsm;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyBlockQueue {

    public static void main(String[] args) {
        BlockingQueue<String> blockQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockQueue.add("a"));
        System.out.println(blockQueue.add("b"));
        System.out.println(blockQueue.add("c"));

    }
}
