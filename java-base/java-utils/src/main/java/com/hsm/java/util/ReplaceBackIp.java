package com.hsm.java.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ReplaceBackIp {

    public static void main(String[] args) {
        if(args.length != 4){
            System.out.println("参数传入错误,请检查参数");
        }
        //数据库ip
        String dbIp = args[0];
        //zookeeper地址ip
        String dbPath = args[1];

        replaceDbIp(dbIp,dbPath);


        //数据库ip
        String zookeeperIp = args[2];
        //zookeeper地址ip
        String zookeeperPath = args[3];

        replaceZookeeperIp(zookeeperIp,zookeeperPath);
    }

    private static void replaceZookeeperIp(String zookeeperIp, String zookeeperPath) {
        //用来存放内容的容器
        StringBuffer contentBuffer = new StringBuffer();
        //换行符
        String linesSparator = System.getProperty("line.separator");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(zookeeperPath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.matches("zookeeper.address=zookeeper://(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." +
                        "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                        "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                        "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d):2181")) {
                    line = String.format("zookeeper.address=zookeeper://%s:2181", zookeeperIp);
                }
                contentBuffer.append(line ).append(linesSparator);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件读取异常");
        }

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(zookeeperPath))) {
            printWriter.print(contentBuffer);
        }catch (Exception e) {
            System.out.println("文件写入异常");
        }
    }

    private static void replaceDbIp(String dbIp, String dbPath) {
        //用来存放内容的容器
        StringBuffer contentBuffer = new StringBuffer();
        //换行符
        String linesSparator = System.getProperty("line.separator");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dbPath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.matches(".*url: jdbc:mysql://(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." +
                        "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                        "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                        "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d).*")) {
                    line = String.format("\t\turl: jdbc:mysql://%s:3306/windranger_pharmaux?characterEncoding=utf8", dbIp);
                }
                contentBuffer.append(line ).append(linesSparator);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件读取异常");
        }

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(dbPath))) {
            printWriter.print(contentBuffer);
        }catch (Exception e) {
            System.out.println("文件写入异常");
        }
    }
}
