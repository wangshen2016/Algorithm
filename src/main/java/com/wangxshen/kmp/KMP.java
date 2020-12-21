package com.wangxshen.kmp;

/**
 * @Author WangShen
 * @Date 2020/12/20 19:48
 * @Version 1.0
 */
public class KMP {
    public boolean match(String a, String b) {
        char[] source = a.toCharArray();
        char[] matcher = b.toCharArray();

        int i = 0;
        int j = 0;

        int[] next = getNextArray(matcher);

        while (i < source.length && j < matcher.length) {
            if (source[i] == matcher[j]) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }

        return j == matcher.length ? true : false;
    }

    private int[] getNextArray(char[] matcher) {
        int[] next = new int[matcher.length];
        next[0] = -1;
        next[1] = 0;

        int i = 2;
        int ch = 0;
        while (i < matcher.length) {
            if (matcher[i-1] == matcher[ch]) {
                next[i++] = ++ch;
            } else if (ch > 0) {
                ch = next[ch];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
