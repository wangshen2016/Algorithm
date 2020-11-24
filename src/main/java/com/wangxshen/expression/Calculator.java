package com.wangxshen.expression;

import java.util.*;

/**
 * @Author WangShen
 * @Date 2020/11/14 15:04
 * @Version 1.0
 */
public class Calculator {

    private static HashMap<Character, Integer> privilage = new HashMap<>();

    static {
        privilage.put('+', 0);
        privilage.put('-', 0);
        privilage.put('*', 1);
        privilage.put('/', 1);
        privilage.put('(', -1);
        privilage.put(')', -1);
    }

    public static LinkedList<Object> convertToPostfix(String expression) {
        char[] chars = expression.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        LinkedList<Object> expr = new LinkedList<>();
        //save number
        int n = 0;

        for (int i = 0; i < chars.length; i++) {
            char chr = chars[i];
            if (Character.isDigit(chr)) {
                n = 10 * n + Integer.parseInt(String.valueOf(chr));
                //if char is the last is the array, add n to expr
                if (i == chars.length-1) {
                    expr.add(n);
                }
            } else {
                if (n != 0) {
                    expr.add(n);
                    n = 0;
                }
                //if stack is empty, push the operator directly.
                if (stack.isEmpty()) {
                    stack.push(chr);
                    continue;
                }
                System.out.println(chr);
                switch (chr) {
                    case '(':
                        stack.push(chr);
                        break;
                    case ')':
                        Character pop = null;
                        while (!stack.isEmpty() && (pop = stack.pop()) != '(') {
                            expr.add(pop);
                        }
                        break;
                    case '+':
                        while (!stack.isEmpty() && (privilage.get(stack.peek()) >= privilage.get(chr))) {
                            expr.add(stack.pop());
                        }
                        stack.push(chr);
                        break;
                    case '-':
                        while (!stack.isEmpty() && (privilage.get(stack.peek()) >= privilage.get(chr))) {
                            expr.add(stack.pop());
                        }
                        stack.push(chr);
                        break;
                    case '*':
                        while (!stack.isEmpty() && (privilage.get(stack.peek()) >= privilage.get(chr))) {
                            expr.add(stack.pop());
                        }
                        stack.push(chr);
                        break;
                    case '/':
                        while (!stack.isEmpty() && (privilage.get(stack.peek()) >= privilage.get(chr))) {
                            expr.add(stack.pop());
                        }
                        stack.push(chr);
                        break;
                    default:
                        break;
                }
            }
        }
        while (!stack.isEmpty()) {
            expr.add(stack.pop());
        }
        System.out.println(expr.toString());
        return expr;
    }

    public static Integer calculate(LinkedList<Object> postfix) {
        System.out.println("-------------");
        Deque<Integer> stack = new ArrayDeque<>();
        Integer a, b;
        for (Object c : postfix) {
            System.out.println(stack);
            if (c instanceof Integer) {
                stack.push((Integer)c);
            } else {
                b = stack.pop();
                a = stack.pop();
                switch ((Character)c) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':

                        stack.push(a / b);
                        break;
                    default:
                        break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String expr = "1+2*3-(4+1)/5";//result=6 expr:1 2 3 * + 4 1 + 5 / -
        expr = "(5*6+(4+12)/8)/4";//result=32
        System.out.println(calculate(convertToPostfix(expr)));

    }
}
