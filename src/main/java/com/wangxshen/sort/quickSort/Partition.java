package com.wangxshen.sort.quickSort;

import java.util.Arrays;

/**
 * @Author WangShen
 * @Date 2020/9/26 15:02
 * @Version 1.0
 */
public class Partition {

    /**
     * @Author:   on2020-09-26 15:03:02
     * @Param: arr 输入整型数组 L  数组左边界 R数组右边界
     * @return: int[] 返回中间值的左右边界
     * description: 荷兰三色国旗问题
     */
    public static int[] partition(int[] arr, int L, int R) {
        if (arr.length < 0 || L > R || L < 0) {
            return null;
        } else if (arr.length == 1) {
            return new int[] {L, R};
        }
        int less = L - 1;
        int more = R;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < arr[R]) {
                swap(arr, cur++, ++less);
            } else if (arr[cur] == arr[R]) {
                cur++;
            } else {
                swap(arr, cur, --more);
            }
        }
        swap(arr, more, R);
        return new int[]{less+1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,1,6,3,7,4,3};
        int[] ret = partition(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(ret));
    }
}
