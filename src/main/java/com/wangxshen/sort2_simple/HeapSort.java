package com.wangxshen.sort2_simple;

/**
 * @Author WangShen
 * @Date 2020/11/21 16:14
 * @Version 1.0
 */
public class HeapSort {
    public static void sort(int[] arr, boolean optimize) {
        getHeap(arr, optimize);

        int size = arr.length;
        Util.swap(arr, 0, --size);

        while (size > 0) {
            heapify(arr, 0, size);
            Util.swap(arr, 0, --size);
        }
    }

    public static void getHeap(int[] arr, boolean optimize) {
        if (optimize) {
            for (int i = arr.length-1; i >= 0; i--) {
                heapify(arr, i, arr.length);
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                heapInsert(arr, i);
            }
        }
    }


    public static void heapify(int[] arr, int index, int size) {
        int left = index*2+1;
        while(left < size) {
            int max = left + 1 < size && arr[left+1] > arr[left] ? left+1 : left;
            if (arr[index] < arr[max]) {
                Util.swap(arr, index, max);
                index = max;
                left = index*2+1;
            } else {
                break;
            }
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while ((index > 0) && (arr[index] > arr[(index-1)/2])) {
            Util.swap(arr, index, (index-1)/2);
            index = (index-1)/2;
        }
    }
}
