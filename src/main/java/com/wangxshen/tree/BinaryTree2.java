package com.wangxshen.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author WangShen
 * @Date 2020/11/27 15:32
 * @Version 1.0
 */
public class BinaryTree2 {

    public static void main(String[] args) {
        Integer[] source = new Integer[] {8,5,2,null,null,7,1,null,null,null,6,null,4,0,null,null,3,null,null};
        TreeNode head = TreeNode.build(source);
        BinaryTree.printNode(head);
    }
}
