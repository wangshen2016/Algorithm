package com.wangxshen.trie;

/**
 * @Author WangShen
 * @Date 2020/9/28 11:05
 * @Version 1.0
 */
public class Trie implements TrieInterface {

    public static class Node {
        public int pass;
        public int end;
        public Node[] nexts;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new Node[26];
        }

    }

    private Node root;

    public Trie() {
        this.root = new Node();
    }

    public int search(String word) {
        if (word == null) {
            return 0;
        }
        char[] str = word.toCharArray();
        Node head = this.root;
        int index = 0;
        for (int i = 0; i < str.length; i++) {
            index = str[i] - 'a';
            if (head.nexts[index] == null) {
                return 0;
            }
            head = head.nexts[index];
        }
        return head.end;
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] str = word.toCharArray();
        Node head = this.root;
        head.pass++;
        int path = 0;
        for (int i = 0; i < str.length; i++) {
            path = str[i] - 'a';
            if (head.nexts[path] == null) {
                head.nexts[path] = new Node();
            }
            head = head.nexts[path];
            head.pass++;
        }
        head.end++;
    }

    public void delete(String word) {
        if (search(word) != 0) {
            char[] str = word.toCharArray();
            Node head = this.root;
            head.pass--;
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (--head.nexts[index].pass == 0) {
                    head.nexts[index] = null;
                    return;
                }
                head = head.nexts[index];
            }
            head.end--;
        }
    }

    public int prefixNumber(String prefix) {
        if (prefix == null) {
            return 0;
        }
        char[] str = prefix.toCharArray();
        Node head = this.root;
        int index = 0;
        for (int i = 0; i < str.length; i++) {
            index = str[i] - 'a';
            if (head.nexts[index] == null) {
                return 0;
            }
            head = head.nexts[index];
        }
        return head.pass;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("f");
        System.out.println(trie.prefixNumber("f"));
    }

}

