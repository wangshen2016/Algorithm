package com.wangxshen.recursionAnddp;

/**
 * @Author WangShen
 * @Date 2020/12/15 16:52
 * @Version 1.0
 */
public class LongestCommonSubString {
    /**
     * @Author:   on2020-12-15 16:52:14
     * @Param: null
     * @return:
     * description: 最长公共子序列的长度
     */
    public static int solution(String a, String b) {
        if (a == null || b == null || a.length() < 1 || b.length() < 1) {
            return 0;
        }
        int[][] dp = new int[a.length()][b.length()];

        char[] aix0 = a.toCharArray();
        char[] aix1 = b.toCharArray();

        //填充第一列
        int tmp = 0;
        for (int i = 0; i < a.length(); i++) {
            if (aix0[i] == aix1[0]) {
                tmp = 1;
            }
            dp[i][0] = tmp;
        }
        //填充第一行
        tmp = 0;
        for (int i = 0; i < b.length(); i++) {
            if (aix1[i] == aix0[0]) {
                tmp = 1;
            }
            dp[0][i] = tmp;
        }

        for (int row = 1; row < a.length(); row++) {
            for (int col = 1; col < b.length(); col++) {
                dp[row][col] = Math.max(dp[row][col-1], dp[row-1][col-1]);
                if (aix0[row] == aix1[col]) {
                    dp[row][col] = Math.max(dp[row-1][col-1] + 1, dp[row][col]);
                }
            }
        }
        return dp[a.length()-1][b.length()-1];
    }
}
