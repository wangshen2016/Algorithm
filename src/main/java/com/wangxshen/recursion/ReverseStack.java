package com.wangxshen.recursion;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author WangShen
 * @Date 2020/12/9 23:01
 * @Version 1.0
 */
public class ReverseStack {

    public static void reverse(Deque<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Integer i = popBottom(stack);
        reverse(stack);
        stack.push(i);
    }

    public static Integer popBottom(Deque<Integer> stack) {
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            int r = stack.pop();
            int bottom = popBottom(stack);
            stack.push(r);
            return bottom;
        }
    }

    @Test
    public void test() {
        Integer[] source = new Integer[] {10,9,8,7,6,5,4,3,2,1};
        Deque<Integer> stack = new ArrayDeque<>();
        for (Integer i : source) {
            stack.push(i);
        }
        System.out.println(stack);
        reverse(stack);
        System.out.println("after reverse");
        System.out.println(stack);
    }
}
