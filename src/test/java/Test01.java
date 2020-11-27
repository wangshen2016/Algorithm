import org.junit.Test;
import org.junit.experimental.theories.Theories;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @Author WangShen
 * @Date 2020/11/3 22:59
 * @Version 1.0
 */
public class Test01 {

    static class Node{
        public int value;
        public Node lchild = null;
        public Node rchild = null;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node lchild, Node rchild) {
            this.value = value;
            this.lchild = lchild;
            this.rchild = rchild;
        }
    }

    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.push(head);
        while (!queue.isEmpty()) {
            Node pop = queue.pop();
            System.out.print(pop.value + " ");
            if (pop.rchild != null) {
                queue.push(pop.rchild);
            }
            if (pop.lchild != null) {
                queue.push(pop.lchild);
            }
        }
        System.out.println("\n---------------------------");
    }

    public static void in(Node head) {
        if (head == null) {
            return;
        }
        Deque<Node> queue = new ArrayDeque<>();
        while (!queue.isEmpty() || head != null) {
            if (head != null) {
                queue.push(head);
                head = head.lchild;
            } else {
                Node pop = queue.pop();
                System.out.print(pop.value + " ");
                head = pop.rchild;
            }
        }
        System.out.println("\n--------------------------");
    }

    public static void post1(Node head) {
        if (head == null) {
            return;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.push(head);
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.peek();
            if (cur.lchild != null && cur.lchild != head && cur.rchild != head) {
                queue.push(cur.lchild);
            } else if (cur.rchild != null && cur.rchild != head) {
                queue.push(cur.rchild);
            } else {
                Node pop = queue.pop();
                System.out.print(pop.value + " ");
                head = pop;
            }
        }
    }

    @Test
    public void test() {
        Node n1 = new Node(8);
        Node n2 = new Node(5);
        Node n3 = new Node(4);
        Node n4 = new Node(2);
        Node n5 = new Node(3);
        Node n6 = new Node(1);
        Node n7 = new Node(6);
        Node n8 = new Node(9);
        Node n9 = new Node(7);
        Node n10 = new Node(0);
        n1.lchild = n2;
        n1.rchild = n3;
        n2.lchild = n4;
        n2.rchild = n5;
        n3.rchild = n7;
        n5.lchild = n6;
        n7.lchild = n8;
        n8.lchild = n9;
        n8.rchild = n10;

        System.out.println("pre-order");
        pre(n1);
        System.out.println("in-order");
        in(n1);
        System.out.println("post-order");
        post1(n1);

    }
}
