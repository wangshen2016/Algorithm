package com.wangxshen.linkedList.execises;

/**
 * @Author WangShen
 * @Date 2020/11/23 21:56
 * @Version 1.0
 */
public class Loop {
    //判断是否有环，如果有环返回入环节点
    public static Node hasLoop(Node head) {
        if (head == null) {
            return head;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2, node1);
        Node node3 = new Node(3, node2);
        Node node4 = new Node(4, node3);
        Node node5 = new Node(5, node4);
        Node node6 = new Node(6, node5);
        node1.next = node3;
        System.out.println(hasLoop(node6).value);
    }
}
