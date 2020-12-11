package com.wangxshen.recursion;

import org.junit.Test;

/**
 * @Author WangShen
 * @Date 2020/12/10 19:55
 * @Version 1.0
 */
public class Bag {
    /**
     * @Author:   on2020-12-10 19:56:19
     * @Param: null
     * @return:
     * description:
     * weight数组,weight[i]代表i商品的重量
     * value数组,value[i]代表i商品的价值
     * bag代表背包负重
     * 要求返回在不超过背包负重的情况下，能装入的最多价值的货物的价值
     */
    public static int solution(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag);
    }

    public static int process(int[] w, int[] v, int i, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (i == w.length) {
            return 0;
        }
        int no = process(w, v, i+1, rest);
        int r = process(w, v, i+1, rest - w[i]);
        int yes = -1;
        if (r != -1) {
            yes = r + v[i];
        }
        return Math.max(yes, no);
    }

    @Test
    public void test() {
        int bag = 10;
        int[] w = new int[]{1,2,3,4,5};
        int[] v = new int[]{5,4,3,2,1};
        System.out.println(solution(w, v, bag));
    }
}
