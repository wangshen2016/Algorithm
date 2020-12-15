package com.wangxshen.review;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author WangShen
 * @Date 2020/12/15 11:50
 * @Version 1.0
 */
public class Test4 {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length-1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process(arr, L, M);
        process(arr, M+1, R);
        merge(arr, L, M, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R-L+1];
        int index = 0;
        int lp = L;
        int rp = M+1;
        while (lp <= M && rp <= R) {
            help[index++] = arr[lp] < arr[rp] ? arr[lp++] : arr[rp++];
        }
        while (lp <= M) {
            help[index++] = arr[lp++];
        }
        while (rp <= R) {
            help[index++] = arr[rp++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    @Test
    public void test() {
        int size = 30;
        int range = 100;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random() * range);
        }
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
