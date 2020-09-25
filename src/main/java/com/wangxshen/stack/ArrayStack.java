package com.wangxshen.stack;

import org.omg.CORBA.Object;

/**
 * @Author WangShen
 * @Date 2020/9/25 15:57
 * @Version 1.0
 */
public class ArrayStack<T> implements Stack<T> {

    private T[] array;
    private Integer top;
    private Integer limit;

    public ArrayStack(Integer limit) {
        this.array = (T[]) new Object[limit];
        this.top = 0;
        this.limit = limit;
    }

    public void push(T value) {
        if (isFull()) {
            throw new RuntimeException("栈满了");
        }
        array[this.top++] = value;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return array[--this.top];
    }

    public T peek() {
        if (!isEmpty()) {
            return array[this.top - 1];
        } else {
            return null;
        }
    }

    public Boolean isFull() {
        return this.top == this.limit ? true : false;
    }

    public Boolean isEmpty() {
        return this.top == 0 ? true : false;
    }
}
