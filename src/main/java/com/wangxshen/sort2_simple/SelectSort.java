package com.wangxshen.sort2_simple;

/**
 * @Author WangShen
 * @Date 2020/11/21 16:09
 * @Version 1.0
 */
public class SelectSort {
    public static void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            min = i;
            for (int j = i+1; j < arr.length; j++) {
                min = arr[j] < arr[min] ? j : min;
            }
            Util.swap(arr, min, i);
        }
    }
}
