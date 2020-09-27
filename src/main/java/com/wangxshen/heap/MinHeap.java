package com.wangxshen.heap;


/**
 * @Author WangShen
 * @Date 2020/9/27 21:39
 * @Version 1.0
 */
public class MinHeap extends Heap {

    public MinHeap() {
        this.limit = 16;
        this.heap = new int[this.limit];
        this.heapSize = 0;
    }

    public MinHeap(int limit) {
        this.limit = limit;
        this.heapSize = 0;
        this.heap = new int[limit];
    }

    @Override
    public void push(int value) {
        if (this.isFull()) {
            throw new RuntimeException("Heap is full");
        }
        this.heap[this.heapSize] = value;
        this.heapInsert(this.heap, this.heapSize++);
    }

    @Override
    public int pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }

        int ans = this.heap[0];
        swap(this.heap, 0, --this.heapSize);
        heapify(this.heap, 0, this.heapSize);
        return ans;
    }

    @Override
    public void heapify(int[] arr, int index, int heapSize) {
        int lchild = 2*index + 1;
        while (lchild < heapSize) {
            int smallest = lchild + 1 < heapSize && arr[lchild + 1] < arr[lchild] ? lchild + 1 : lchild;
            smallest = arr[index] < arr[smallest] ? index : smallest;
            if (smallest == index) {
                break;
            }
            swap(arr, smallest, index);
            index = smallest;
            lchild = 2*index + 1;
        }
    }

    @Override
    public void heapInsert(int[] arr, int index) {
        while (arr[index] < arr[(index-1)/2]) {
            swap(arr, index, (index-1)/2);
            index = (index-1)/2;
        }
    }
}
