package com.wangxshen.tree.avlTree;


/**
 * @Author WangShen
 * @Date 2020/12/5 14:54
 * @Version 1.0
 */
public class AVLNode{
    int height = 1;
    int key;
    AVLNode left;
    AVLNode right;

    public AVLNode() {
    }

    public AVLNode(int key) {
        this.key = key;
    }

    public AVLNode(int key, AVLNode left, AVLNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
}
