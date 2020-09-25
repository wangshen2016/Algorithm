package com.wangxshen.stack;

/**
 * @Author WangShen
 * @Date 2020/9/25 16:25
 * @Version 1.0
 */
public class MinStack implements Stack<Integer> {
    ArrayStack<Integer> stack;
    ArrayStack<Integer> minStack;

    public void push(Integer value) {
        stack.push(value);
        if (minStack.isEmpty()) {
            minStack.push(value);
        } else {
            minStack.push(minStack.peek() > value ? value : minStack.peek());
        }
    }

    public Integer pop() {
        minStack.pop();
        return stack.pop();
    }

    public Boolean isEmpty() {
        return stack.isEmpty();
    }

    public Integer peek() {
        return stack.peek();
    }

    public Integer getMin() {
        return minStack.peek();
    }
}
