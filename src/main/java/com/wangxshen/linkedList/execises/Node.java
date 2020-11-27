package com.wangxshen.linkedList.execises;

/**
 * @Author WangShen
 * @Date 2020/11/23 18:18
 * @Version 1.0
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author:   on2020-11-24 20:44:40
 * @Param: null
 * @return:
 * description: 链表节点实现以及工具函数
 */
public class Node {
    public int value;
    public Node next;
    public Node rand;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public boolean hasNext() {
        return next == null ? false : true;
    }



    public static void print(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    public static Node build(int[] arr) {
        Node pre = new Node(0);
        Node head = pre;
        for (int v : arr) {
            pre.next = new Node(v);
            pre = pre.next;
        }
        return head.next;
    }

    public static Node buildRand(int[] arr) {
        Node pre = new Node(0);
        Node head = pre;
        Node[] nodeArray = new Node[arr.length+1];
        for (int i = 0; i < arr.length; i++) {
            pre.next = new Node(arr[i]);
            pre = pre.next;
            nodeArray[i] = pre;
        }
        nodeArray[arr.length] = null;
        //随机指针赋值
        pre = head.next;
        int randIndex = 0;
        while (pre != null) {
            randIndex = (int)((Math.random()) * (arr.length+1));
            pre.rand = nodeArray[randIndex];
            pre = pre.next;
        }
        return head.next;
    }

    public static void printRand(Node head) {
        while (head != null) {
            System.out.println("value: " + head.value + ", rand: " + (head.rand != null ? head.rand.value : "--"));
            head = head.next;
        }
    }

    public static int[] getTestValues(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int)(Math.random() * N);
        }
        return arr;
    }
}
