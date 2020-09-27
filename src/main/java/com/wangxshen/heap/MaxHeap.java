package com.wangxshen.heap;

/**
 * @Author WangShen
 * @Date 2020/9/27 15:08
 * @Version 1.0
 */
public class MaxHeap extends Heap{

    public MaxHeap() {
        this.limit = 16;
        this.heap = new int[this.limit];
        this.heapSize = 0;
    }

    public MaxHeap(int limit) {
        this.limit = limit;
        this.heap = new int[limit];
        this.heapSize = 0;
    }


    @Override
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("Heap is full");
        }
        this.heap[this.heapSize++] = value;
        heapInsert(this.heap, this.heapSize - 1);
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Heap is Empty");
        }
        int ans = this.heap[0];
        swap(this.heap, 0, --this.heapSize);
        heapify(this.heap, 0, this.heapSize);
        return ans;
    }

    /**
     * @Author:   on2020-09-27 15:54:00
     * @Param: null
     * @return:
     * description: O(logN), 向上调整当前index数据的位置
     */
    @Override
    public void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index-1)/2]) {
            swap(arr, index, (index-1)/2);
            index = (index-1)/2;
        }
    }

    /**
     * @Author:   on2020-09-27 15:54:22
     * @Param: null
     * @return:
     * description: O(logN)
     */
    @Override
    public void heapify(int[] arr, int index, int heapSize) {
        int lchild = 2 * index + 1;
        while (lchild < heapSize) {
            int largest = lchild + 1 < heapSize && arr[lchild] < arr[lchild + 1] ? lchild + 1 : lchild;
            largest = arr[largest] > arr[index] ? largest : index;

            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            lchild = 2 * index + 1;
        }
    }
}
