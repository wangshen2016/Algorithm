package com.wangxshen.sort;

import java.util.Arrays;

/**
 * @Author WangShen
 * @Date 2020/9/26 10:54
 * @Version 1.0
 */
public class SelectSort {

    public static void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length-1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[] {8, 3, 2, 1, 0, 8, 29, 3};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
