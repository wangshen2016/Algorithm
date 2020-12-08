package com.wangxshen.graph.struct;

import java.util.ArrayList;

/**
 * @Author WangShen
 * @Date 2020/12/8 14:16
 * @Version 1.0
 */
public class Node {
    public int value;
    public int in; // in-degree
    public int out; // out-degree
    public ArrayList<Node> nexts; // 邻接点（有向图的下一个点）
    public ArrayList<Edge> edges; // 边集（有向图中由该点出去的边）

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
