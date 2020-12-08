package com.wangxshen.graph.struct;

/**
 * @Author WangShen
 * @Date 2020/12/8 14:16
 * @Version 1.0
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
