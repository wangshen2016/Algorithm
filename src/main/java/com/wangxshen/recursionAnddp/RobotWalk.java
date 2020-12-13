package com.wangxshen.recursionAnddp;

/**
 * @Author WangShen
 * @Date 2020/12/13 20:27
 * @Version 1.0
 */
public class RobotWalk {
    /**
     * @Author:   on2020-12-13 20:27:34
     * @Param: null
     * @return:
     * description: 给出四个参数N、M、P、K
     * N：有1~N个连续位置
     * M：当前机器人所在位置
     * P：机器人要去的位置
     * K：需要走几步（机器人必须走K步到达P）
     * 当机器人来到1位置，只能往2位置走
     * 当机器人来到N位置，只能往N-1位置走
     * 当机器人在2~N-1位置时，可以往两边走
     * 问：共有几种walk方案
     */
    public static int solution(int N, int M, int P, int K) {
        if (N <= 0 || M <= 0 || M > N || P <= 0 || P > N || K <= 0) {
            return 0;
        }
        return walk(N, M, P, K);
    }

    public static int walk(int N, int cur, int target, int rest) {
        if (rest == 0 ) {
            return cur == target ? 1 : 0;
        }
        if (cur == 1) {
            return walk(N, 2, target, rest-1);
        }
        if (cur == N) {
            return walk(N, N-1, target, rest-1);
        }

        return walk(N, cur-1, target, rest-1) + walk(N, cur+1, target, rest-1);
    }

    /**
     * @Author:   on2020-12-13 20:51:39
     * @Param: null
     * @return:
     * description: 使用记忆缓存优化算法
     */
    public static int solution2(int N, int M, int P, int K) {
        if (N <= 0 || M <= 0 || M > N || P <= 0 || P > N || K <= 0) {
            return 0;
        }
        int[][] dp = new int[N+1][K+1];
        for (int row = 0; row <= N; row++) {
            for (int col = 0; col <= K; col ++) {
                dp[row][col] = -1;
            }
        }
        return walkWithCache(N, M, P, K, dp);
    }

    public static int walkWithCache(int N, int cur, int target, int rest, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        if (rest == 0 ) {
            dp[cur][rest] = cur == target ? 1 : 0;
            return dp[cur][rest];
        }
        if (cur == 1) {
            dp[cur][rest] = walk(N, 2, target, rest-1);
            return dp[cur][rest];
        }
        if (cur == N) {
            dp[cur][rest] = walk(N, N-1, target, rest-1);
            return dp[cur][rest];
        }
        dp[cur][rest] = walk(N, cur-1, target, rest-1)
                + walk(N, cur+1, target, rest-1);
        return dp[cur][rest];
    }

    /**
     * @Author:   on2020-12-13 21:12:55
     * @Param: null
     * @return:
     * description: 动态规划解法，由上述递归解法归纳出dp解法
     */
    public static int solution3(int N, int M, int P, int K) {
        if (N <= 0 || M <= 0 || M > N || P <= 0 || P > N || K <= 0) {
            return 0;
        }
        int[][] dp = new int[N+1][K+1];

        //初始化第一列
        dp[M][0] = 1;

        //依次求值
        for (int col = 1; col <= K; col++) {
            for (int row = 1; row <= N; row++) {
                if (row == 1) {
                    dp[row][col] = dp[row+1][col-1];
                } else if (row == N) {
                    dp[row][col] = dp[row-1][col-1];
                } else {
                    dp[row][col] = dp[row+1][col-1] + dp[row-1][col-1];
                }
            }
        }
        return dp[M][K];
    }
}
