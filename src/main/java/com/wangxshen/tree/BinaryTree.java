package com.wangxshen.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @Author WangShen
 * @Date 2020/11/24 20:41
 * @Version 1.0
 */
public class BinaryTree {
    static class Node{
        public int value;
        public Node lchild = null;
        public Node rchild = null;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node lchild, Node rchild) {
            this.value = value;
            this.lchild = lchild;
            this.rchild = rchild;
        }
    }

    //递归先序遍历
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        pre(head.lchild);
        pre(head.rchild);
    }

    //非递归先序遍历
    public static void pre2(Node head) {
        if (head == null) {
            return;
        }
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.print(pop.value + " ");
            if (pop.rchild != null) {
                stack.push(pop.rchild);
            }
            if (pop.lchild != null) {
                stack.push(pop.lchild);
            }
        }
    }

    //递归中序遍历
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.lchild);
        System.out.print(head.value + " ");
        in(head.rchild);
    }

    //非递归中序遍历
    public static void in2(Node head) {
        if (head != null) {
            Deque<Node> stack = new ArrayDeque<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.lchild;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.rchild;
                }
            }
        }
    }

    //递归后序遍历
    public static void post(Node head) {
        if (head == null) {
            return;
        }
        post(head.lchild);
        post(head.rchild);
        System.out.print(head.value + " ");
    }

    //非递归后序遍历
    public static void post2(Node head) {
        if (head == null) {
            return;
        }
        Deque<Node> stack = new ArrayDeque<>();
        Deque<Node> stack2 = new ArrayDeque<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            stack2.push(pop);
            if (pop.lchild != null) {
                stack.push(pop.lchild);
            }
            if (pop.rchild != null) {
                stack.push(pop.rchild);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value + " ");
        }
    }

    //非递归后序遍历
    public static void post3(Node h) {
        if (h == null) {
            return;
        }
        Deque<Node> stack = new ArrayDeque<>();
        Node c = null;
        stack.push(h);
        while (!stack.isEmpty()) {
            c = stack.peek();
            if (c.lchild != null && h != c.lchild && h != c.rchild) {
                stack.push(c.lchild);
            } else if (c.rchild != null && h != c.rchild) {
                stack.push(c.rchild);
            } else {
                Node pop = stack.pop();
                System.out.print(pop.value + " ");
                h = pop;
            }
        }
    }

    //层序遍历
    public static void level(Node head) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.print(poll.value + " ");
            if (poll.lchild != null) {
                queue.offer(poll.lchild);
            }
            if (poll.rchild != null) {
                queue.offer(poll.rchild);
            }
        }
    }

    //层序遍历，按层打印，并打印每层长度
    public static void level2(Node head) {
        Queue<Node> queue = new ArrayDeque<>();
        int n = 1;
        int count = 0;
        queue.offer(head);
        while (!queue.isEmpty()) {
            count = 0;
            for (int i = 0; i < n; i++) {
                Node poll = queue.poll();
                System.out.print(poll.value + " ");
                if (poll.lchild != null) {
                    queue.offer(poll.lchild);
                    count++;
                }
                if (poll.rchild != null) {
                    queue.offer(poll.rchild);
                    count++;
                }
            }
            System.out.println("length: " + n);
            n = count;
        }
    }

    //serialize
    public static Queue<Integer> serialize(Node head) {
        Queue<Integer> ans = new ArrayDeque<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(Node head, Queue<Integer> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(head.value);
            pres(head.lchild, ans);
            pres(head.rchild, ans);
        }
    }

    //deserialize
    public static Node build(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        return preb(queue);
    }

    public static Node preb(Queue<Integer> queue) {
        Integer poll = queue.poll();
        if (poll == null) {
            return null;
        }
        Node head = new Node(poll);
        head.lchild = preb(queue);
        head.rchild = preb(queue);
        return head;
    }


    public static void main(String[] args) {
        Node n1 = new Node(8);
        Node n2 = new Node(5);
        Node n3 = new Node(4);
        Node n4 = new Node(2);
        Node n5 = new Node(3);
        Node n6 = new Node(1);
        Node n7 = new Node(6);
        Node n8 = new Node(9);
        Node n9 = new Node(7);
        Node n10 = new Node(0);
        n1.lchild = n2;
        n1.rchild = n3;
        n2.lchild = n4;
        n2.rchild = n5;
        n3.rchild = n7;
        n5.lchild = n6;
        n7.lchild = n8;
        n8.lchild = n9;
        n8.rchild = n10;

        pre(n1);
        System.out.println();
        pre2(n1);
        System.out.println();
        in(n1);
        System.out.println();
        in2(n1);
        System.out.println();
        post(n1);
        System.out.println();
        post2(n1);
        System.out.println();
        post3(n1);
        System.out.println();
        level(n1);
        System.out.println("-----------------------");
        level2(n1);
    }
}
