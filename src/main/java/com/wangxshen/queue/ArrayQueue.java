package com.wangxshen.queue;

import java.lang.reflect.Array;

/**
 * @Author WangShen
 * @Date 2020/9/25 16:03
 * @Version 1.0
 */
public class ArrayQueue<T> {
    private T[] array;
    private Integer pollIndex;
    private Integer pushIndex;
    private Integer size;
    private Integer limit;

    public ArrayQueue(Integer limit) {
        this.array = (T[]) new Object[limit];
        this.limit = limit;
        this.pollIndex = 0;
        this.pushIndex = 0;
        this.size = 0;
    }

    public void push(T value) {
        if (this.size == this.limit) {
            throw new RuntimeException("队列不能加了");
        }
        this.size++;
        this.array[this.pushIndex] = value;
        this.pushIndex = nextIndex(this.pushIndex);
    }

    public T pop() {
        if (this.size == 0) {
            throw new RuntimeException("队列是空的");
        }
        this.size--;
        T ret = this.array[this.pollIndex];
        this.pollIndex = nextIndex(this.pollIndex);
        return ret;
    }

    private Integer nextIndex(Integer index) {
        return index < this.limit - 1 ? index + 1 : 0;
    }
}
