package com.wangxshen.recursionAnddp;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @Author WangShen
 * @Date 2020/12/10 15:46
 * @Version 1.0
 */
public class NumToString {
    /**
     * @Author:   on2020-12-10 15:46:54
     * @Param: null
     * @return:
     * description: 1代表A，2代表B，3代表C......
     * 给出一个数字组成的字符串，将这个字符拆转换成字母组成的字符串，
     * 问有多少种？
     * eg: "111" -> "AAA", "AK", "KA"
     */
    public static void printNumToString(String str) {
        if (str == null || str == "") {
            return;
        }
        ArrayList<String> ans = new ArrayList<>();
        process(str.toCharArray(), 0, ans, "");
        System.out.println(ans);
    }

    public static void process(char[] str, int i, ArrayList<String> ans, String path) {
        if (i == str.length) {
            ans.add(path);
            return;
        }
        if (str[i] == '0') {
            return;
        }
        if (str[i] == '1') {
            int num = Integer.parseInt(String.valueOf(str[i]));
            process(str, i+1, ans, path + String.valueOf((char)('A' + num - 1)));

            if (i + 1 < str.length) {
                num = Integer.parseInt(String.valueOf(str[i]) + String.valueOf(str[i+1]));
                process(str, i+2, ans, path + String.valueOf((char)('A' + num - 1)));
            }
        } else if (str[i] == '2') {
            int num = Integer.parseInt(String.valueOf(str[i]));
            process(str, i+1, ans, path + String.valueOf((char)('A' + num - 1)));

            if (i + 1 < str.length && str[i+1] >= '0' && str[i+1] <= '6') {
                num = Integer.parseInt(String.valueOf(str[i]) + String.valueOf(str[i+1]));
                process(str, i+2, ans, path + String.valueOf((char)('A' + num - 1)));
            }
        } else {
            int num = Integer.parseInt(String.valueOf(str[i]));
            process(str, i+1, ans, path + String.valueOf((char)('A' + num - 1)));
        }
    }

    /**
     * @Author:   on2020-12-13 22:30:07
     * @Param: null
     * @return:
     * description: 只获取转换后有几种的方法
     */
    public static int getCount(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        if (str[i] == '1') {
            int ret = getCount(str, i+1);

            if (i + 1 < str.length) {
                ret += getCount(str, i+2);
            }
            return ret;
        } else if (str[i] == '2') {
            int ret = getCount(str, i+1);

            if (i + 1 < str.length && str[i+1] >= '0' && str[i+1] <= '6') {
                ret += getCount(str, i+2);
            }
            return ret;
        } else {
            return getCount(str, i+1);
        }
    }

    /**
     * @Author:   on2020-12-13 22:33:57
     * @Param: null
     * @return:
     * description: getCount改成动态规划
     */
    public static int dp(String str) {
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length+1];
        dp[chars.length] = 1;
        for (int i = chars.length-1; i >= 0; i--) {
            if (chars[i] == '0') {
                if (i == 0 || chars[i-1] != '1') {
                    //这种情况无法转化，直接返回0
                    return 0;
                } else {
                    // 可以转化，但是dp[i] 和 dp[i-1]要特殊处理，
                    // 跳过后面chars[i] == '1'时的处理流程，否则会重复处理
                    dp[i] = 0;
                    dp[i-1] = dp[i+1];
                    i--;
                    continue;
                }
            }
            if (chars[i] == '1') {
                int ret = dp[i+1];

                if (i + 2 <= chars.length) {
                    ret += dp[i+2];
                }
                dp[i] = ret;
            } else if (chars[i] == '2') {
                int ret = dp[i+1];

                if (i + 2 <= chars.length && chars[i+1] >= '0' && chars[i+1] <= '6') {
                    ret += dp[i+2];
                }
                dp[i] = ret;
            } else {
                dp[i] = dp[i+1];
            }
        }
        return dp[0];
    }

    @Test
    public void test() {
        String str = "112310231110";
        printNumToString(str);
        System.out.println(getCount(str.toCharArray(), 0));
        System.out.println(dp(str));
    }
}
