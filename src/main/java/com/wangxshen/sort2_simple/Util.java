package com.wangxshen.sort2_simple;

/**
 * @Author WangShen
 * @Date 2020/11/21 15:51
 * @Version 1.0
 */
public class Util {
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
