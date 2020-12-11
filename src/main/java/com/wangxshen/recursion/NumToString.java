package com.wangxshen.recursion;

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

    @Test
    public void test() {
        String str = "1111";
        printNumToString(str);
    }
}
