package com.wangxshen.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author WangShen
 * @Date 2020/11/24 20:41
 * @Version 1.0
 */
public class BinaryTree {

    //递归先序遍历
    public static void pre(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        pre(head.lchild);
        pre(head.rchild);
    }

    //非递归先序遍历
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

    //递归中序遍历
    public static void in(TreeNode head) {
        if (head == null) {
            return;
        }
        in(head.lchild);
        System.out.print(head.value + " ");
        in(head.rchild);
    }

    //非递归中序遍历
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

    //递归后序遍历
    public static void post(TreeNode head) {
        if (head == null) {
            return;
        }
        post(head.lchild);
        post(head.rchild);
        System.out.print(head.value + " ");
    }

    //非递归后序遍历
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

    //非递归后序遍历
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

    //层序遍历
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

    //层序遍历，按层打印，并打印每层长度
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

    //先序遍历-序列化
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

    //先序遍历-反序列化
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

    //层序遍历-序列化
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

    //层序遍历-反序列化
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

    //打印二叉树
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

    public static TreeNode generateNode(Integer val) {
        if (val == null) {
            return null;
        }
        return new TreeNode(val);
    }


    public static void main(String[] args) {
        Integer[] source = new Integer[] {8,5,2,null,null,7,1,null,null,null,6,null,4,0,null,null,3,null,null};
        TreeNode head = TreeNode.build(source);

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
}
