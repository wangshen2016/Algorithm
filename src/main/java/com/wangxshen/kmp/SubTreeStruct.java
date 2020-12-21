package com.wangxshen.kmp;

import com.wangxshen.tree.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author WangShen
 * @Date 2020/12/21 15:58
 * @Version 1.0
 */
public class SubTreeStruct {
    /**
     * @Author:   on2020-12-21 15:58:40
     * @Param: null
     * @return:
     * description: 给出一棵二叉树T1，给出另一棵二叉树T2，
     * 返回T2是否和T1的某子树结构和数值完全相同
     */
    public static boolean isSameStructure(Node T1, Node T2) {
        Object[] array1 = serialize(T1).toArray();
        Object[] array2 = serialize(T2).toArray();

        return kmp(array1, array2);
    }

    private static boolean kmp(Object[] array1, Object[] array2) {
        int i = 0;
        int j = 0;
        int[] next = getNext(array2);

        while (i < array1.length && j < array2.length) {
            if ((array1[i] == null && array2[j] == null)
                    || (array1[i] != null && array2[j] != null)
                    && array1[i].equals(array2[j])) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j];
            }
        }

        return j == array2.length ? true : false;
    }

    public static int[] getNext(Object[] matcher) {
        int[] next = new int[matcher.length];
        next[0] = -1;
        next[1] = 0;

        int i = 2;
        int ch = 0;
        while (i < matcher.length) {
            if ((matcher[i-1] == null && matcher[ch] == null)
                    || (!(matcher[i-1] == null || matcher[ch] == null)
                    && matcher[i-1].equals(matcher[ch]))) {
                next[i++] = ++ch;
            } else if (ch == 0) {
                next[i++] = 0;
            } else {
                ch = next[ch];
            }
        }
        return next;
    }

    public static class Node {
        public Integer val;
        public Node lchild;
        public Node rchild;

        public Node(Integer val) {
            this.val = val;
        }
    }

    //先序遍历序列化二叉树
    public static Queue<Integer> serialize(Node head) {
        if (head == null) {
            return null;
        }
        Queue<Integer> queue = new LinkedList<>();
        pres(head, queue);
        return queue;
    }

    public static void pres(Node node, Queue<Integer> queue) {
        if (node == null) {
            queue.add(null);
        } else {
            queue.add(node.val);
            pres(node.lchild, queue);
            pres(node.rchild, queue);
        }
    }

    //先序遍历反序列化二叉树
    public static Node unSerialize(Queue<Integer> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        return preb(queue);
    }

    public static Node preb(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            throw new RuntimeException("Tree structure is wrong!");
        }
        Integer poll = queue.poll();
        if (poll == null) {
            return null;
        }
        Node node = new Node(poll);
        node.lchild = preb(queue);
        node.rchild = preb(queue);
        return node;
    }

    @Test
    public void test() {
        Integer[] arr1 = new Integer[] {8,5,null,null,4,3,null,null,1,null,null};
        Integer[] arr2 = new Integer[] {4,3,null,null,null};

        Queue<Integer> queue1 = new LinkedList<>();
        for (Integer i : arr1) {
            queue1.add(i);
        }
        System.out.println(queue1);
        Queue<Integer> queue2 = new LinkedList<>();
        for (Integer i : arr2) {
            queue2.add(i);
        }
        System.out.println(queue2);

        Node T1 = unSerialize(queue1);
        System.out.println("T1:");
        pre(T1);

        System.out.println("\nT2:");
        Node T2 = unSerialize(queue2);
        pre(T2);

        System.out.println();

        System.out.println(isSameStructure(T1, T2));

    }

    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        pre(head.lchild);
        pre(head.rchild);
    }
}
