package com.wangxshen.sort;

import com.wangxshen.heap.Heap;
import com.wangxshen.heap.MaxHeap;
import com.wangxshen.heap.MinHeap;

/**
 * @Author WangShen
 * @Date 2020/9/27 16:00
 * @Version 1.0
 */
public class HeapSort {

    /**
     * @Author:   on2020-09-27 16:25:10
     * @Param: null
     * @return:
     * description: 堆排序的额外空间复杂度为O(1)，时间复杂度为O(N*logN)
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        //该对象仅用来调用其中的成员方法
        Heap heap = new MaxHeap();
        //O(N*logN)
        for (int i = 0; i < arr.length; i++) {//O(N)
            heap.heapInsert(arr, i);//O(logN)
        }

        //对上面的优化,结果得到一个没有排好序的最大堆O(N)
        for (int i = arr.length; i >= 0; i--) {
            heap.heapify(arr, i, arr.length);
        }

        int heapSize = arr.length;

        //最大值放到后面
        heap.swap(arr, 0, --heapSize);

        //O(N*logN)
        while (heapSize > 0) {//O(N)
            heap.heapify(arr, 0, heapSize);//O(logN)
            heap.swap(arr, 0, --heapSize);//O(1)
        }
    }

    /**
     * @Author:   on2020-09-27 21:58:21
     * @Param: null
     * @return:
     * description: 使用最小堆排序
     */
    public static void sort2(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        Heap heap = new MinHeap();

        for (int i = 0; i < arr.length; i++) {
            heap.heapInsert(arr, i);
        }

        int heapSize = arr.length;

        heap.swap(arr, 0, --heapSize);

        while(heapSize > 0) {
            heap.heapify(arr, 0, heapSize);
            heap.swap(arr, 0, --heapSize);
        }

    }
    
    /**
     * @Author:   on2020-09-27 22:40:06 
     * @Param: null
     * @return: 
     * description: 已知一个几乎有序的数组，几乎有序是指，
     * 如果把数组排好顺序的话，每个元素移动的距离一定不超过k，
     * 并且k相对于数组长度来说是比较小的。
     * 请选择一个合适的排序策略，对这个数组进行排序。
     *
     * 时间复杂度O(N*logK)
     */
    public static void sort3(int[] arr, int k) {
        if (arr == null || arr.length < 2 || k < 1) {
            return;
        }
        Heap heap = new MinHeap(k+1);
        int index = 0;
        for (; index < Math.min(arr.length - 1, k); index++) {
            heap.push(arr[index]);
        }
        int i = 0;
        for (;index < arr.length; i++, index++) {
            heap.push(arr[index]);
            arr[i] = heap.pop();
        }

        while (!heap.isEmpty()) {
            arr[i++] = heap.pop();
        }
    }
}
