package com.wangxshen.linkedList.execises;

import com.wangxshen.stack.ArrayStack;
import com.wangxshen.stack.LinkedStack;
import com.wangxshen.stack.Stack;

/**
 * @Author WangShen
 * @Date 2020/9/30 14:09
 * @Version 1.0
 */
public class IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * @Author:   on2020-09-30 14:20:36
     * @Param: null
     * @return:
     * description: 使用栈判断是否是回文的，额外空间复杂度O(N)
     */
    public static boolean isPalindrome1(Node head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        Stack<Node> stack = new LinkedStack<>();
        Node node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        node = head;
        while (node != null) {
            if (node.value != stack.pop().value) {
                return false;
            }
            node = node.next;
        }
        return true;
    }

    /**
     * @Author:   on2020-09-30 14:21:41
     * @Param: null
     * @return:
     * description: 使用栈判断是否是回文的，额外空间复杂度O(N/2)，需要用到快慢指针求中点的方法
     */
    public static boolean isPalindrome2(Node head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        Stack<Node> stack = new LinkedStack<>();
        Node mid = getMiddle(head);
        Node cur = mid;
        //链表右半部分压栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != mid.next) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //求链表中点，如果长度为奇数返回中点，如果为偶数返回前一个中点
    public static Node getMiddle(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        //此时链表至少有三个节点
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * @Author:   on2020-09-30 14:38:58
     * @Param: null
     * @return:
     * description: 不适用额外的栈，需要用到链表反转和快慢指针求中点的方法,额外空间复杂度O(1)
     */
    public static boolean isPalindrome3(Node head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        Node mid = getMiddle(head);
        Node node = mid.next;
        mid.next = null;
        node = reverse(node);
        Node head2 = node;
        while (head != null && head2 != null) {
            if (head.value != head2.value) {
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }

        //恢复链表
        node = reverse(node);
        mid.next = node;
        return true;
    }

    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = null;
        Node next = null;
        while(head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }

}
