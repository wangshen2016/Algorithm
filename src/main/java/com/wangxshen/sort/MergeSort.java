package com.wangxshen.sort;

import java.util.Arrays;

/**
 * @Author WangShen
 * @Date 2020/9/26 10:12
 * @Version 1.0
 */
public class MergeSort {

    /**
     * @Author:   on2020-09-26 11:24:46
     * @Param: null
     * @return:
     * description: 递归算法
     */
    public static void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        } else if(arr.length == 2) {
            if (arr[0] <= arr[1]) {
                return;
            } else {
                int tmp = arr[1];
                arr[1] = arr[0];
                arr[0] = tmp;
                return;
            }
        } else {
            process(arr, 0, arr.length - 1);
        }
    }

    public static void process(int arr[], int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int arr[], int L, int mid, int R) {
        int[] help = new int[R-L+1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while(p2 <= R) {
            help[i++] = arr[p2++];
        }
        for(i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    /**
     * @Author:   on2020-09-26 11:28:54
     * @Param: null
     * @return:
     * description: 非递归算法
     */
    public static void sort2(int[] arr) {
        if (arr.length < 2) {
            return;
        } else if(arr.length == 2) {
            if (arr[0] <= arr[1]) {
                return;
            } else {
                int tmp = arr[1];
                arr[1] = arr[0];
                arr[0] = tmp;
                return;
            }
        }
        int mergeSize = 1;
        while (mergeSize < arr.length) {
            int L = 0;
            while (L < arr.length) {
                int M = L + mergeSize - 1;
                if (M >= arr.length) {
                    break;
                }
                int R = Math.min(M + mergeSize, arr.length - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            /*
            * 防止mergeSize超过int的最大值，导致的溢出
            * */
            if (mergeSize > arr.length/2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    /**
     * @Author:   on2020-09-26 12:25:18
     * @Param: null
     * @return:
     * description: 求数组最小和问题
     */
    public static int minSum(int[] arr) {
        if (arr.length < 2) {
            return 0;
        } else if(arr.length == 2) {
            if (arr[0] < arr[1]) {
                return arr[0];
            } else {
                int tmp = arr[1];
                arr[1] = arr[0];
                arr[0] = tmp;
                return 0;
            }
        }
        return process2(arr, 0, arr.length - 1);
    }

    public static int process2(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return process2(arr, L, M) + process2(arr, M+1, R) + merge2(arr, L, M, R);
    }

    public static int merge2(int[] arr, int L, int M, int R) {
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        int[] help = new int[R-L+1];
        int i = 0;
        while (p1 <= M && p2 <= R) {
            res += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

}
