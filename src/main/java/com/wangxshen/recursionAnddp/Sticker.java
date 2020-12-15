package com.wangxshen.recursionAnddp;

import org.junit.Test;

import java.util.HashMap;

/**
 * @Author WangShen
 * @Date 2020/12/14 19:52
 * @Version 1.0
 */
public class Sticker {
    /**
     * @Author:   on2020-12-15 14:54:35
     * @Param: null
     * @return:
     * description:
     * 给出贴纸集合String[] arr
     * 贴纸可以剪碎，每种贴纸可以使用n次，最终拼凑出aim字符串，
     * 返回需要的最少的贴纸数量
     */
    /** 伪代码
    public static int p(String[] arr, String rest) {
        if (rest.equals("")) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            String str = rest - arr[i];
            min = Math.min(min, p(arr, str)+1);
        }
        return min;
    }
    */
    public static int getMinStickers(String[] arr, String aim) {
        int[][] map = new int[arr.length][26];

        for (int i = 0; i < arr.length; i++) {
            for (char ch : arr[i].toCharArray()) {
                map[i][ch - 'a']++;
            }
        }

        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);

        return process(map, aim, dp);
    }

    public static int process(int[][] map, String rest, HashMap<String, Integer> dp) {
        if (dp.containsKey(rest)) {
            return dp.get(rest);
        }

        char[] target = rest.toCharArray();
        int[] tmap = new int[26];
        for (char ch : target) {
            tmap[ch - 'a']++;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < map.length; i++) {

            if (map[i][target[0] - 'a'] == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < 26; j++) {
                if (tmap[j] > 0) {
                    for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
                        sb.append((char)('a' + j));
                    }
                }
            }

            String r = sb.toString();
            int ret = process(map, r, dp);
            if (ret != -1) {
                ans = Math.min(ans, ret + 1);
            }
        }
        dp.put(rest, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(rest);
    }

    @Test
    public void test() {
        String target = "helloworld";
        String[] stickers = new String[] {
          "abcde","hello","wyz","worl","word"
        };
        System.out.println(getMinStickers(stickers, target));
    }
}
