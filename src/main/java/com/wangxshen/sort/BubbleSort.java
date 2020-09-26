package com.wangxshen.sort;

import java.util.Arrays;

/**
 * @Author WangShen
 * @Date 2020/9/26 10:48
 * @Version 1.0
 */
public class BubbleSort {

    public static void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        }

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {8, 3, 2, 1, 0, 8, 29, 3};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
