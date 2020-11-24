package com.wangxshen.lru;

import java.util.HashMap;

/**
 * @Author WangShen
 * @Date 2020/11/24 11:53
 * @Version 1.0
 */
/**
 * @Author:   on2020-11-24 20:45:45
 * @Param: null
 * @return:
 * description: leetcode 145题
 */
class LRUCache {
    DoubleList cache;
    HashMap<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        cache = new DoubleList();
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            int value = map.get(key).v;
            put(key, value);
            return value;
        }
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (map.containsKey(key)) {
            cache.remove(map.get(key));
            cache.addFirst(node);
            map.put(key, node);
        } else {
            if (cache.size == capacity) {
                int k = cache.removeLast().k;
                map.remove(k);
            }
            cache.addFirst(node);
            map.put(key, node);
        }
    }

    static class Node {
        public int k, v;
        public Node pre, next;

        public Node(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

    static class DoubleList{
        Node firstNode;
        Node lastNode;
        int size;

        public DoubleList(){
            firstNode = null;
            lastNode = null;
            size = 0;
        }

        public void addFirst(Node x) {
            if (firstNode == null) {
                firstNode = x;
                lastNode = x;
            } else {
                firstNode.pre = x;
                x.next = firstNode;
                firstNode = x;
            }
            size++;
        }

        public void remove(Node x) {
            if (x == null) {
                return;
            }
            if (x == lastNode) {
                removeLast();
                return;
            }
            if (x == firstNode) {
                firstNode = x.next;
                x.next.pre = null;
                x.next = null;
                size--;
                return;
            }
            Node head = firstNode;
            while (head != x && head != null) {
                head = head.next;
            }
            if (head == null) {
                return;
            }
            head.pre.next = head.next;
            head.next.pre = head.pre;
            head.next = null;
            head.pre = null;
            size--;
        }

        public Node removeLast() {
            if (firstNode == null) {
                return null;
            }
            Node removed = lastNode;
            if (firstNode == lastNode) {
                firstNode = null;
                lastNode = null;
            } else {
                lastNode = removed.pre;
                removed.pre.next = null;
                removed.pre = null;
            }
            size--;
            return removed;
        }

        public int size() {
            return size;
        }
    }

}
