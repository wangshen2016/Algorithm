package com.wangxshen.slidingwindow;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author WangShen
 * @Date 2020/12/17 12:10
 * @Version 1.0
 */
public class MonotonousStack {
    /**
     * @Author:   on2020-12-17 12:10:39 
     * @Param: int[] arr
     * @return: 
     * description: 给出一个只包含正整数的数组arr，
     * arr中任何一个子数组sub，一定能算出（sub累加和）*（sub中的最小值）是什么，
     * 那么所有子数组中，这个值最大是多少？
     *
     * solution:前缀和 数组 + 单调栈（小->大）
     */
    public static int solution(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        LinkedList<Integer> stack = new LinkedList<>();;

        int[] prefixSum = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                prefixSum[i] = arr[i];
            } else {
                prefixSum[i] = arr[i] + prefixSum[i - 1];
            }
        }

        int max = Integer.MIN_VALUE;
        for (int L = 0; L < arr.length; L++) {
            int R = L;
            while (R < arr.length && arr[L] <= arr[R]) {
                while (!stack.isEmpty() && arr[stack.peekLast()] > arr[R]) {
                    stack.pollLast();
                }
                stack.addLast(R);
                R++;
            }
            int sum = prefixSum[R-1] - (L == 0 ? 0 : prefixSum[L-1]);
            max = Math.max(arr[stack.peekFirst()] * sum, max);
            stack.clear();
        }
        return max;

    }

    @Test
    public void test() {
        int[] arr = new int[] {3,4,5,6,7,3,2,4,1};
        System.out.println(solution(arr));
    }

    /**
     * @Author:   on2020-12-17 18:56:51
     * @Param: int[] arr
     * @return:
     * description: 打印arr中每个位置i左边和右边最近的比arr[i]小的值
     */
    public static Integer[][] solution1(int[] arr) {
        Integer[][] ret = new Integer[arr.length][2];

        Deque<LinkedList<Integer>> stack = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i);
                stack.offerLast(list);
                continue;
            }
            LinkedList<Integer> list = stack.peekLast();
            Integer index = list.peekLast();

            while(!stack.isEmpty()) {

                if (arr[i] < arr[index]) {
                    LinkedList<Integer> pop = stack.pollLast();
                    LinkedList<Integer> nextPeek = null;
                    Integer minLeft = null;
                    if (!stack.isEmpty() && (nextPeek = stack.peekLast()) != null) {
                        minLeft = nextPeek.peekLast();
                    }
                    for (int curpop : pop) {
                        ret[curpop][0] = minLeft;
                        ret[curpop][1] = i;
                    }

                    if (!stack.isEmpty()) {
                        list = stack.peekLast();
                        index = list.peekLast();
                    } else {
                        LinkedList<Integer> equalVals = new LinkedList<>();
                        equalVals.add(i);
                        stack.offerLast(equalVals);
                        break;
                    }

                } else if (arr[i] == arr[index]) {
                    list.offerLast(i);
                    break;

                } else {
                    LinkedList<Integer> equalVals = new LinkedList<>();
                    equalVals.add(i);
                    stack.offerLast(equalVals);
                    break;
                }
            }
        }

        while (!stack.isEmpty()) {
            LinkedList<Integer> pop = stack.pollLast();

            Integer minLeft = null;
            LinkedList<Integer> nextPeek = null;
            if (!stack.isEmpty() && (nextPeek = stack.peekLast()) != null) {
                minLeft = nextPeek.peekLast();
            }
            for (int curpop : pop) {
                ret[curpop][0] = minLeft;
                ret[curpop][1] = null;
            }
        }
        return ret;
    }

    @Test
    public void test0() {
        int[] arr = new int[] {3,4,5,6,3,2,4,1,5};
        Integer[][] ret = solution1(arr);
        for (int i = 0; i < ret.length; i++) {
            System.out.println(i + ": " + Arrays.toString(ret[i]));
        }
    }
}
