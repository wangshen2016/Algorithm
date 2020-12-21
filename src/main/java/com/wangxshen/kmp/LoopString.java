package com.wangxshen.kmp;

import org.junit.Test;

/**
 * @Author WangShen
 * @Date 2020/12/21 15:40
 * @Version 1.0
 */
public class LoopString {
    /**
     * @Author:   on2020-12-21 15:40:49
     * @Param: String str1, String str2
     * @return: boolean isLoop?
     * description: 环回字符串问题
     * eg: str1: 123456, 其环回字符串为 234561, 345612, 456123, 561234, 612345
     * 返回str2是否为str1的环回字符串
     */
    public static boolean isLoop(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        str1 += str1;
        return kmp(str1, str2);
    }

    public static boolean kmp(String a, String b) {
        char[] source = a.toCharArray();
        char[] matcher = b.toCharArray();
        int[] next = getNext(matcher);

        int i = 0;
        int j = 0;
        while (i < source.length && j < matcher.length) {
            if (source[i] == matcher[j]) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == matcher.length ? true : false;
    }

    public static int[] getNext(char[] arr) {
        int[] next = new int[arr.length];
        next[0] = -1;
        next[1] = 0;

        int i = 2;
        int ch = 0;
        while (i < arr.length) {
            if (arr[i-1] == arr[ch]) {
                next[i++] = ++ch;
            } else if (ch == 0) {
                next[i++] = 0;
            } else {
                ch = next[ch];
            }
        }
        return next;
    }

    @Test
    public void test() {
        String str = "123456";
        String[] arr = new String[] {
                "234561",
                "345612",
                "456123",
                "561234",
                "612345",
                "234516"
        };
        for (int i = 0; i < arr.length; i++) {
            System.out.println(isLoop(str, arr[i]));
        }
    }
}
