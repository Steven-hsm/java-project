package com.hsm.java.util;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: Main
 * @description: TODO
 * @date 2020/11/27 14:25
 */
public class Main {
    public static void main(String[] args) {
        boolean startedFlag = Boolean.valueOf(System.getProperty("started"));
        if(startedFlag){
            System.setProperty("started",String.valueOf(true));
        }
        System.out.println(startedFlag);
        System.setProperty("started",String.valueOf(true));
        System.out.println(Boolean.valueOf(System.getProperty("started")));
    }

}
