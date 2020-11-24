package com.wangxshen.sort2_simple;

/**
 * @Author WangShen
 * @Date 2020/11/21 16:45
 * @Version 1.0
 */
public class QuickSort {
    public static void sort(int[] arr) {
        process(arr, 0, arr.length-1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        Util.swap(arr, R, L + (int)(Math.random()*(R-L)));
        int[] partition = partition(arr, L, R);
        process(arr, L, partition[0]-1);
        process(arr, partition[1]+1, R);
    }

    public static int[] partition(int[] arr, int L, int R) {
        if (arr.length < 0 || L > R || L < 0) {
            return null;
        } else if (arr.length == 1) {
            return new int[] {L, R};
        }
        int LP = L-1;
        int RP = R;
        int seed = R;
        int cur = L;

        while (cur < RP) {
            if (arr[cur] < arr[seed]) {
                Util.swap(arr, ++LP, cur++);
            } else if (arr[cur] > arr[seed]) {
                Util.swap(arr, cur, --RP);
            } else {
                cur++;
            }
        }
        Util.swap(arr, cur, R);
        return new int[]{LP + 1, RP - 1};
    }
}
