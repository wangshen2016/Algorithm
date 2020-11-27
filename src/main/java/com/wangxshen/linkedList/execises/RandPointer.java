package com.wangxshen.linkedList.execises;

/**
 * @Author WangShen
 * @Date 2020/11/27 14:10
 * @Version 1.0
 */
public class RandPointer {
    public static Node copy(Node head) {
        Node cur = head;
        Node next = null;
        Node copied = null;
        // build: n1 -> c1 -> n2 -> c2 -> n3 -> c3 -> null
        while (cur != null) {
            next = cur.next;
            copied = new Node(cur.value, next);
            cur.next = copied;
            cur = next;
        }
        // copy rand pointer
        cur = head;
        while (cur != null) {
            next = cur.next;
            next.rand = cur.rand != null ? cur.rand.next : null;
            cur = next.next;
        }
        // split
        cur = head;
        Node ans = head.next;
        while (cur != null) {
            next = cur.next.next;
            copied = cur.next;
            cur.next = next;
            copied.next = next != null ? next.next : null;
            cur = next;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = Node.getTestValues(1000000);
        Node head = Node.buildRand(arr);
        Node copied = copy(head);
        while (head != null && copied != null) {
            if ((head.value != copied.value) ||
                    (head.rand != null && copied.rand != null && head.rand.value != copied.rand.value) ||
                    (head.rand == null && copied.rand != null) ||
                    (head.rand != null && copied.rand == null)) {
                System.out.println("fail");
                return;
            }
            head = head.next;
            copied = copied.next;
        }
        if (head != null || copied != null) {
            System.out.println("fail");
            return;
        }
        System.out.println("success");
    }
}
