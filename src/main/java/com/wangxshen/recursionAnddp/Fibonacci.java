package com.wangxshen.recursionAnddp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author WangShen
 * @Date 2020/12/13 20:07
 * @Version 1.0
 */
public class Fibonacci {
    public static int process(int N) {
        if (N == 1 || N == 2) {
            return 1;
        }

        return process(N-1) + process(N-2);
    }

    @Test
    public void test() {
        int N = 8;
        System.out.println("result: " + process(N));
        System.out.println("下面即所谓动态规划思路......");
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            if (i == 0 || i == 1) {
                result[i] = 1;
            } else {
                result[i] = result[i - 1] + result[i - 2];
            }
        }
        System.out.println(Arrays.toString(result));
    }
}
