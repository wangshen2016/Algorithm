package com.wangxshen.tree;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * @Author WangShen
 * @Date 2020/11/27 15:32
 * @Version 1.0
 */

/*
* 本节是二叉树的树形dp问题
* */
public class BinaryTree_dp {


    /**
     * @Author:   on2020-12-05 14:09:21
     * @Param: null
     * @return:
     * description: 给定一颗二叉树的头节点，返回这颗二叉树是不是平衡二叉树(左树平衡，右树平衡，左右树高度差不超过1)
     */
    public static class Info_balance {
        public int height;
        public boolean isBalance = false;

        public Info_balance() { }

        public Info_balance(int height, boolean isBalance) {
            this.height = height;
            this.isBalance = isBalance;
        }
    }

    public static boolean isBalance(TreeNode node) {
        return getBalanceInfo(node).isBalance;
    }

    public static Info_balance getBalanceInfo(TreeNode node) {
        if (node == null) {
            return new Info_balance(0, true);
        }
        Info_balance lInfo = getBalanceInfo(node.lchild);
        Info_balance rInfo = getBalanceInfo(node.rchild);
        int height = Math.max(lInfo.height, rInfo.height) + 1;
        boolean isBalanced = lInfo.isBalance == true &&
                rInfo.isBalance == true &&
                Math.abs(lInfo.height - rInfo.height) <= 1 ? true : false;
        return new Info_balance(height, isBalanced);
    }
    
    /**
     * @Author:   on2020-12-05 14:27:08 
     * @Param: null
     * @return: 
     * description: 给定一颗二叉树的头节点，任何两个节点之间都存在距离，返回整颗二叉树的最大距离
     */
    public static class Info_distance {
        int height;
        public int max_distence;

        public Info_distance() { }

        public Info_distance(int height, int max_distence) {
            this.height = height;
            this.max_distence = max_distence;
        }
    }

    public int getMaxDistance(TreeNode node) {
        return getMaxDistanceInfo(node).max_distence;
    }

    public Info_distance getMaxDistanceInfo(TreeNode node) {
        if (node == null) {
            return new Info_distance(0, 0);
        }
        Info_distance lInfo = getMaxDistanceInfo(node.lchild);
        Info_distance rInfo = getMaxDistanceInfo(node.rchild);

        int height = Math.max(lInfo.height, rInfo.height) + 1;
        int distanceWithX = lInfo.height + rInfo.height + 1;
        int distanceWithoutX = Math.max(lInfo.max_distence, rInfo.max_distence);
        int max_distance = distanceWithoutX > distanceWithX ? distanceWithoutX : distanceWithX;
        return new Info_distance(height, max_distance);
    }
    
    /**
     * @Author:   on2020-12-05 14:47:53
     * @Param: null
     * @return: 
     * description: 给定一颗二叉树的头节点，返回这颗二叉树的最大搜索子树的头节点
     */
    public static class Info_maxSearchTree{
        boolean isAllBST = false;
        int size;
        int max;
        int min;

        public Info_maxSearchTree(boolean isAllBST, int size, int max, int min) {
            this.isAllBST = isAllBST;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }

    public static int getMaxSearchTreeCount(TreeNode node) {
        return getMaxSearchCount(node).size;
    }

    public static Info_maxSearchTree getMaxSearchCount(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info_maxSearchTree lInfo = getMaxSearchCount(node.lchild);
        Info_maxSearchTree rInfo = getMaxSearchCount(node.rchild);
        boolean isAllBST = false;
        int size = 0;
        int max = node.value;
        int min = node.value;
        if (lInfo != null) {
            max = Math.max(max, lInfo.max);
            min = Math.min(min, lInfo.min);
        }
        if (rInfo != null) {
            max = Math.max(max, rInfo.max);
            min = Math.min(min, rInfo.min);
        }

        if ((lInfo == null || lInfo.max < node.value) &&
                (rInfo == null || rInfo.min > node.value) &&
            (lInfo == null || lInfo.isAllBST) &&
                (rInfo == null || rInfo.isAllBST)) {
            isAllBST = true;
            size = (lInfo == null ? 0 : lInfo.size) + (rInfo == null ? 0 : rInfo.size) + 1;
        } else {
            size = Math.max((lInfo == null ? 0 : lInfo.size), (rInfo == null ? 0 : rInfo.size));
        }

        return new Info_maxSearchTree(isAllBST, size, max, min);
    }

    /**
     * @Author:   on2020-12-05 20:04:00
     * @Param: null
     * @return:
     * description: 派对的最大快乐值问题
     * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
     * 1. 如果某个员工来了，那他的直属下级都不会来
     * 2. 派对的整体快乐值是到场的员工快乐值的累加
     * 3. 你的目标是让派对的整体快乐值最大
     * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值
     */
    //员工定义
    public static class Employee {
        public int happy;//员工的快乐值
        List<Employee> nexts;//员工的直属下级
    }

    /**
     * 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试
     * 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试
     * 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试
     * 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试
     * */
    Integer[] source = null;
    TreeNode head = null;

    @Before
    public void before() {
        source = new Integer[] {8,5,2,null,null,7,1,null,null,null,6,null,4,0,null,null,3,null,null};
        head = TreeNode.build(source);
        BinaryTree.printNode(head);
        System.out.println("------------------Enviroment has been prepared.-------------------");
    }

    /**
     * @Author:   on2020-12-05 14:17:17
     * @Param: null
     * @return:
     * description: 测试平衡二叉树判定
     */
    @Test
    public void test01() {
        Integer[] balancedTree = new Integer[] {1,2,4,8,null,null,null,5,null,9,null,null,3,6,null,null,7,10,null,null,null};
        TreeNode head01 = TreeNode.build(balancedTree);
        System.out.println(isBalance(head));
        System.out.println(isBalance(head01));
    }

    /**
     * @Author:   on2020-12-05 14:40:00
     * @Param: null
     * @return:
     * description: 测试获取最大距离算法
     */
    @Test
    public void test02() {
        Integer[] testTree = new Integer[] {1,2,null,null,3,4,5,6,7,null,null,null,null,null,8,null,9,null,10,null,11,null,null};
        TreeNode head01 = TreeNode.build(testTree);
        System.out.println("max distence of tree1: " + getMaxDistance(head));
        BinaryTree.printNode(head01);
        System.out.println("max distence of tree2: " + getMaxDistance(head01));
    }

    /**
     * @Author:   on2020-12-05 19:24:56
     * @Param: null
     * @return:
     * description: 测试获取最大搜索子树算法
     */
    @Test
    public void test03() {
        System.out.println(getMaxSearchTreeCount(head));
        System.out.println("------------------------------");
        Integer[] testSearchTree = new Integer[] {5,1,0,null,null,4,3,null,null,null,6,null,9,null,null};
        TreeNode head01 = TreeNode.build(testSearchTree);
        BinaryTree.printNode(head01);
        System.out.println(getMaxSearchTreeCount(head01));
        System.out.println("------------------------------");
        Integer[] testTree = new Integer[] {8,5,1,0,null,null,4,3,null,null,null,6,null,9,null,null,9,5,6,null,null,null};
        TreeNode head02 = TreeNode.build(testTree);
        BinaryTree.printNode(head02);
        System.out.println(getMaxSearchTreeCount(head02));
        System.out.println("------------------------------");
    }
}
