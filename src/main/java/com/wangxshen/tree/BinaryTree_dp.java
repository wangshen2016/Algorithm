package com.wangxshen.tree;

import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

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
     * description: 给定一颗二叉树的头节点，返回这颗二叉树的最大搜索子树的节点数
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
     * @Author:   on2020-12-05 23:09:21
     * @Param: null
     * @return:
     * description: 给定一棵二叉树头节点，返回其最大二叉搜索子树的头节点
     */
    public static class Info_maxBST{
        TreeNode maxSubBSTHead;
        int size;
        int max;
        int min;

        public Info_maxBST(TreeNode maxSubBSTHead, int size, int max, int min) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }

    public static TreeNode getMaxBSTHead(TreeNode node) {
        Info_maxBST maxBSTInfo = getMaxBSTInfo(node);
        return maxBSTInfo.maxSubBSTHead;
    }

    public static Info_maxBST getMaxBSTInfo(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info_maxBST lInfo = getMaxBSTInfo(node.lchild);
        Info_maxBST rInfo = getMaxBSTInfo(node.rchild);

        TreeNode subMaxBSTHead = null;
        int size = 0;
        int max = node.value;
        int min = node.value;

        if (lInfo != null) {
            max = Math.max(lInfo.max, max);
            min = Math.min(lInfo.min, min);
            subMaxBSTHead = lInfo.maxSubBSTHead;
            size = lInfo.size;
        }
        if (rInfo != null) {
            max = Math.max(rInfo.max, max);
            min = Math.min(rInfo.min, min);
            subMaxBSTHead = size > rInfo.size ? subMaxBSTHead : rInfo.maxSubBSTHead;
            size = size > rInfo.size ? size : rInfo.size;
        }
        if ((lInfo == null || lInfo.maxSubBSTHead == node.lchild) &&
            (rInfo == null || rInfo.maxSubBSTHead == node.rchild) &&
            (lInfo == null || lInfo.max < node.value) &&
            (rInfo == null || rInfo.min > node.value)) {
            subMaxBSTHead = node;
            size = (lInfo == null ? 0 : lInfo.size) + (rInfo == null ? 0 : rInfo.size) + 1;
        }
        return new Info_maxBST(subMaxBSTHead, size, max, min);
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

    public static class Info_happy {
        int yes;//该员工参加时的最大happy
        int no;//该员工不参加时的最大happy

        public Info_happy(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static int getHappy(Employee boss) {
        Info_happy infoHappy = process(boss);
        return Math.max(infoHappy.yes, infoHappy.no);
    }

    public static Info_happy process(Employee emp) {
        if (emp.nexts == null) {
            return new Info_happy(emp.happy,0);
        }

        int yes = emp.happy;
        int no = 0;
        for (Employee e : emp.nexts) {
            Info_happy info = process(e);
            yes += info.no;
            no += Math.max(info.yes, info.no);
        }
        return new Info_happy(yes, no);
    }

    /**
     * @Author:   on2020-12-05 22:43:05
     * @Param: null
     * @return:
     * description: 给定一棵二叉树的头节点，返回这棵二叉树是不是满二叉树
     */
    public static class Info_full {
        int size;
        int height;

        public Info_full(int size, int height) {
            this.size = size;
            this.height = height;
        }
    }

    public static boolean isFull(TreeNode node) {
        Info_full info = getInfo(node);
        return Math.pow(2,info.height) - 1 == info.size;
    }

    public static Info_full getInfo(TreeNode node) {
        if (node == null) {
            return new Info_full(0,0);
        }
        Info_full lInfo = getInfo(node.lchild);
        Info_full rInfo = getInfo(node.rchild);
        int height = Math.max(lInfo.height, rInfo.height) + 1;
        int size = lInfo.size + rInfo.size + 1;
        return new Info_full(size, height);
    }

    /**
     * @Author:   on2020-12-05 23:25:22
     * @Param: null
     * @return:
     * description: 给定一棵二叉树头节点，判断是不是完全二叉树
     */
    //非递归方法，使用宽度优先遍历
    public static boolean isCBT(TreeNode node) {
        if (node == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        boolean leaf = false;//用于判断是否已经出现过叶子节点
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            TreeNode l = poll.lchild;
            TreeNode r = poll.rchild;
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    //递归套路
    public static class Info_cbt {
        boolean isCBT;
        boolean isFull;
        int height;

        public Info_cbt(boolean isCBT, boolean isFull, int height) {
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static boolean isCBT2(TreeNode node) {
        Info_cbt cbtInfo = getCBTInfo(node);
        return cbtInfo.isCBT;
    }

    public static Info_cbt getCBTInfo(TreeNode node) {
        if (node == null) {
            return new Info_cbt(true, true, 0);
        }
        Info_cbt lInfo = getCBTInfo(node.lchild);
        Info_cbt rInfo = getCBTInfo(node.rchild);
        int height = Math.max(lInfo.height, rInfo.height) + 1;
        boolean isFull = lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height;
        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else {
            if (lInfo.isCBT && rInfo.isCBT) {
                if (lInfo.isCBT && rInfo.isFull && lInfo.height == rInfo.height + 1) {
                    isCBT = true;
                } else if (lInfo.isFull && rInfo.isCBT && lInfo.height == rInfo.height) {
                    isCBT = true;
                }
            }
        }
        return new Info_cbt(isCBT, isFull, height);
    }
    
    /**
     * @Author:   on2020-12-06 00:05:22
     * @Param: null
     * @return: 
     * description: 给定一棵二叉树的头节点，和另外两个节点a和b，返回a和b的最低工共祖先
     */
    //使用map & set
    public static TreeNode getLowestAncestor(TreeNode root, TreeNode a, TreeNode b) {
        HashMap<TreeNode, TreeNode> fatherMap = new HashMap<>();
        buildMap(root, null, fatherMap);

        HashSet<TreeNode> aSet = new HashSet<>();
        TreeNode cur = a;
        while (cur != null) {
            aSet.add(cur);
            cur = fatherMap.get(cur);
        }

        while (!aSet.contains(b)) {
            b = fatherMap.get(b);
        }
        return b;
    }

    public static void buildMap(TreeNode node, TreeNode father, HashMap<TreeNode, TreeNode> fatherMap) {
        if (node == null) {
            return;
        }
        fatherMap.put(node,father);
        buildMap(node.lchild, node, fatherMap);
        buildMap(node.rchild, node, fatherMap);
    }

    //不使用map和set
    public static class Info_ans{
        TreeNode ans;
        boolean findA;
        boolean findB;

        public Info_ans(TreeNode ans, boolean findA, boolean findB) {
            this.ans = ans;
            this.findA = findA;
            this.findB = findB;
        }
    }

    public static TreeNode getLowestAncestor2(TreeNode node, TreeNode a, TreeNode b) {
        Info_ans ans = findAns(node, a, b);
        return ans.ans;
    }

    public static Info_ans findAns(TreeNode node, TreeNode a, TreeNode b) {
        if (node == null) {
            return new Info_ans(null, false, false);
        }
        Info_ans lInfo = findAns(node.lchild, a, b);
        Info_ans rInfo = findAns(node.rchild, a, b);
        boolean findA = node == a || lInfo.findA || rInfo.findA;
        boolean findB = node == b || lInfo.findB || rInfo.findB;

        TreeNode ans = null;
        if (lInfo.ans != null) {
            ans = lInfo.ans;
        }
        if (rInfo.ans != null) {
            ans = rInfo.ans;
        }
        if (findA && findB && ans == null) {
            ans = node;
        }
        return new Info_ans(ans, findA, findB);
    }

    /**
     * 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试
     * 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试
     * 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试
     * 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试
     * */

    @Before
    public void before() {
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
        Integer[] source = new Integer[] {8,5,2,null,null,7,1,null,null,null,6,null,4,0,null,null,3,null,null};
        TreeNode head = TreeNode.build(source);
        BinaryTree.printNode(head);

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
        Integer[] source = new Integer[] {8,5,2,null,null,7,1,null,null,null,6,null,4,0,null,null,3,null,null};
        TreeNode head = TreeNode.build(source);
        BinaryTree.printNode(head);

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
     * description: 测试获取最大搜索子树头节点及size算法
     */
    @Test
    public void test03() {
        Integer[] source = new Integer[] {8,5,2,null,null,7,1,null,null,null,6,null,4,0,null,null,3,null,null};
        TreeNode head = TreeNode.build(source);
        BinaryTree.printNode(head);
        System.out.println("size: " + getMaxSearchTreeCount(head));
        System.out.println("head: " + getMaxBSTHead(head).value);
        System.out.println("------------------------------");
        Integer[] testSearchTree = new Integer[] {5,1,0,null,null,4,3,null,null,null,6,null,9,null,null};
        TreeNode head01 = TreeNode.build(testSearchTree);
        BinaryTree.printNode(head01);
        System.out.println(getMaxSearchTreeCount(head01));
        System.out.println("head: " + getMaxBSTHead(head01).value);
        System.out.println("------------------------------");
        Integer[] testTree = new Integer[] {8,5,1,0,null,null,4,3,null,null,null,6,null,9,null,null,9,5,6,null,null,null};
        TreeNode head02 = TreeNode.build(testTree);
        BinaryTree.printNode(head02);
        System.out.println(getMaxSearchTreeCount(head02));
        System.out.println("head: " + getMaxBSTHead(head02).value);
        System.out.println("------------------------------");
    }

    /**
     * @Author:   on2020-12-05 22:55:39
     * @Param: null
     * @return:
     * description: 测试判断满二叉树算法
     */
    @Test
    public void test04() {
        Integer[] source = new Integer[] {1,2,4,null,null,5,null,null,3,6,null,null,7,null,null};
        TreeNode head = TreeNode.build(source);
        BinaryTree.printNode(head);
        System.out.println("Is full binary tree ? " + isFull(head));
    }

    /**
     * @Author:   on2020-12-05 23:53:51
     * @Param: null
     * @return:
     * description: 测试判断完全二叉树算法
     */
    @Test
    public void test05() {
        Integer[] source = new Integer[] {8,5,2,null,null,7,1,null,null,null,6,null,4,0,null,null,3,null,null};
        TreeNode head = TreeNode.build(source);
        BinaryTree.printNode(head);
        System.out.println("is cbt1 ? " + isCBT(head));
        System.out.println("is cbt2 ? " + isCBT2(head));

        Integer[] source1 = new Integer[] {1,2,4,null,null,5,null,null,3,6,null,null,7,null,null};
        TreeNode head1 = TreeNode.build(source1);
        BinaryTree.printNode(head1);
        System.out.println("is cbt1 ? " + isCBT(head1));
        System.out.println("is cbt2 ? " + isCBT2(head1));

        Integer[] source2 = new Integer[] {1,2,4,null,null,null,3,null,null};
        TreeNode head2 = TreeNode.build(source2);
        BinaryTree.printNode(head2);
        System.out.println("is cbt1 ? " + isCBT(head2));
        System.out.println("is cbt2 ? " + isCBT2(head2));

        Integer[] source3 = new Integer[] {1,2,4,null,null,5,null,null,3,null,null};
        TreeNode head3 = TreeNode.build(source3);
        BinaryTree.printNode(head3);
        System.out.println("is cbt1 ? " + isCBT(head3));
        System.out.println("is cbt2 ? " + isCBT2(head3));

        Integer[] source4 = new Integer[] {1,2,4,null,null,5,null,null,3,null,6,null,null};
        TreeNode head4 = TreeNode.build(source4);
        BinaryTree.printNode(head4);
        System.out.println("is cbt1 ? " + isCBT(head4));
        System.out.println("is cbt2 ? " + isCBT2(head4));
    }

    /**
     * @Author:   on2020-12-06 10:13:24
     * @Param: null
     * @return:
     * description: 测试最小工共祖先算法
     */
    @Test
    public void test06() {
        Integer[] source = new Integer[] {8,5,2,null,null,7,1,null,null,null,6,null,4,0,null,null,3,null,null};
        TreeNode head = TreeNode.build(source);
        BinaryTree.printNode(head);

        TreeNode[] nodesArray = TreeNode.getNodesArray(head);
        TreeNode a, b;
        int l = nodesArray.length;
        for (int i = 0; i < 10; i++) {
            int indexA = (int)(Math.random() * l);
            int indexB = (int)(Math.random() * l);
            a = nodesArray[indexA];
            b = nodesArray[indexB];
            TreeNode lowestAncestor1 = getLowestAncestor(head, a, b);
            TreeNode lowestAncestor2 = getLowestAncestor2(head, a, b);
            if (lowestAncestor1 == lowestAncestor2) {
                System.out.println("a:" + a.value + ", b:" + b.value + ", ancestor:" + lowestAncestor1.value);
            } else {
                System.out.println("fail");
            }

        }
    }

}
