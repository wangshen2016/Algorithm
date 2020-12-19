package com.wangxshen.recursionAnddp;


/**
 * @Author WangShen
 * @Date 2020/12/15 19:34
 * @Version 1.0
 */
public class CoffeeCup {
    /**
     * @Author:   on2020-12-15 19:34:53
     * @Param: int[] drinks：喝完咖啡的时间点（咖啡杯可以洗的时间）
     *         int a：用咖啡机洗杯子用时
     *         int b：自然风干用时
     *
     * @return:
     * description: 洗咖啡杯问题
     * 可以选择用咖啡机洗杯子，但是只有一个咖啡机，每次只能洗一个杯子；
     * 可以选择自然风干杯子，可以并行的风干；
     * 问：返回所有咖啡杯洗干净的最短用时
     */
    public static int getMinTime(int[] drinks, int a, int b) {
        if (drinks == null || drinks.length == 0) {
            return -1;
        }
        if (b < a) {
            return drinks[drinks.length-1] + b;
        }
        return process(drinks, a, b, 0, 0);
    }

    public static int process(int[] drinks, int a, int b, int i, int washline) {
        //洗最后一杯
        if (i == drinks.length - 1) {
            return Math.min(Math.max(drinks[i], washline) + a,
                    drinks[i] + b);
        }

        //用咖啡机洗当前咖啡杯的时间
        int wash = Math.max(drinks[i], washline) + a;
        //后面的杯子洗干净最早的时间
        int next = process(drinks, a, b, i+1, wash);

        int t1 = Math.max(wash, next);

        int dry = drinks[i] + b;
        next = process(drinks, a, b, i+1, washline);

        int t2 = Math.max(dry, next);

        return Math.min(t1, t2);
    }

    /**
     * @Author:   on2020-12-15 19:59:37
     * @Param: null
     * @return:
     * description: 动态规划
     */
    public static int dp_solution(int[] drinks, int a, int b) {
        if (drinks == null || drinks.length == 0) {
            return -1;
        }
        if (b < a) {
            return drinks[drinks.length-1] + b;
        }

        int N = drinks.length;
        int limit = 0;
        for (int i = 0; i < N; i++) {
            limit = Math.max(drinks[i], limit) + a;
        }

        int[][] dp = new int[N][limit+1];

        for (int washline = 0; washline <= limit; washline++) {
            dp[N-1][washline] = Math.min(Math.max(drinks[N-1], washline) + a,
                    drinks[N-1] + b);
        }

        for (int i = N-2; i >= 0; i--) {
            for (int washline = 0; washline <= limit; washline++) {

                int t1 = Integer.MAX_VALUE;
                int wash = Math.max(drinks[i], washline) + a;
                if (wash <= limit) {
                    t1 = Math.max(wash, dp[i+1][wash]);
                }

                int dry = drinks[i] + b;
                int t2 = Math.max(dry, dp[i+1][washline]);

                dp[i][washline] = Math.min(t1, t2);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] test = { 1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15 };
        int a1 = 3;
        int b1 = 10;
        System.out.println(getMinTime(test, a1, b1));
        System.out.println(dp_solution(test, a1, b1));

    }
}
