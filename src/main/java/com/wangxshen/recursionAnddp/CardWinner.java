package com.wangxshen.recursionAnddp;

import org.junit.Test;

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

    @Test
    public void test() {
        int[] arr = new int[] {70, 100, 1, 4};
        System.out.println(winner(arr));
    }

}
