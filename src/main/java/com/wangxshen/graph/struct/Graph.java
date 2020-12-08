package com.wangxshen.graph.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author WangShen
 * @Date 2020/12/8 14:16
 * @Version 1.0
 */
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
