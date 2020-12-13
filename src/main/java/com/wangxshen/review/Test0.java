package com.wangxshen.review;

/**
 * @Author WangShen
 * @Date 2020/12/13 14:37
 * @Version 1.0
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.OptionalInt;

/**
 * 暴力递归转动态规划自测练习
* */
public class Test0 {
    /**
     * @Author:   on2020-12-13 14:38:52
     * @Param: null
     * @return:
     * description: 二分法获取最大值，无重复枚举，不需要转动态规划
     */
    public static int getMax(int[] arr) {
        if (arr == null || arr.length < 1) {
            return Integer.MIN_VALUE;
        }
        return process0(arr, 0, arr.length-1);
    }

    public static int process0(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        int mid = i + ((j - i) >> 1);
        int a = process0(arr, i, mid);
        int b = process0(arr, mid+1, j);
        return Math.max(a, b);
    }

    @Test
    public void test0() {
        int seed = 100;
        int size = 20;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random() * seed);
        }
        if (Arrays.stream(arr).max().getAsInt() != getMax(arr)) {
            System.out.println("fail");
            System.out.println(Arrays.toString(arr));
        } else {
            System.out.println("success");
        }
    }

    /**
     * @Author:   on2020-12-13 15:09:32
     * @Param: null
     * @return:
     * description: 背包问题
     */
    public static int getMaxBag(int[] w, int[] v, int bag) {
        return process1(w, v, 0, bag);
    }

    public static int process1(int[] w, int[] v, int i, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (w.length == i) {
            return 0;
        }
        int no = process1(w, v, i + 1, rest);

        int yes = -1;
        int r = process1(w, v, i + 1, rest - w[i]);
        if (r != -1) {
            yes = v[i] + r;
        }

        return Math.max(no, yes);
    }

    @Test
    public void test1() {
        int bag = 0;
        int[] w = new int[]{1,2,3,4,5};
        int[] v = new int[]{5,4,3,2,1};
        System.out.println(getMaxBag(w, v, bag));
    }

    /**
     * @Author:   on2020-12-13 16:10:24
     * @Param: null
     * @return:
     * description: 打印字符串所有子序列
     */
    public static void printSubString(String str) {
        if (str == null) {
            return;
        }
        ArrayList<String> ans = new ArrayList<>();
        process2(str.toCharArray(), 0, "", ans);
        System.out.println(ans);
    }

    public static void process2(char[] chars, int i, String path, ArrayList<String> ans) {
        if (i == chars.length) {
            ans.add(path);
            return;
        }
        process2(chars, i+1, path, ans);
        process2(chars, i+1, path + String.valueOf(chars[i]), ans);
    }

    @Test
    public void test02() {
        String str = "abc";
        printSubString(str);
    }

    /**
     * @Author:   on2020-12-13 16:33:47
     * @Param: null
     * @return:
     * description: 打印一个字符串的全部排列
     */

    public static void printAllRange(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        ArrayList<String> ans = new ArrayList<>();
        process4(str.toCharArray(), 0, ans);
        System.out.println(ans);
    }

    public static void process4(char[] chars, int i, ArrayList<String> ans) {
        if (i == chars.length) {
            ans.add(String.valueOf(chars));
            return;
        }
        boolean[] visit = new boolean[26];
        for (int j = i; j < chars.length; j++) {
            if (!visit[chars[j] - 'a']) {
                visit[chars[j] - 'a'] = true;
                swap(chars, i, j);
                process4(chars, i+1, ans);
                swap(chars, i, j);
            }
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void test04() {
        String str = "abcc";
        printAllRange(str);
    }

    /**
     * @Author:   on2020-12-13 17:51:19
     * @Param: null
     * @return:
     * description: 数字转字符
     */
    public static void convert(String str) {
        if (str == null) {
            return;
        }
        ArrayList<String> ans = new ArrayList<>();
        int ret = process05(str.toCharArray(), 0, "", ans);
        System.out.println(ans);
        System.out.println("N: " + ret);
    }

    public static int process05(char[] chars, int i, String path, ArrayList<String> ans) {
        if (i == chars.length) {
            ans.add(path);
            return 1;
        }

        if (chars[i] == '0') {
            return 0;
        }

        if (chars[i] == '1') {
            //i独自转换
            int ret = process05(chars, i+1, path + String.valueOf('A'), ans);

            //i和i+1组合
            int b = 0;
            if (i + 1 < chars.length) {
                Integer num = Integer.parseInt(String.valueOf(chars[i])+ String.valueOf(chars[i+1])) - 1;
                ret += process05(chars, i+2, path + String.valueOf((char)('A' + num)), ans);
            }
            return ret;
        }

        if (chars[i] == '2') {
            //i独自转换
            int ret = process05(chars, i+1, path + String.valueOf('B'), ans);

            //i和i+1组合
            if (i + 1 < chars.length && chars[i] >= '0' && chars[i+1] <= '6') {
                Integer num = Integer.parseInt(String.valueOf(chars[i])+ String.valueOf(chars[i+1])) - 1;
                ret = process05(chars, i+2, path + String.valueOf((char)('A' + num)), ans);
            }
            return ret;
        }
        Integer num = Integer.parseInt(String.valueOf(chars[i])) - 1;
        return process05(chars, i+1, path + String.valueOf((char)('A' + num)), ans);

    }

    @Test
    public void test05() {
        String str = "112310";
        convert(str);
    }

    /**
     * @Author:   on2020-12-13 18:44:49
     * @Param: null
     * @return:
     * description: 左右拿纸牌问题
     */

    public static int getMaxPoint(int[] arr) {
        if (arr == null) {
            return -1;
        }
        return Math.max(first(arr, 0, arr.length-1), second(arr, 0, arr.length-1));
    }

    //先手
    public static int first(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        return Math.max(arr[L] + second(arr, L+1, R),
                arr[R] + second(arr, L, R-1));
    }

    //后手
    public static int second(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(first(arr, L+1, R),
                first(arr, L, R-1));
    }

    @Test
    public void test06() {
        int[] arr = new int[] {3, 100, 7};
        System.out.println(getMaxPoint(arr));
    }

}
