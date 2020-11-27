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

    //获取中序遍历某节点的后继节点
    public static TreeNode getInSuccessor(TreeNode head) {
        if (head == null) {
            return null;
        }
        //如果有右子树，则其后继节点就是右孩子的最左节点
        if (head.rchild != null) {
            head = head.rchild;
            while (head.lchild != null) {
                head = head.lchild;
            }
            return head;
        } else {
            // 1. 由该节点向上看，如果该节点是左孩子，那么其后继就是其父节点，
            // 2. 如果该节点x是右孩子，那么就一直向上看，
            // 直到某个节点a的左孩子是该节点的某个父系节点b时（b = x.parent...parent）,
            // 节点a就是x的后继节点
           TreeNode parent = head.parent;

           while (parent != null && parent.lchild != head) {
               head = parent;
               parent = head.parent;
           }
           return parent;
        }
    }

    //获取中序遍历某节点的前驱节点
    public static TreeNode getPrecursor(TreeNode head) {
        if (head == null) {
            return null;
        }
        //如果有左子树，则其后继节点就是左孩子的最右节点
        if (head.lchild != null) {
            head = head.lchild;
            while (head.rchild != null) {
                head = head.rchild;
            }
            return head;
        } else {
            // 1. 由该节点向上看，如果该节点是右孩子，那么其后继就是其父节点，
            // 2. 如果该节点x是左孩子，那么就一直向上看，
            // 直到某个节点a的右孩子是该节点的某个父系节点b时（b = x.parent...parent）,
            // 节点a就是x的后继节点
            TreeNode parent = head.parent;

            while (parent != null && parent.rchild != head) {
                head = parent;
                parent = head.parent;
            }
            return parent;
        }
    }

    public static void main(String[] args) {
        Integer[] source = new Integer[] {8,5,2,null,null,7,1,null,null,null,6,null,4,0,null,null,3,null,null};
        TreeNode head = TreeNode.build(source);
        BinaryTree.printNode(head);
        System.out.println("-------------------------------------");
        Queue<TreeNode> nodes = TreeNode.getNodes(head);
        while (!nodes.isEmpty()) {
            TreeNode poll = nodes.poll();
            TreeNode inSuccessor = getInSuccessor(poll);
            TreeNode inPredecessor = getPrecursor(poll);
            System.out.println("value: " + poll.value + " precursor: " + (inPredecessor == null ? "--" : inPredecessor.value));
            System.out.println("value: " + poll.value + " successor: " + (inSuccessor == null ? "--" : inSuccessor.value));
            System.out.println();
        }
    }
}
