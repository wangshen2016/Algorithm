package com.wangxshen.recursionAnddp;

import org.junit.Test;

/**
 * @Author WangShen
 * @Date 2020/12/17 20:07
 * @Version 1.0
 */
public class HorseInChess {
    /**
     * @Author:   on2020-12-17 20:07:57
     * @Param: int width(10); int height(9); int x; int y; int k;
     * @return:
     * description:  给出一个棋盘 10 * 9；
     * 马走日，从(0,0)出发，要求必须走k步，到达(x,y)，
     * 问：一共有几种走法
     */
    public static int solution0(int width, int height, int x, int y, int k) {
        return process(width, height, x, y, k);
    }

    public static int process(int w, int h, int x, int y, int rest) {
        if (rest == 0) {
            return x == 0 && y == 0 ? 1 : 0;
        }
        if (!check(w, h, x, y)) {
            return 0;
        }

        rest--;
        return process(w, h, x-1, y+2, rest)
                + process(w, h, x-2, y+1, rest)
                + process(w, h, x-2, y-1, rest)
                + process(w, h, x-1, y-2, rest)
                + process(w, h, x+1, y-2, rest)
                + process(w, h, x+2, y-1, rest)
                + process(w, h, x+2, y+1, rest)
                + process(w, h, x+1, y+2, rest);


    }

    public static boolean check(int w, int h, int x, int y) {
        if (x < 0 || y < 0 || x >= w || y >= h) {
            return false;
        }
        return true;
    }

    /**
     * @Author:   on2020-12-19 16:33:15
     * @Param: null
     * @return:
     * description: 动态规划解法
     */

    public static int dp(int width, int height, int x, int y, int k) {
        int[][][] dp = new int[width][height][k+1];
        dp[0][0][0] = 1;

        for (int m = 1; m <= k; m++) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    dp[i][j][m] = getValue(dp, width, height, i-1, j+2, m-1)
                            + getValue(dp, width, height, i-2, j+1, m-1)
                            + getValue(dp, width, height, i-2, j-1, m-1)
                            + getValue(dp, width, height, i-1, j-2, m-1)
                            + getValue(dp, width, height, i+1, j-2, m-1)
                            + getValue(dp, width, height, i+2, j-1, m-1)
                            + getValue(dp, width, height, i+2, j+1, m-1)
                            + getValue(dp, width, height, i+1, j+2, m-1);
                }
            }
        }
        return dp[x][y][k];
    }

    public static int getValue(int[][][] dp, int W, int H, int x, int y, int k) {
        if (x < 0 || y < 0 || x >= W || y >= H) {
            return 0;
        }
        return dp[x][y][k];
    }

    @Test
    public void test() {
        int W = 10;
        int H = 9;

        int x = 2;
        int y = 3;
        int k = 3;
        System.out.println(solution0(W, H, x, y, k));
        System.out.println(dp(W, H, x, y, k));
    }
}
