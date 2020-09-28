package com.wangxshen.trie;

import java.util.HashMap;

/**
 * @Author WangShen
 * @Date 2020/9/28 16:17
 * @Version 1.0
 */
public class Trie2 implements TrieInterface {

    public static class Node {
        int pass = 0;
        int end = 0;
        HashMap<Integer, Node> nexts;

        public Node() {
            this.nexts = new HashMap<>();
        }
    }

    private Node root;

    public Trie2() {
        this.root = new Node();
    }

    @Override
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        char[] chars = word.toCharArray();
        Node head = this.root;
        head.pass++;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = (int)chars[i];
            if (!head.nexts.containsKey(index)) {
                return 0;
            }
            head = head.nexts.get(index);
        }
        return head.end;
    }

    @Override
    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] chars = word.toCharArray();
        Node head = this.root;
        head.pass++;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = (int)chars[i];
            if (head.nexts.get(index) == null) {
                head.nexts.put(index, new Node());
            }
            head = head.nexts.get(index);
            head.pass++;
        }
        head.end++;
    }

    @Override
    public void delete(String word) {
        if (search(word) != 0) {
            char[] chars = word.toCharArray();
            Node head = this.root;
            head.pass--;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = (int)chars[i];
                if (--head.nexts.get(index).pass == 0) {
                    head.nexts.remove(index);
                    return;
                }
                head = head.nexts.get(index);
            }
            head.end--;
        }
    }

    @Override
    public int prefixNumber(String prefix) {
        if (prefix == null) {
            return 0;
        }
        char[] chars = prefix.toCharArray();
        Node head = this.root;
        head.pass++;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = (int)chars[i];
            if (!head.nexts.containsKey(index)) {
                return 0;
            }
            head = head.nexts.get(index);
        }
        return head.pass;
    }

}
