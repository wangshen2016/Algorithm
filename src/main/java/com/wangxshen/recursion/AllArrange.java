package com.wangxshen.recursion;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @Author WangShen
 * @Date 2020/12/10 14:13
 * @Version 1.0
 */
public class AllArrange {
    /**
     * @Author:   on2020-12-10 14:58:28
     * @Param: null
     * @return:
     * description: 获取一个字符串的全排列
     */
    public static void printAllArrange(String str) {
        if (str == null || str == "") {
            return;
        }
        ArrayList<String> ans = new ArrayList<>();
        process(str.toCharArray(), 0, ans);
        System.out.println(ans);
    }

    public static void process(char[] str, int i, ArrayList<String> ans) {
        if (i == str.length) {
            ans.add(String.valueOf(str));
        }
        for (int j = i; j < str.length; j++) {
            swap(str, i, j);
            process(str, i+1, ans);
            swap(str, i, j);
        }
    }

    /**
     * @Author:   on2020-12-10 15:40:03
     * @Param: null
     * @return:
     * description: 去重版本
     */
    private void printAllArrangeNoRepeat(String str) {
        if (str == null || str == "") {
            return;
        }
        ArrayList<String> ans = new ArrayList<>();
        processNoRepeat(str.toCharArray(), 0, ans);
        System.out.println(ans);
    }

    public static void processNoRepeat(char[] str, int i, ArrayList<String> ans) {
        if (str.length == i) {
            ans.add(String.valueOf(str));
            return;
        }
        boolean[] used = new boolean[26];
        for (int j = i; j < str.length; j++) {
            if (!used[str[j]-'a']) {
                used[str[j]-'a'] = true;
                swap(str, i, j);
                processNoRepeat(str, i+1, ans);
                swap(str, i, j);
            }
        }
    }

    public static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    @Test
    public void test() {
        String str = "aabb";
        printAllArrange(str);
        System.out.println("---------------");
        printAllArrangeNoRepeat(str);
    }


}
