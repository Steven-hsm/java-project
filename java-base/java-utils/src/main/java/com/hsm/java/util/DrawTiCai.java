package com.hsm.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DrawTiCai {
    //private static List<Integer> buyList = Arrays.asList(2,4,6,8,10);
    private static List<Integer> buyList = Arrays.asList(1,3,5,7,9);
    public static void main(String[] args) {
        int useMoney = 0;
        int winMoney = 540;
        int rate = 1;
        for (int i = 0; i < 10000000; i++) {
            useMoney += 2 * rate;
            winMoney = 540 * rate;
            if(winMoney < useMoney){
                System.out.println("第" + i + "期开始加倍");
                rate += 1;
            }
            List<Integer> winList = winNum();
            boolean win = true;
            for (Integer buyNum : buyList) {
                if(!winList.contains(buyNum)){
                    win = false;
                }
            }
            if(win){
                System.out.println("第" + i + "次购买,中奖号码为:" +  winList + "花了" + useMoney + "钱,赢了" + winMoney +  "钱");
                break;
            }
        }
    }

    public static List<Integer> winNum() {
        List<Integer> numList = new ArrayList<>();
        Random random = new Random();
        while (numList.size() < 5) {
            int i = random.nextInt(12);
            if (!numList.contains(i)) {
                numList.add(i);
            }
        }
        return numList;
    }
}
