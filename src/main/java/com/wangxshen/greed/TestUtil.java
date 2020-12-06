package com.wangxshen.greed;

/**
 * @Author WangShen
 * @Date 2020/12/6 12:10
 * @Version 1.0
 */
//方便测试用的工具类
public class TestUtil {
    public static String generateRandomString(int strlen) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strlen; i++) {
            char c = (char) ((int)(Math.random() * strlen) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

    public static String[] generateRandomStringArray(int len, int strlen) {
        String[] ans = new String[len];
        for (int i = 0; i < len; i++) {
            ans[i] = generateRandomString(strlen);
        }
        return ans;
    }
}
