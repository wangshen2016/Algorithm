package com.wangxshen.unionset;

import java.util.*;

/**
 * @Author WangShen
 * @Date 2020/12/7 11:16
 * @Version 1.0
 */
//并查集的实现及相关算法
public class UnionSet<V> {

    public static class Node<V>{
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    private HashMap<V, Node<V>> nodes;
    private HashMap<Node<V>, Node<V>> parents;
    private HashMap<Node<V>, Integer> sizeMap;

    public UnionSet(List<V> list) {
        nodes = new HashMap<>();
        parents = new HashMap<>();
        sizeMap = new HashMap<>();

        for (V v : list) {
            Node<V> vNode = new Node<>(v);
            nodes.put(v, vNode);
            parents.put(vNode, vNode);
            sizeMap.put(vNode, 1);
        }
    }

    public Node<V> getFather(Node<V> node) {
        Node<V> cur = node;
        while (cur != parents.get(cur)) {
            cur = parents.get(cur);
        }
        return cur;
    }

    public boolean isSameSet(Node<V> a, Node<V> b) {
        if (getFather(a) == getFather(b)) {
            return true;
        }
        return false;
    }

    public void unionSet(Node<V> a, Node<V> b) {
        if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
            return;
        }
        Node<V> fatherA = getFather(a);
        Node<V> fatherB = getFather(b);
        if (fatherA == fatherB) {
            return;
        }

        if (sizeMap.get(fatherA) > sizeMap.get(fatherB)) {
            parents.put(fatherB, fatherA);
            sizeMap.put(fatherA, sizeMap.get(fatherA) + sizeMap.get(fatherB));
            sizeMap.remove(fatherB);
        } else {
            parents.put(fatherA, fatherB);
            sizeMap.put(fatherB, sizeMap.get(fatherA) + sizeMap.get(fatherB));
            sizeMap.remove(fatherA);
        }

    }
}
