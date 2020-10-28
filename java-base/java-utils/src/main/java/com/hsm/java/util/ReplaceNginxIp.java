package com.hsm.java.util;

import java.io.*;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: ReplaceNginxIp
 * @description: 替换nginx配置ip地址
 * @date 2020/10/28 14:41
 */
public class ReplaceNginxIp {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("请传入参数");
            System.exit(-1);
        }
        if (args.length < 2) {
            System.out.println("没有传入ip地址,不做替换");
            System.exit(0);
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("nginx配置文件位置异常，请检查参数");
            System.exit(-1);
        }
        //用来存放内容的容器
        StringBuffer contentBuffer = new StringBuffer();
        //换行符
        String linesSparator = System.getProperty("line.separator");


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.matches(".*default.*(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." +
                        "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                        "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                        "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d).*")) {
                    line = String.format("\t\tdefault %s", args[1]);
                }
                contentBuffer.append(line ).append(linesSparator);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件读取异常");
        }

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file+"12"))) {
            printWriter.print(contentBuffer);
        }catch (Exception e) {
            System.out.println("文件写入异常");
        }
    }
}
