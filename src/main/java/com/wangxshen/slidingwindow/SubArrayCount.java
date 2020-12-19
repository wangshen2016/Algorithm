package com.wangxshen.slidingwindow;

import java.util.LinkedList;

/**
 * @Author WangShen
 * @Date 2020/12/16 15:51
 * @Version 1.0
 */
public class SubArrayCount {
    /**
     * @Author:   on2020-12-16 15:51:21
     * @Param: null
     * @return: 
     * description: 给定一个数组arr，和一个正数num，
     * 某个arr中的子数组sub，如果想达标，必须满足：
     * sub中的最大值 - sub中的最小值 <= num;
     * 返回arr中达标子数组的数量
     *
     */
    public static int solution(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        LinkedList<Integer> minq = new LinkedList<>();
        LinkedList<Integer> maxq = new LinkedList<>();

        int res = 0;
        int L = 0;
        int R = 0;

        while(L < arr.length) {
            R = L;
            while (R < arr.length) {
                while(!minq.isEmpty() && arr[minq.peekLast()] >= num) {
                    minq.pollLast();
                }
                minq.offerLast(R);

                while(!maxq.isEmpty() && arr[maxq.peekLast()] <= num) {
                    maxq.pollLast();
                }
                maxq.offerLast(R);

                if (arr[maxq.peekFirst()] - arr[minq.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            res += R - L + 1;
            L++;
            if (maxq.peekFirst() == L) {
                maxq.pollFirst();
            }
            if (minq.peekFirst() == L) {
                minq.pollFirst();
            }
        }
        return res;
    }

}
