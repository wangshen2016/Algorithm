package com.wangxshen.recursionAnddp;

import org.junit.Test;

/**
 * @Author WangShen
 * @Date 2020/12/9 22:11
 * @Version 1.0
 */
public class HanoiTower {
    public static void hanoiTower(int n) {
        move(n, "left", "right", "middle");
    }

    public static void move(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
        } else {
            move(n-1, from, other, to);
//            move(1, from, to, other);
            System.out.println("move 1 from " + from + " to " + to);
            move(n-1, other, to, from);
        }

    }

    @Test
    public void test() {
        hanoiTower(3);
    }
}
