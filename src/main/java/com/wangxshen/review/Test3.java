package com.wangxshen.review;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author WangShen
 * @Date 2020/12/15 11:04
 * @Version 1.0
 */
public class Test3 {
    /**
     * @Author:   on2020-12-15 11:04:45
     * @Param: null
     * @return:
     * description: 快排
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length-1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] part = partition(arr, L, R);
        if (part != null) {
            process(arr, L, part[0]-1);
            process(arr, part[1]+1, R);
        }
    }

    public static int[] partition(int[] arr, int L, int R) {
        if (arr.length == 1) {
            return new int[]{L,R};
        }

        int radix = arr[R];
        int lp = L-1;
        int rp = R;
        int i = L;
        while (i < rp) {
            if (arr[i] < radix) {
                swap(arr, i++, ++lp);
            } else if (arr[i] > radix) {
                swap(arr, i, --rp);
            } else {
                i++;
            }
        }
        swap(arr, i, R);
        return new int[] {lp+1, rp-1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void test() {
        int size = 10;
        int range = 100;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random() * range);
        }
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
