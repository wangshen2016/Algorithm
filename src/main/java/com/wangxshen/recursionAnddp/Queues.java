package com.wangxshen.recursionAnddp;

import org.junit.Test;

/**
 * @Author WangShen
 * @Date 2020/12/13 19:45
 * @Version 1.0
 */

public class Queues {
    /**
     * @Author:   on2020-12-13 19:45:12
     * @Param: null
     * @return:
     * description: N皇后问题，
     * 要求任两个皇后不同行不同列且不在对角线上
     * 返回共有多少种摆法
     */
    public static int getCount(int N) {
        if (N < 4) {
            return 0;
        }
        return process(new int[N], 0, N);
    }

    public static int process(int[] record, int i, int N) {
        if (i == N) {
            return 1;
        }
        int ret = 0;
        for (int j = 0; j < N; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                ret += process(record, i+1, N);
            }
        }
        return ret;
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(record[k]-j) == Math.abs(k-i)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int N = 14;
        System.out.println(getCount(N));
    }
}
