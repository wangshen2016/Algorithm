package com.wangxshen.recursionAnddp;

/**
 * @Author WangShen
 * @Date 2020/9/25 16:50
 * @Version 1.0
 */
public class GetMax {

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        int mid = left + ((right - left) >> 1);
        int leftMax = process(arr, left, mid);
        int rightMax = process(arr, mid, right);
        return leftMax > rightMax ? leftMax : rightMax;
    }
}
