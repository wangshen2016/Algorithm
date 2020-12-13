package com.wangxshen.recursionAnddp;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @Author WangShen
 * @Date 2020/12/10 13:45
 * @Version 1.0
 */
public class SubStrings {
    /**
     * @Author:   on2020-12-10 13:45:58
     * @Param: null
     * @return:
     * description: 打印一个字符串的全部子序列
     * eg: "abc" -> "a", "b", "c", "ab", "ac", "bc", "abc"
     */
    public static void printSub(String str) {
        if (str == null || str == "") {
            return;
        }
        //如果需要去重，就使用set
        ArrayList<String> ans = new ArrayList<>();
        process(str.toCharArray(), 0, ans, "");
        System.out.println(ans);
    }

    public static void process(char[] str, int index, ArrayList<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        String no = path;
        process(str, index+1, ans, no);

        String yes = path + str[index];
        process(str, index+1, ans, yes);
    }

   

    @Test
    public void test() {
        String str = "abc";
        printSub(str);
    }
}
