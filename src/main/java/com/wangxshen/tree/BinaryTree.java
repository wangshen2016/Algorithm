package com.wangxshen.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author WangShen
 * @Date 2020/11/24 20:41
 * @Version 1.0
 */

/*
* 本节是二叉树的基本算法
* */
public class BinaryTree {

    /**
     * @Author:   on2020-12-05 14:04:33
     * @Param: null
     * @return:
     * description: 递归先序遍历
     */
    public static void pre(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        pre(head.lchild);
        pre(head.rchild);
    }

    /**
     * @Author:   on2020-12-05 14:04:40
     * @Param: null
     * @return:
     * description: 非递归先序遍历
     */
    public static void pre2(TreeNode head) {
        if (head == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.print(pop.value + " ");
            if (pop.rchild != null) {
                stack.push(pop.rchild);
            }
            if (pop.lchild != null) {
                stack.push(pop.lchild);
            }
        }
    }

    /**
     * @Author:   on2020-12-05 14:04:47
     * @Param: null
     * @return:
     * description: 递归中序遍历
     */
    public static void in(TreeNode head) {
        if (head == null) {
            return;
        }
        in(head.lchild);
        System.out.print(head.value + " ");
        in(head.rchild);
    }

    /**
     * @Author:   on2020-12-05 14:04:53
     * @Param: null
     * @return:
     * description: 非递归中序遍历
     */
    public static void in2(TreeNode head) {
        if (head != null) {
            Deque<TreeNode> stack = new ArrayDeque<>();
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

    /**
     * @Author:   on2020-12-05 14:05:02
     * @Param: null
     * @return:
     * description: 递归后序遍历
     */
    public static void post(TreeNode head) {
        if (head == null) {
            return;
        }
        post(head.lchild);
        post(head.rchild);
        System.out.print(head.value + " ");
    }

    /**
     * @Author:   on2020-12-05 14:05:07
     * @Param: null
     * @return:
     * description: 非递归后序遍历
     */
    public static void post2(TreeNode head) {
        if (head == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
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

    /**
     * @Author:   on2020-12-05 14:05:12
     * @Param: null
     * @return:
     * description: 非递归后序遍历
     */
    public static void post3(TreeNode h) {
        if (h == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode c = null;
        stack.push(h);
        while (!stack.isEmpty()) {
            c = stack.peek();
            if (c.lchild != null && h != c.lchild && h != c.rchild) {
                stack.push(c.lchild);
            } else if (c.rchild != null && h != c.rchild) {
                stack.push(c.rchild);
            } else {
                TreeNode pop = stack.pop();
                System.out.print(pop.value + " ");
                h = pop;
            }
        }
    }

    /**
     * @Author:   on2020-12-05 14:05:19
     * @Param: null
     * @return:
     * description: 层序遍历
     */
    public static void level(TreeNode head) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            System.out.print(poll.value + " ");
            if (poll.lchild != null) {
                queue.add(poll.lchild);
            }
            if (poll.rchild != null) {
                queue.add(poll.rchild);
            }
        }
    }

    /**
     * @Author:   on2020-12-05 14:05:25
     * @Param: null
     * @return:
     * description: 层序遍历，按层打印，并打印每层长度
     */
    public static void level2(TreeNode head) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        int n = 1;
        int count = 0;
        queue.add(head);
        while (!queue.isEmpty()) {
            count = 0;
            for (int i = 0; i < n; i++) {
                TreeNode poll = queue.poll();
                System.out.print(poll.value + " ");
                if (poll.lchild != null) {
                    queue.add(poll.lchild);
                    count++;
                }
                if (poll.rchild != null) {
                    queue.add(poll.rchild);
                    count++;
                }
            }
            System.out.println("length: " + n);
            n = count;
        }
    }

    /**
     * @Author:   on2020-12-05 14:05:31
     * @Param: null
     * @return:
     * description: 先序遍历-序列化
     */
    public static Queue<Integer> serialize(TreeNode head) {
        Queue<Integer> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(TreeNode head, Queue<Integer> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(head.value);
            pres(head.lchild, ans);
            pres(head.rchild, ans);
        }
    }

    /**
     * @Author:   on2020-12-05 14:05:37
     * @Param: null
     * @return:
     * description: 先序遍历-反序列化
     */
    public static TreeNode build(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        return preb(queue);
    }

    public static TreeNode preb(Queue<Integer> queue) {
        Integer poll = queue.poll();
        if (poll == null) {
            return null;
        }
        TreeNode head = new TreeNode(poll);
        head.lchild = preb(queue);
        head.rchild = preb(queue);
        return head;
    }

    /**
     * @Author:   on2020-12-05 14:07:04
     * @Param: null
     * @return:
     * description: 层序遍历-序列化
     */
    public static Queue<Integer> levelSerialze(TreeNode head) {
        if (head == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> ans = new LinkedList<>();

        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll == null) {
                ans.add(null);
                continue;
            } else {
                ans.add(poll.value);
            }
            if (poll.lchild != null) {
                queue.add(poll.lchild);
            } else {
                queue.add(null);
            }
            if (poll.rchild != null) {
                queue.add(poll.rchild);
            } else {
                queue.add(null);
            }
        }
        return ans;
    }

    /**
     * @Author:   on2020-12-05 14:07:11
     * @Param: null
     * @return:
     * description: 层序遍历-反序列化
     */
    public static TreeNode buildByLevel(Queue<Integer> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        TreeNode head = generateNode(queue.poll());
        Queue<TreeNode> nodes = new ArrayDeque<>();
        nodes.add(head);
        while (!nodes.isEmpty()) {
            TreeNode poll = nodes.poll();
            poll.lchild = generateNode(queue.poll());
            poll.rchild = generateNode(queue.poll());
            if (poll.lchild != null) {
                nodes.add(poll.lchild);
            }
            if (poll.rchild != null) {
                nodes.add(poll.rchild);
            }
        }
        return head;
    }

    public static TreeNode generateNode(Integer val) {
        if (val == null) {
            return null;
        }
        return new TreeNode(val);
    }

    /**
     * @Author:   on2020-12-05 14:07:17
     * @Param: null
     * @return:
     * description: 打印二叉树
     */
    public static void printNode(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.println("Binary Tree:");
        inOrder(head, 0, "H", 10);
        System.out.println();
    }

    public static void inOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        inOrder(head.rchild, height + 1, "v", len);
        String value = to + head.value + to;
        int lenm = value.length();
        int lenl = (len-lenm)/2;
        int lenr = len - lenm - lenl;
        value = getSpace(lenl) + value + getSpace(lenr);
        System.out.println(getSpace(height * len) + value);

        inOrder(head.lchild, height+1, "^", len);
    }

