package com.wangxshen.sort2_simple;

/**
 * @Author WangShen
 * @Date 2020/11/21 15:50
 * @Version 1.0
 */
public class BubbleSort {
    public static void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        for (int i = arr.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    Util.swap(arr, j, j+1);
                }
            }
        }
    }
}
