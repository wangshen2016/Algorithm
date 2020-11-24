package com.wangxshen.linkedList.execises;

/**
 * @Author WangShen
 * @Date 2020/11/23 21:03
 * @Version 1.0
 */
public class Partition {
    public static Node partition(Node head, int val) {
        Node sH = null, sT = null, eH = null, eT = null, bH = null, bT = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < val) {
                if (sH == null && sT == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == val) {
                if (eH == null && eT == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (bH == null && bT == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }


        if (sT != null) {
            if (eT != null) {
                sT.next = eH;
            } else {
                sT.next = bH;
            }
        }
        if (eT != null) {
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }

    public static void main(String[] args) {
        int N = 100;
        int[] arr = Node.getTestValues(N);
        Node head = Node.build(arr);
        Node.print(head);
        System.out.println("\n--------------------");
        Node partition = partition(head, 50);
        Node.print(partition);
    }

}
