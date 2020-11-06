package com.hsm.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: WinMoneu
 * @description:
 * 调整 guessNums,以及play11choose5方法的两个参数即可得出不同的中奖模拟结果
 * @date 2020/11/6 10:36
 */
public class WinMoney {

    private static List<Integer> guessNums = Arrays.asList(2, 4, 6, 8, 10);

    public static void main(String[] args) {
        //tryNum(100);
        play11choose5(1,540);
    }

    /**
     *
     * @param rate 倍率 默认是1,从1倍开始卖
     * @param originlWinPrice 1倍赢的价格
     */
    public static void play11choose5(int rate, int originlWinPrice) {
        //支付总金额
        int useTotalMoney = 0;
        //中奖之后赢的钱
        int winPrice = originlWinPrice * rate;
        for (int i = 0; true; i++) {
            //如果你非要赢很多钱,可以将winPrice加上你想赢得钱
            while (useTotalMoney + 2 * rate >= winPrice) {
                rate += 1;
                winPrice = originlWinPrice * rate;
            }
            useTotalMoney += (2 * rate);
            //支付的钱如果比总金额大了,那肯定要翻倍了

            //花了钱就买一笔
            List<Integer> winNums = getWinNums();
            if (isWin(guessNums, winNums)) {
                System.out.println(String.format("第%s期中奖,总共花了%s钱,最后赢了%s钱,实际赚了%s钱", i, useTotalMoney, winPrice, winPrice - useTotalMoney));
                //尝试去掉break,可以打印很多期的
                break;
            }
        }

    }


    /**
     * 传入期数,中奖模拟
     *
     * @param num
     */
    public static void tryNum(int num) {
        for (int i = 0; i < num; i++) {
            List<Integer> winNums = getWinNums();
            if (isWin(guessNums, winNums)) {
                System.out.println(String.format("第%s期中奖,中间号码为%s", i, winNums));
            }
        }
    }

    /**
     * 是否中奖判断
     *
     * @param guessNums
     * @param winNums
     * @return
     */
    public static boolean isWin(List<Integer> guessNums, List<Integer> winNums) {
        for (Integer guessNum : guessNums) {
            if (!winNums.contains(guessNum)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取中奖号码
     *
     * @return
     */
    public static List<Integer> getWinNums() {
        List<Integer> winNums = new ArrayList<>();
        Random random = new Random();
        while (winNums.size() < 5) {
            int winNum = random.nextInt(11) + 1;
            if (!winNums.contains(winNum)) {
                winNums.add(winNum);
            }
        }
        return winNums;
    }
}
