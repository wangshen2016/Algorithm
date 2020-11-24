package com.wangxshen.linkedList.execises;

/**
 * @Author WangShen
 * @Date 2020/11/23 18:04
 * @Version 1.0
 */
public class Reverse {
    //非递归
    public static Node reverse1(Node head){
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 递归
    public static Node reverse2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node last = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    //反转前n个节点
    static Node successor = null;
    public static Node reverseN(Node head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        Node last = reverseN(head.next, n-1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    //反转m to n 个节点
    public static Node reverseM2N(Node head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseM2N(head.next, m-1, n-1);
        return head;
    }

    public static void main(String[] args) {
        final int N = 10000;
        int[] arr = Node.getTestValues(N);
        Node head = Node.build(arr);
        //测试反转整个链表
 /*       Node reversed = reverse2(head);

        for (int i = arr.length-1; i >= 0; i--, reversed = reversed.next) {
            if (reversed == null || reversed.value != arr[i]) {
                System.out.println("fail");
                return;
            }
        }
        System.out.println("success");*/

        //测试反转前n个节点
        /*int limit = 100;
        Node reverseN = reverseN(head, limit);

        for (int i = limit-1; i >= 0; i--, reverseN = reverseN.next) {
            if (reverseN == null || reverseN.value != arr[i]) {
                System.out.println("fail");
                return;
            }
        }
        for (int i = limit; i < arr.length; i++, reverseN = reverseN.next) {
            if (reverseN == null || reverseN.value != arr[i]) {
                System.out.println("fail");
                return;
            }
        }
        System.out.println("success");*/

        //测试反转M到N的链表,M>=0
        int m = 100;
        int n = 200;
        Node reverseM2N = reverseM2N(head, m, n);

        for (int i = 0; i < m-1; i++, reverseM2N = reverseM2N.next) {
            if (reverseM2N == null || reverseM2N.value != arr[i]) {
                System.out.println("fail");
                return;
            }
        }

        for (int i = n-1; i >= m-1; i--, reverseM2N = reverseM2N.next) {
            if (reverseM2N == null || reverseM2N.value != arr[i]) {
                System.out.println("fail");
                return;
            }
        }

        for (int i = n; i < arr.length; i++, reverseM2N = reverseM2N.next) {
            if (reverseM2N == null || reverseM2N.value != arr[i]) {
                System.out.println("fail");
                return;
            }
        }
        System.out.println("success");

    }
}
