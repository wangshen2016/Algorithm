package com.wangxshen.sort.quickSort;

import java.util.Arrays;

/**
 * @Author WangShen
 * @Date 2020/9/26 15:27
 * @Version 1.0
 */
public class QuickSort {
    /**
     * @Author:   on2020-09-26 15:27:08
     * @Param: null
     * @return:
     * description: 利用荷兰国旗问题
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
//        process(arr, 0, arr.length - 1);
        process2(arr, 0, arr.length - 1);
    }

    /**
     * @Author:   on2020-09-26 15:52:52
     * @Param: null
     * @return:
     * description: 快排V2.0版本，V1.0版本更简单所以不予实现
     */
    public static void process(int arr[], int L, int R) {
        if (L >= R) {
            return;
        }
        int[] border = Partition.partition(arr, L, R);
        process(arr, L, border[0] - 1);
        process(arr, border[1] + 1, R);
    }

    /**
     * @Author:   on2020-09-26 15:54:01
     * @Param: null
     * @return:
     * description: 经典随机快排，V3.0， 使用随机基准值
     */
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //使用数组中的随机位置作为基准值，然后将基准值放在最右边，然后就变成了快排V2.0
        Partition.swap(arr, L + (int)Math.random() * (R - L + 1), R);
        int[] border = Partition.partition(arr, L, R);
        process(arr, L, border[0] - 1);
        process(arr, border[1] + 1, R);
    }
}