    public static String getSpace(int len) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * @Author:   on2020-12-05 13:50:43
     * @Param: null
     * @return:
     * description: 获取中序遍历某节点的后继节点
     */
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

    /**
     * @Author:   on2020-12-05 13:50:52
     * @Param: null
     * @return:
     * description: 获取中序遍历某节点的前驱节点
     */
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

    /**
     * @Author:   on2020-12-05 13:50:22
     * @Param: null
     * @return:
     * description: 打印凹凸折痕，纸条多次对折，观察凹凸折痕规律，并从上到下顺序打印
     */
    public static void printAllFold(int N) {
        if (N < 1) {
            return;
        }
        printNode(1, N, true);
    }

    public static void printNode(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        printNode(i+1, N, true);
        System.out.print(down == true ? "凹" : "凸");
        printNode(i+1, N, false);
    }


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
     * @Author:   on2020-12-05 13:56:27
     * @Param: null
     * @return:
     * description: 测试二叉树遍历
     */
    @Test
    public void test01() {
        System.out.println("\n先序遍历二叉树，递归算法");
        pre(head);

        System.out.println("\n先序遍历二叉树，非递归算法");
        pre2(head);

        System.out.println("\n中序遍历二叉树，递归算法");
        in(head);

        System.out.println("\n中序遍历二叉树，递归算法");
        in2(head);

        System.out.println("\n后序遍历二叉树，递归算法");
        post(head);

        System.out.println("\n后序遍历二叉树，递归算法1，使用两个栈实现");
        post2(head);

        System.out.println("\n后序遍历二叉树，递归算法2，使用一个栈实现");
        post3(head);

        System.out.println("\n层序遍历");
        level(head);

        System.out.println("\n层序遍历并输出每层长度");
        level2(head);
    }

    /**
     * @Author:   on2020-12-05 13:57:09
     * @Param: null
     * @return:
     * description: 测试二叉树序列化
     */
    @Test
    public void test02() {
        System.out.println("\n先序序列化二叉树");
        Queue<Integer> serialize = serialize(head);
        System.out.println(serialize);

        System.out.println("\n先序反序列化");
        head = build(serialize);
        pre(head);

        System.out.println("\n打印二叉树");
        printNode(head);

        System.out.println("\n层序序列化二叉树");
        Queue<Integer> queue = levelSerialze(head);
        System.out.println(queue);

        System.out.println("\n层序反序列化");
        head = buildByLevel(queue);
        level(head);

        System.out.println("\n打印二叉树");
        printNode(head);
    }

    /**
     * @Author:   on2020-12-05 13:54:18
     * @Param: null
     * @return:
     * description: 测试获取前驱、后继节点
     */
    @Test
    public void test03() {
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

    /**
     * @Author:   on2020-12-05 13:58:11
     * @Param: null
     * @return:
     * description: 测试打印凹凸折痕
     */
    @Test
    public void test04() {
        int N = 3;
        printAllFold(N);
    }
}
