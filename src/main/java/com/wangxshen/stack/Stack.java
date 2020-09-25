package com.wangxshen.stack;

/**
 * @Author WangShen
 * @Date 2020/9/25 15:54
 * @Version 1.0
 */
public interface Stack<T> {
    public void push(T value);
    public T pop();
    public Boolean isEmpty();
    public T peek();
}
