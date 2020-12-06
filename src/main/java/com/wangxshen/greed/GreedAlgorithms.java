package com.wangxshen.greed;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * @Author WangShen
 * @Date 2020/12/6 10:43
 * @Version 1.0
 */
//贪心算法的各种案例
public class GreedAlgorithms {

    /**
     * @Author:   on2020-12-06 11:17:09 
     * @Param: null
     * @return: 
     * description: 给定一个由字符串组成的数组，必须把所有字符串拼接起来，
     * 返回所有可能的结果中，字典序最小的结果
     */
    //暴力方法
    public static String getMinPath(String[] strs) {
        String path = "";
        HashSet<Integer> used = new HashSet<>();
        ArrayList<String> all = new ArrayList<>();
        proccess_minPath(strs, path, used, all);
        String lowest = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if (all.get(i).compareTo(lowest) < 0) {
                lowest = all.get(i);
            }
        }
        return lowest;
    }

    public static void proccess_minPath(String[] strs, String path, HashSet<Integer> used, ArrayList<String> all) {
        if (used.size() == strs.length) {
            all.add(path);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if (!used.contains(i)) {
                    used.add(i);
                    proccess_minPath(strs, path + strs[i], used, all);
                    used.remove(i);
                }
            }
        }
    }

    //贪心算法
    public static String getMinPath2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    @Test
    public void testMinPath() {
        int arrSize = 10;
        int strLength = 5;
        int loop = 100;
        for (int i = 0; i < loop; i++) {
            String[] strs = TestUtil.generateRandomStringArray(arrSize, strLength);
            String res1 = getMinPath(strs);
            String res2 = getMinPath2(strs);
            if (!res1.equals(res2)) {
                System.out.println("-------------test fail--------------");
                System.out.println(res1);
                System.out.println(res2);
                return;
            }
        }
        System.out.println("success");
    }

}
