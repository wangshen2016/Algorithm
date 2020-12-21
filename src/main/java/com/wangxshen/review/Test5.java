package com.wangxshen.review;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author WangShen
 * @Date 2020/12/21 10:52
 * @Version 1.0
 */
public class Test5 {

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
        process(arr, L, part[0]-1);
        process(arr, part[1]+1, R);
    }

    public static int[] partition(int[] arr, int L, int R) {
        if (arr.length == 1) {
            return new int[] {L, R};
        }
        int lp = L-1;
        int rp = R;
        int index = L;

        while (index < rp) {
            if (arr[index] < arr[R]) {
                swap(arr, ++lp, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --rp);
            } else {
                index++;
            }
        }
        swap(arr, index, R);
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
        int seed = 100;
        int loop = 1000;
        for (int i = 0; i < loop; i++) {
            int[] a = getRandomArray(size, seed);
            int[] b = Arrays.copyOf(a, a.length);
            Arrays.sort(a);
            sort(b);
            for (int j = 0; j < size; j++) {
                if (a[j] != b[j]) {
                    System.out.println("test fail");
                    return;
                }
            }
        }
        System.out.println("success");
    }

    public static int[] getRandomArray(int size, int seed) {
        int[] ret = new int[size];

        for (int i = 0; i < size; i++) {
            ret[i] = (int)(Math.random() * seed);
        }

        return ret;
    }
}
