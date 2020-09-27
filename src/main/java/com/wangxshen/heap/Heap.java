package com.wangxshen.heap;

/**
 * @Author WangShen
 * @Date 2020/9/27 15:05
 * @Version 1.0
 */
public abstract class Heap {
    protected int[] heap;
    protected int limit;
    protected int heapSize;

    public abstract void push(int value);
    public abstract int pop();
    public abstract void heapify(int[] arr, int index, int heapSize);
    public abstract void heapInsert(int[] arr, int index);

    public boolean isEmpty() {
        return this.heapSize == 0;
    }

    public boolean isFull() {
        return this.heapSize == this.limit;
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public int[] getHeap() {
        return this.heap;
    }

    public int size() {
        return this.heapSize;
    }

    public int getLimit() {
        return this.limit;
    }
}
