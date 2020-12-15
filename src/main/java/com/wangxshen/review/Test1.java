package com.wangxshen.review;

import org.junit.Test;

/**
 * @Author WangShen
 * @Date 2020/12/13 19:35
 * @Version 1.0
 */
public class Test1 {
    /**
     * @Author:   on2020-12-15 10:52:11
     * @Param: null
     * @return:
     * description: 暴力递归背包问题
     */
    public static int solution0(int[] w, int[] v, int bag) {
        return process0(w, v, 0, bag);
    }

    public static int process0(int[] w, int[] v, int i, int rest) {
        if (rest < 0) {
            return -1;
        }

        if (i == w.length) {
            return 0;
        }

        int no = process0(w, v, i+1, rest);
        int yes = Integer.MIN_VALUE;
        int r = process0(w, v, i+1, rest-w[i]);
        if (r != -1) {
            yes = r + v[i];
        }
        return Math.max(no, yes);
    }

    /**
     * @Author:   on2020-12-15 10:56:36
     * @Param: null
     * @return:
     * description: 动态规划解法
     * */
    public static int solution1(int[] w, int[] v, int bag) {
        return process1(w, v, bag);
    }

    public static int process1(int[] w, int[] v, int bag) {
        int[][] dp = new int[w.length+1][bag+1];

        for (int i = w.length-1; i >= 0; i--) {
            for (int j = 0; j <= bag; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = w.length-1; i >= 0; i--) {
            for (int rest = 0; rest <= bag; rest++) {
                int no = dp[i+1][rest];
                int yes = Integer.MIN_VALUE;
                if (rest >= w[i]) {
                    yes = v[i] + dp[i+1][rest-w[i]];
                }
                dp[i][rest] = Math.max(yes, no);
            }
        }
        return dp[0][bag];
    }

    @Test
    public void test() {
        int bag = 10;
        int[] w = new int[]{1,2,3,4,5};
        int[] v = new int[]{5,4,3,2,1};
        System.out.println(solution0(w, v, bag));
        System.out.println(solution1(w, v, bag));
    }

}
