package com.wangxshen.recursionAnddp;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author WangShen
 * @Date 2020/12/14 11:20
 * @Version 1.0
 */
public class CoinsWay {
    /**
     * @Author:   on2020-12-14 11:20:37
     * @Param: null
     * @return:
     * description: 给出数组arr，代表无重复任意正数面值的货币集合，
     * 给出aim代表要达到的总价值，
     * 要求返回 使用arr中任意张任意面值的货币 达成aim价值 的方法有多少种
     */
    public static int solution(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int i, int rest) {

        if (i == arr.length) {
            return rest == 0 ? 1:0;
        }

        int count = 0;
        for (int num = 0; num * arr[i] <= rest; num++) {
            count += process(arr, i+1, rest - num * arr[i]);
        }

        return count;
    }

    /**
     * @Author:   on2020-12-14 14:11:26
     * @Param: null
     * @return:
     * description: 记忆性搜索优化解法
     */
    public static int solution_dp(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        int[][] dp = new int[arr.length+1][aim+1];
        for (int i = 0; i < arr.length+1; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        int ret = dp(arr, 0, aim, dp);
        return ret;
    }

    public static int dp(int[] arr, int i, int rest, int[][] dp) {

        if (dp[i][rest] != -1) {
            return dp[i][rest];
        }
        if (i == arr.length) {
            dp[i][rest] = rest == 0 ? 1 : 0;
            return dp[i][rest];
        }

        dp[i][rest] = 0;
        for (int num = 0; num * arr[i] <= rest; num++) {
            dp[i][rest] += dp(arr, i+1, rest - (num * arr[i]), dp);
        }

        return dp[i][rest];
    }

    /**
     * @Author:   on2020-12-14 16:07:20
     * @Param: null
     * @return:
     * description: 经典动态规划
     */
    public static int dp0(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];

        dp[N][0] = 1;

        for (int i = N-1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = 0;
                for (int num = 0; num * arr[i] <= j; num++) {
                    dp[i][j] += dp[i+1][j - num * arr[i]];
                }
            }
        }

        return dp[0][aim];
    }

    /**
     * @Author:   on2020-12-14 16:35:47
     * @Param: null
     * @return:
     * description: 优化重复累加后的解决方案
     */
    public static int dp1(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 1;

        for (int i = N-1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i+1][rest];
                if (rest - arr[i] >= 0) {
                    dp[i][rest] += dp[i][rest - arr[i]];
                }
            }
        }
        return dp[0][aim];
    }

    @Test
    public void test() {
        int aim = 1000;
        int[] arr = new int[] {10,5,50,100};
        System.out.println(solution(arr, aim));
        System.out.println(solution_dp(arr, aim));
        System.out.println(dp0(arr, aim));
        System.out.println(dp1(arr, aim));
    }

}
