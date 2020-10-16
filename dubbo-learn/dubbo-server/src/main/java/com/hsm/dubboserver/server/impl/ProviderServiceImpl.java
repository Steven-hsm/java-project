package com.hsm.dubboserver.server.impl;

import com.hsm.dubboserver.ProviderService;

public class ProviderServiceImpl implements ProviderService {
    @Override
    public String SayHello(String word) {
        System.out.println("传入参数：" + word);
        return word;
    }
}
