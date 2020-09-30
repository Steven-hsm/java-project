package com.hsm.tomcat.c01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/9/29 10:51
 */
public class Response {
    private static final int BUFFER_SIZE = 1024;
    private OutputStream os;
    private Request request;

    public Response(OutputStream os) {
        this.os = os;
    }

    public void setRequst(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try{
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if(file.exists()){

                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while(ch != -1){
                    os.write(bytes,0,ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            }else{
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n"+
                        "Content-type:text/html\r\n" +
                        "Content-length:23\r\n"+
                        "\r\n" +
                        "<h1>404 File Not Found</h1>";
                os.write(errorMessage.getBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis != null){
                fis.close();
            }
        }

    }
}
