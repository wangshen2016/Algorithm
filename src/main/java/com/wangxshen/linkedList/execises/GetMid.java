package com.wangxshen.linkedList.execises;

/**
 * @Author WangShen
 * @Date 2020/11/23 17:08
 * @Version 1.0
 */
public class GetMid {

    public static Node getMidOrUp(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static Node getMidOrLow(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static Node getUpMidOrUp(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static Node getUpMidOrLow(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        final int N = 1000000;
        int[] arr = Node.getTestValues(N);
        Integer testVal = null;
        Node head = Node.build(arr);
        //getMidOrUp
        if (N%2 == 0) {
            testVal = N/2 - 1 >= 0 ? arr[N/2 - 1] : null;
        } else {
            testVal = arr[N/2];
        }
        if (getMidOrUp(head).value != testVal) {
            System.out.println("fail--1");
        } else {
            System.out.println("success--1");
        }
        //getMidOrLow
        if (N%2 == 0) {
            testVal = arr[N/2];
        } else {
            testVal = N/2 > 0 ? arr[N/2] : arr[1];
        }
        if (getMidOrLow(head).value != testVal) {
            System.out.println("fail--2");
        } else {
            System.out.println("success--2");
        }
        //getUpMidOrUp
        if (N%2 == 0) {
            testVal = N/2 - 1 > 0 ? arr[N/2 - 2] : null;
        } else {
            testVal = N/2 > 0 ? arr[N/2-1] : null;
        }
        if (getUpMidOrUp(head).value != testVal) {
            System.out.println("fail--3");
        } else {
            System.out.println("success--3");
        }
        //getUpMidOrLow
        if (N%2 == 0) {
            testVal = N/2 > 0 ? arr[N/2 - 1] : null;
        } else {
            testVal = N/2 > 0 ? arr[N/2-1] : null;
        }
        if (getUpMidOrLow(head).value != testVal) {
            System.out.println("fail--4");
        } else {
            System.out.println("success--4");
        }
    }
}
