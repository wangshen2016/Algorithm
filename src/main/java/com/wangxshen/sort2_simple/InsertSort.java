package com.wangxshen.sort2_simple;

/**
 * @Author WangShen
 * @Date 2020/11/21 16:03
 * @Version 1.0
 */
public class InsertSort {
    public static void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        }

        for(int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    Util.swap(arr, j, j-1);
                } else {
                    break;
                }
            }
        }
    }
}
