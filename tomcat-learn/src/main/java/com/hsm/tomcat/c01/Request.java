package com.hsm.tomcat.c01;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/9/29 10:51
 */
public class Request {
    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse() {
        StringBuilder request = new StringBuilder(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char)buffer[j]);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
    }

    private String parseUri(String requstStr) {
        int index1,index2;
        index1 = requstStr.indexOf(' ');
        if(index1 != -1){
            index2 = requstStr.indexOf(' ',index1 + 1);
            if(index2 > index1){
                return requstStr.substring(index1+1, index2);
            }
        }

        return null;
    }

    public String getUri() {
        return uri;
    }
}
