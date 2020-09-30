package com.hsm.tomcat.c01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/9/29 10:39
 */
public class HttpServer {
    //D:\github\java-project\tomcat-learn\target\classes\webroot
    //public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
    public static final String WEB_ROOT = "D:\\github\\java-project\\tomcat-learn\\src\\main\\resources\\webroot";

    private static final String SHUTDOWN_COMMAND = "/shutdown";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await(8080);

    }

    public void await(int port){
        //服务器端监听
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        //没有停止,一直监听客户端请求
        while (!shutdown){
            Socket socket = null;
            InputStream is = null;
            OutputStream os = null;

            try{
                socket = serverSocket.accept();
                is = socket.getInputStream();
                os = socket.getOutputStream();
                Request request = new Request(is);
                request.parse();

                Response response = new Response(os);
                response.setRequst(request);
                response.sendStaticResource();

                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }

    }
}
