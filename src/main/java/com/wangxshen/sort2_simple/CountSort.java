package com.wangxshen.sort2_simple;

/**
 * @Author WangShen
 * @Date 2020/11/21 18:00
 * @Version 1.0
 */
public class CountSort {
    public static void sort(int[] arr) {
        int[] bucket = new int[arr.length];
        for (int i : arr) {
            bucket[i]++;
        }
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while(bucket[i] > 0) {
                arr[index++] = i;
                bucket[i]--;
            }
        }
    }
}
