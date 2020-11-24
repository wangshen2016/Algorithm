package com.wangxshen.linkedList.execises;

import com.wangxshen.linkedList.struct.SinglyLinkedList;

import java.util.ArrayList;

/**
 * @Author WangShen
 * @Date 2020/9/29 13:58
 * @Version 1.0
 */
public class SlowFastPointer {

    /**
     * @Author:   on2020-09-29 14:00:39
     * @Param: head
     * @return: LinkedList<Integer>
     * description: 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     */
    public static SinglyLinkedList<Integer> midOrUpMidNode(SinglyLinkedList<Integer> head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        //此时链表至少有三个节点
        SinglyLinkedList<Integer> slow = head.next;
        SinglyLinkedList<Integer> fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * @Author:   on2020-09-29 14:04:02
     * @Param: null
     * @return: 
     * description: 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     */
    public static SinglyLinkedList<Integer> midOrDownMidNode(SinglyLinkedList<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        //此时链表至少有两个节点
        SinglyLinkedList<Integer> slow = head.next;
        SinglyLinkedList<Integer> fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * @Author:   on2020-09-29 14:05:23
     * @Param: null
     * @return: 
     * description: 输入链表头节点，奇数长度返回中点的前一个，偶数长度返回上中点的前一个
     */
    public static SinglyLinkedList<Integer> midOrUpMidPreNode(SinglyLinkedList<Integer> head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        //此时链表至少有三个节点
        SinglyLinkedList<Integer> slow = head;
        SinglyLinkedList<Integer> fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * @Author:   on2020-09-29 14:05:37
     * @Param: null
     * @return:
     * description: 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     */
    public static SinglyLinkedList<Integer> midOrDownMidPreNode(SinglyLinkedList<Integer> head) {
        if (head == null || head.next == null) {
            return null;
        }
        //此时链表至少有两个节点
        SinglyLinkedList<Integer> slow = head;
        SinglyLinkedList<Integer> fast = head.next;

        while (fast.next != null && fast.next.next != null) {
            //第一次进入时链表至少有四个节点
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    //对数器，用来测试
    public static SinglyLinkedList<Integer> right1(SinglyLinkedList<Integer> head) {
        if (head == null) {
            return null;
        }
        SinglyLinkedList<Integer> cur = head;
        ArrayList<SinglyLinkedList<Integer>> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 1) / 2);
    }

    public static SinglyLinkedList<Integer> right2(SinglyLinkedList<Integer> head) {
        if (head == null) {
            return null;
        }
        SinglyLinkedList<Integer> cur = head;
        ArrayList<SinglyLinkedList<Integer>> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }

    public static SinglyLinkedList<Integer> right3(SinglyLinkedList<Integer> head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        SinglyLinkedList<Integer> cur = head;
        ArrayList<SinglyLinkedList<Integer>> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 3) / 2);
    }

    public static SinglyLinkedList<Integer> right4(SinglyLinkedList<Integer> head) {
        if (head == null || head.next == null) {
            return null;
        }
        SinglyLinkedList<Integer> cur = head;
        ArrayList<SinglyLinkedList<Integer>> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 2) / 2);
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> test = null;
        test = new SinglyLinkedList<Integer>(0);
        test.next = new SinglyLinkedList<Integer>(1);
        test.next.next = new SinglyLinkedList<Integer>(2);
        test.next.next.next = new SinglyLinkedList<Integer>(3);
        test.next.next.next.next = new SinglyLinkedList<Integer>(4);
        test.next.next.next.next.next = new SinglyLinkedList<Integer>(5);
        test.next.next.next.next.next.next = new SinglyLinkedList<Integer>(6);
        test.next.next.next.next.next.next.next = new SinglyLinkedList<Integer>(7);
        test.next.next.next.next.next.next.next.next = new SinglyLinkedList<Integer>(8);

        SinglyLinkedList<Integer> ans1 = null;
        SinglyLinkedList<Integer> ans2 = null;

        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrUpMidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrDownMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

    }


}
