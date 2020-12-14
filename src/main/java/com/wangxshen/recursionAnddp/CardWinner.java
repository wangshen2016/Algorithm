package com.wangxshen.recursionAnddp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author WangShen
 * @Date 2020/12/11 14:52
 * @Version 1.0
 */
public class CardWinner {
    /**
     * @Author:   on2020-12-11 14:53:01
     * @Param: null
     * @return:
     * description: 给定一个整形数组arr，代表不同数值的纸牌排成一排，
     * 玩家A和玩家B依次拿走每张纸牌，
     * 规定玩家A先拿，玩家B后拿，
     * 但是每个玩家每次只能拿走最左或者最右的纸牌，
     * 玩家A和玩家B都没头发的聪明，请返回最终获胜者的分数
     */
    public static int winner(int[] arr) {
        if (arr == null) {
            return -1;
        }
        return Math.max(f(arr, 0, arr.length-1), s(arr, 0, arr.length-1));
    }
    public static int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        return Math.max(arr[L] + s(arr, L+1, R), arr[R] + s(arr, L, R-1));
    }

    public static int s(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(f(arr, L+1, R), f(arr, L, R-1));
    }

    /**
     * @Author:   on2020-12-14 10:11:03
     * @Param: null
     * @return:
     * description: 动态规划解法
     */
    public static int dp_solution(int[] arr) {
        int N = arr.length;
        int[][] F = new int[N][N];
        int[][] S = new int[N][N];

        //initialize the dp arrays through base case.
        //S[][]默认初始化
        for (int i = 0; i < N; i++) {
            F[i][i] = arr[i];
        }

        //fill the array
        for (int i = 1; i < N; i++) {
            int row = 0;
            int col = i;
            for (;col < N; col++) {
                F[row][col] = Math.max(arr[row] + S[row+1][col], arr[col] + S[row][col-1]);
                S[row][col] = Math.min(F[row+1][col], F[row][col-1]);
                row++;
            }
        }

        return Math.max(F[0][N-1], S[0][N-1]);
    }

    @Test
    public void test() {
        int N = 100000;
        int size = 20;
        int range = 100;
        for (int i = 0; i < N; i++) {
            int[] arr = getRandomArray(size, range);
            if (winner(arr) != dp_solution(arr)) {
                System.out.println("------------fail--------------");
                return;
            }
        }
        System.out.println("success");
    }

    public static int[] getRandomArray(int size, int range) {
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random() * range);
        }
        return arr;
    }

}
