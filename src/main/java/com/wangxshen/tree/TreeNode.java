package com.wangxshen.tree;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author WangShen
 * @Date 2020/11/27 15:51
 * @Version 1.0
 */
public class TreeNode {
    public int value;
    public TreeNode lchild = null;
    public TreeNode rchild = null;
    public TreeNode parent = null;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(int value, TreeNode parent) {
        this.value = value;
        this.parent = parent;
    }

    public TreeNode(int value, TreeNode lchild, TreeNode rchild) {
        this.value = value;
        this.lchild = lchild;
        this.rchild = rchild;
    }

    public TreeNode(int value, TreeNode lchild, TreeNode rchild, TreeNode parent) {
        this.value = value;
        this.lchild = lchild;
        this.rchild = rchild;
        this.parent = parent;
    }

    //反序列化方法build一棵二叉树
    public static TreeNode build(Integer[] source) {
        if (source == null || source.length == 0) {
            return null;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (Integer v : source) {
            queue.offer(v);
        }
        TreeNode head = preBuild(queue, null);
        return head;
    }

    public static TreeNode preBuild(Queue<Integer> queue, TreeNode parent) {
        Integer poll = queue.poll();
        if (poll == null) {
            return null;
        }
        TreeNode head = new TreeNode(poll, parent);
        head.lchild = preBuild(queue, head);
        head.rchild = preBuild(queue, head);
        return head;
    }

    public static Queue<TreeNode> getNodes(TreeNode head) {
        Queue<TreeNode> queue = new LinkedList<>();
        prePush(head, queue);
        return queue;
    }

    public static void prePush(TreeNode head, Queue<TreeNode> queue) {
        if (head == null) {
            return;
        }
        queue.add(head);
        prePush(head.lchild, queue);
        prePush(head.rchild, queue);
    }
}
