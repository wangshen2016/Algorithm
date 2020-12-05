package com.wangxshen.tree.avlTree;

import com.wangxshen.tree.TreeNode;
import org.junit.Test;

/**
 * @Author WangShen
 * @Date 2020/12/5 14:56
 * @Version 1.0
 */
public class AVLTree {

    /**
     * @Author:   on2020-12-05 17:11:08
     * @Param: null
     * @return:
     * description: 构造一棵AVL树
     */
    public static AVLNode build(Integer[] source) {
        AVLNode root = null;
        for (Integer key : source) {
            root = insert(root, key);
        }
        return root;
    }

    /**
     * @Author:   on2020-12-05 16:18:07
     * @Param: null
     * @return:
     * description: 插入一条数据
     */
    public static AVLNode insert(AVLNode node, int key) {
        if (node == null) {
            return new AVLNode(key);
        }

        if (key > node.key) {
            node.right = insert(node.right, key);
            if (height(node.right) - height(node.left) == 2) {
                if (key > node.right.key) {
                    node = rrRotation(node);
                } else {
                    node = rlRotation(node);
                }
            }
        } else if (key < node.key) {
            node.left = insert(node.left, key);
            if (height(node.left) - height(node.right) == 2) {
                if (key < node.left.key) {
                    node = llRotation(node);
                } else {
                    node = lrRotation(node);
                }
            }
        } else {
            //更新node的值
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    /**
     * @Author:   on2020-12-05 16:18:19
     * @Param: null
     * @return:
     * description: 删除一条数据
     */
    public static AVLNode delete(AVLNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.left = delete(node.left, key);
            if (height(node.right) - height(node.left) == 2) {
                AVLNode r = node.right;
                if (height(r.left) > height(r.right)) {
                    node = rlRotation(node);
                } else {
                    node = rrRotation(node);
                }
            }
        } else if (key > node.key) {
            node.right = delete(node.right, key);
            if (height(node.left) - height(node.right) == 2) {
                AVLNode l = node.left;
                if (height(l.left) > height(l.right)) {
                    node = llRotation(node);
                } else {
                    node = lrRotation(node);
                }
            }
        } else {
            if (node.left != null && node.right != null) {
                if (height(node.left) > height(node.right)) {
                    AVLNode max = maxNode(node.left);
                    node.key = max.key;
                    node.left = delete(node.left, max.key);
                } else {
                    AVLNode min = minNode(node.right);
                    node.key = min.key;
                    node.right = delete(node.right, min.key);
                }
            } else {
                node = (node.left == null) ? node.left : node.right;
                return node;
            }
        }
        return node;
    }

    /**
     * @Author:   on2020-12-05 17:07:35
     * @Param: null
     * @return:
     * description: 查询树上是否有key值
     */
    public static boolean search(AVLNode node, int key) {
        while (node != null) {
            if (key == node.key) {
                return true;
            }
            if (key < node.key) {
                node = node.left;
                continue;
            }
            if (key > node.key) {
                node = node.right;
                continue;
            }
        }
        return false;
    }

    /**
     * @Author:   on2020-12-05 16:18:29
     * @Param: null
     * @return:
     * description: 获取某节点为根节点的树的高度
     */
    public static int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * @Author:   on2020-12-05 16:19:10
     * @Param: null
     * @return:
     * description: 获取某节点为根节点的树的最大节点
     */
    public static AVLNode maxNode(AVLNode node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * @Author:   on2020-12-05 16:19:53
     * @Param: null
     * @return:
     * description: 获取某节点为根节点的树的最小节点
     */
    public static AVLNode minNode(AVLNode node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    /**
     * @Author:   on2020-12-05 16:21:01
     * @Param: null
     * @return:
     * description: RR类型旋转
     */
    public static AVLNode rrRotation(AVLNode node) {
        AVLNode right = node.right;

        node.right = right.left;
        right.left = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        right.height = Math.max(height(right.left), height(right.right)) + 1;
        return right;
    }

    /**
     * @Author:   on2020-12-05 16:21:01
     * @Param: null
     * @return:
     * description: RL类型旋转
     */
    public static AVLNode rlRotation(AVLNode node) {
        node.right = llRotation(node.right);
        return rrRotation(node);
    }

    /**
     * @Author:   on2020-12-05 16:21:01
     * @Param: null
     * @return:
     * description: LL类型旋转
     */
    public static AVLNode llRotation(AVLNode node) {
        System.out.println("-------" + node.key);
        AVLNode left = node.left;

        node.left = left.right;
        left.right = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        left.height = Math.max(height(left.left), height(left.right)) + 1;


        return left;
    }

    /**
     * @Author:   on2020-12-05 16:21:01
     * @Param: null
     * @return:
     * description: LR类型旋转
     */
    public static AVLNode lrRotation(AVLNode node) {
        node.left = rrRotation(node.left);
        return llRotation(node);
    }

    /**
     * @Author:   on2020-12-05 17:17:09
     * @Param: null
     * @return:
     * description: 打印avl树
     */
    public static void printNode(AVLNode head) {
        if (head == null) {
            return;
        }
        System.out.println("Binary Tree:");
        inOrder(head, 0, "H", 10);
        System.out.println();
    }

    public static void inOrder(AVLNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        inOrder(head.right, height + 1, "v", len);
        String value = to + head.key + to;
        int lenm = value.length();
        int lenl = (len-lenm)/2;
        int lenr = len - lenm - lenl;
        value = getSpace(lenl) + value + getSpace(lenr);
        System.out.println(getSpace(height * len) + value);

        inOrder(head.left, height+1, "^", len);
    }

    public static String getSpace(int len) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * @Author:   on2020-12-05 17:17:55
     * @Param: null
     * @return:
     * description: 测试
     */
    @Test
    public void test01() {
        Integer[] source = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        AVLNode root = build(source);
        printNode(root);

        root = delete(root, 4);
        printNode(root);

    }

}
