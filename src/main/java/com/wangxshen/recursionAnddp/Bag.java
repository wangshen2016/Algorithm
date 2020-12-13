package com.wangxshen.recursionAnddp;

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

    public static int dp(int[] w, int[] v, int bag) {
        int[][] dp = new int[w.length + 1][bag + 1];
        //1.  依据递归的base填充初始行（倒数第一行），
        //    即 i == w.length : 0, dp已经初始化，省略dp[w.length][col]=0

        //2. 从倒数第二行开始向上填充dp，下->上，左->右
        for (int i = w.length-1; i >=0; i--) {
            for (int j = 0; j <= bag; j++) {
                //3. int no = process(w, v, i+1, rest);
                int a = dp[i+1][j];
                //4. int r = process(w, v, i+1, rest - w[i]);
                //   这里要判断是否越界
                int b = Integer.MIN_VALUE;
                if (j >= w[i]) {
                    b = dp[i+1][j - w[i]] + v[i];
                }
                dp[i][j] = Math.max(a, b);
            }
        }
        return dp[0][bag];
    }

    @Test
    public void test() {
        int bag = 10;
        int[] w = new int[]{1,2,3,4,5};
        int[] v = new int[]{5,4,3,2,1};
        System.out.println(solution(w, v, bag));
        System.out.println(dp(w, v, bag));
    }
}
