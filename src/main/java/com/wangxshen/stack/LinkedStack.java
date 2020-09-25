package com.wangxshen.stack;

import com.wangxshen.linkedList.DoublyLinkedList;
import com.wangxshen.queue.DoubleEndsQueue;

/**
 * @Author WangShen
 * @Date 2020/9/25 15:46
 * @Version 1.0
 */
public class LinkedStack<T> implements Stack<T> {
    private DoubleEndsQueue<T> queue;

    public LinkedStack() {
        queue = new DoubleEndsQueue<T>();
    }

    public void push(T value) {
        queue.addFromHead(value);
    }

    public T pop() {
        return queue.popFromHead();
    }

    public Boolean isEmpty() {
        return queue.isEmpty();
    }

    public T peek() {
        return queue.head.value;
    }
}
