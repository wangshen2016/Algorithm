package com.wangxshen.linkedList.execises;

/**
 * @Author WangShen
 * @Date 2020/9/30 15:29
 * @Version 1.0
 */

import com.wangxshen.heap.MaxHeap;

import java.util.ArrayList;

/**
 * @Author:   on2020-09-30 16:47:50
 * description: 将单链表按某值划分成左边小、中间相等、右边大 的形式
 */
public class SmallEqualBigger {
    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * @Author:   on2020-09-30 16:47:01
     * @Param: null
     * @return:
     * description: 将链表节点放到数组中，在数组上做partition，然后重新赋值
     */
    public static Node resign(Node head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }
        Node node = head;
        int count = 0;
        while (node != null) {
            node = node.next;
            count++;
        }
        Node[] nodes = new Node[count];
        node = head;
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = node;
            node = node.next;
        }
        partition(nodes, pivot);
        for (int i = 1; i < nodes.length; i++) {
            nodes[i-1].next = nodes[i];
        }
        nodes[nodes.length-1].next = null;
        return nodes[0];
    }

    public static void partition(Node[] arr, int pivot) {
        int L = -1;
        int R = arr.length;
        int cur = 0;
        while (cur < R) {
            if (arr[cur].value < pivot) {
                swap(arr, ++L, cur++);
            } else if (arr[cur].value == pivot) {
                cur++;
            } else {
                swap(arr, cur, --R);
            }
        }
    }

    public static void swap(Node[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        Node tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        Node head = new Node((int)(Math.random()*10));
        System.out.print(head.value + " ");
        Node node = head;
        for (int i = 0; i < 10; i++) {
            node.next = new Node((int)(Math.random()*10));
            node = node.next;
            System.out.print(node.value + " ");
        }
        System.out.println("\n-----------------------");
        head = resign(head, 5);

        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

}
