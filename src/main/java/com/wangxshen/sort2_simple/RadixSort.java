package com.wangxshen.sort2_simple;

/**
 * @Author WangShen
 * @Date 2020/11/21 18:06
 * @Version 1.0
 */
public class RadixSort {
    public static void sort(int[] arr, int radix) {
        int[] bucket = new int[arr.length];
        int N = maxbits(arr);
        int digit = 0, j = 0;

        for (int d = 1; d <= N; d++) {
            int[] count = new int[radix];
            for (j = 0; j < arr.length; j++) {
                digit = getDigit(arr[j], radix, d);
                count[digit]++;
            }
            for (j = 1; j < radix; j++) {
                count[j] = count[j] + count[j-1];
            }

            for (j = arr.length-1; j >= 0; j--) {
                digit = getDigit(arr[j],radix, d);
                bucket[count[digit] - 1] = arr[j];
                count[digit]--;
            }

            for(j = 0; j < arr.length; j++) {
                arr[j] = bucket[j];
            }

        }
    }

    public static int getDigit(int num, int radix, int d) {
        return (num / (int)Math.pow(radix, d-1)) % radix;
    }

    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int ret = 1;
        while ((max /= 10) > 0) {
            ret++;
        }
        return ret;
    }

}
