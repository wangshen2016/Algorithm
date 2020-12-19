package com.wangxshen.slidingwindow;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author WangShen
 * @Date 2020/12/16 12:25
 * @Version 1.0
 */
public class WindowMaxValue {
    /**
     * @Author:   on2020-12-16 12:25:49
     * @Param: int[] arr, int w
     * @return: int[] res
     * description: 给出一个整形数组arr，窗口大小w
     * 返回在将窗口从左向右移动过程中，窗口中的最大值
     * eg: int[] arr = new int[]{2,5,4,3,6,7}; int w = 3;
     * res = {5,5,6,7}
     */
    public static int[] getMaxValueOfWindow(int[] arr, int w) {
        if (arr == null || arr.length == 1) {
            return arr;
        }
        if (w < 0) {
            return null;
        }
        Deque<Integer> window = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        for (int R = 0; R < arr.length; R++) {

            while (!window.isEmpty() && arr[window.peekLast()] <= arr[R]) {
                window.pollLast();
            }
            window.offerLast(R);

            if (R - w + 1 > window.peekFirst()) {
                window.pollFirst();
            }

            if (R >= w-1) {
                res[R-w+1] = arr[window.peekFirst()];
            }
        }

        return res;
    }

    public static int[] forTest(int[] arr, int w) {
        if (arr == null || arr.length == 1) {
            return arr;
        }
        if (w < 0) {
            return null;
        }
        Deque<Integer> window = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];

        for (int R = 0; R < arr.length; R++) {
            while (!window.isEmpty() && arr[window.peekLast()] <= arr[R]) {
                window.pollLast();
            }

            window.addLast(R);

            if (window.peekFirst() == R - w) {
                window.pollFirst();
            }

            if (R >= w - 1) {
                res[R-w+1] = arr[window.peekFirst()];
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int size = 20;
        int seed = 100;
        int w = (int)(Math.random() * 5) + 1;
        int loop = 10000;
        for (int i = 0; i < loop; i++) {
            int[] arr = randomArray(size, seed);
            int[] res1 =  getMaxValueOfWindow(arr, w);
            int[] res2 = forTest(arr, w);
            for (int j = 0; j < res1.length; j++) {
                if (res1[j] != res2[j]) {
                    System.out.println("source: " + Arrays.toString(arr));
                    System.out.println("res1: " + Arrays.toString(res1));
                    System.out.println("res2: " + Arrays.toString(res2));
                    return;
                }
            }
        }
        System.out.println("success");
    }

    public static int[] randomArray(int size, int seed) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random() * seed);
        }
        return arr;
    }
}
