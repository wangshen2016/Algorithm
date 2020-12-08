package com.wangxshen.graph.struct;

/**
 * @Author WangShen
 * @Date 2020/12/8 14:23
 * @Version 1.0
 */
public class GraphUtil {

    /**
     * @Author:   on2020-12-08 14:24:14
     * @Param: null
     * @return:
     * description: 输入一个 N*3 矩阵，如[[3,1,2],[2,1,3],[2,2,3],[1,3,3]],
     * 每一行[x,y,z]中，x代表边的权重，y代表from，z代表to，
     * 根据这个矩阵，创建出一个Graph结构
     */
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (Integer[] row : matrix) {
            Integer weight = row[0];
            Integer from = row[1];
            Integer to = row[2];

            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            fromNode.out++;
            fromNode.nexts.add(toNode);
            toNode.in++;
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }
}
