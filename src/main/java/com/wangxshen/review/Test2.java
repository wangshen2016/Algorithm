package com.wangxshen.review;

import java.util.Arrays;

/**
 * @Author WangShen
 * @Date 2020/12/14 21:32
 * @Version 1.0
 */
public class Test2 {
    public static void heapSort(int[] arr) {
        for (int i = arr.length-1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int index = arr.length;
        swap(arr, 0, --index);

        while (index > 0) {
            heapify(arr, 0, index);
            swap(arr, 0, --index);
        }
    }

    public static void heapify(int[] arr, int index, int size) {
        int lchild = 2 * index + 1;
        while (lchild < size && arr[index] < arr[lchild]) {
            int max = lchild + 1 < size && arr[lchild+1] > arr[lchild] ? lchild+1 : lchild;
            swap(arr, max, index);
            index = max;
            lchild = 2 * index + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int size = 10;
        int range = 100;
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * range);
        }

        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
