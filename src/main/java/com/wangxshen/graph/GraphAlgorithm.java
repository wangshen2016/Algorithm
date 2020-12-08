package com.wangxshen.graph;

import com.wangxshen.graph.struct.Edge;
import com.wangxshen.graph.struct.Graph;
import com.wangxshen.graph.struct.GraphUtil;
import com.wangxshen.graph.struct.Node;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author WangShen
 * @Date 2020/12/8 14:22
 * @Version 1.0
 */
public class GraphAlgorithm {

    /**
     * @Author:   on2020-12-08 16:01:33
     * @Param: null
     * @return:
     * description: 宽度优先遍历
     */
    public static void bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.print(poll.value + " ");
            for (Node nd : poll.nexts) {
                if (!set.contains(nd)) {
                    set.add(nd);
                    queue.add(nd);
                }
            }
        }
    }

    /**
     * @Author:   on2020-12-08 16:01:46
     * @Param: null
     * @return:
     * description: 深度优先遍历
     */
    public static void dfs(Node node) {
        Deque<Node> stack = new ArrayDeque<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.print(node.value + " ");
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (Node nd : pop.nexts) {
                if (!set.contains(nd)) {
                    stack.push(pop);
                    stack.push(nd);
                    set.add(nd);
                    System.out.print(nd.value + " ");
                }
            }
        }
    }

    @Test
    public void testDBfs() {
        Integer[][] matrix = new Integer[][] {
                {0,0,1},
                {0,0,5},
                {0,1,0},
                {0,1,2},
                {0,1,3},
                {0,2,1},
                {0,2,4},
                {0,3,2},
                {0,3,4},
                {0,4,5},
                {0,5,6}
        };

        Graph graph = GraphUtil.createGraph(matrix);
        Node node = graph.nodes.get(0);
        dfs(node);
        System.out.println();
        bfs(node);
    }
    
    /**
     * @Author:   on2020-12-08 16:10:01
     * @Param: null
     * @return: 
     * description:
     * 拓扑排序：
     * 1. 在图中找出所有入度为0的点输出
     * 2. 把所有入度为0的点在图中删除，继续找入度为0的点，重复1，2的过程
     * 3. 图的所有点都被删除后，依次输出的顺序就是拓扑排序
     * 要求：
     * 有向图且其中没有环
     */

    public static ArrayList<Node> topologicSort(Graph graph) {
        Queue<Node> zeroInNode = new LinkedList<>();
        HashMap<Node, Integer> inMap = new HashMap<>();

        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInNode.add(node);
            }
        }

        ArrayList<Node> ans = new ArrayList<>();

        while (!zeroInNode.isEmpty()) {
            Node poll = zeroInNode.poll();
            ans.add(poll);
            for (Node node : poll.nexts) {
                inMap.put(node, inMap.get(node) - 1);
                if (inMap.get(node) == 0) {
                    zeroInNode.add(node);
                }
            }
        }
        return ans;
    }

    @Test
    public void testTopologicSort() {
        Integer[][] matrix = new Integer[][] {
                {0,1,2},
                {0,1,3},
                {0,2,3},
                {0,2,4},
                {0,3,5},
                {0,4,5},
                {0,5,6}
        };

        Graph graph = GraphUtil.createGraph(matrix);
        ArrayList<Node> nodes = topologicSort(graph);

        nodes.stream().forEach(node -> System.out.print(node.value + " "));
    }

    /**
     * @Author:   on2020-12-08 16:39:27
     * @Param: null
     * @return:
     * description: 无向图的最小生成树问题 - Kruskal算法
     * 使用并查集
     * 下面是有向图的算法，无向图就向边集增加相反的边即可
     */
    public static class NodeUnionSet {
        public HashMap<Node, Node> fatherMap;
        public HashMap<Node, Integer> sizeMap;

        public NodeUnionSet(Set<Node> nodes) {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node getFather(Node node) {
            Deque<Node> stack = new ArrayDeque<>();
            Node cur = node;
            while (cur != fatherMap.get(cur)) {
                stack.push(cur);
                cur = fatherMap.get(cur);
            }
            while (!stack.isEmpty()) {
                Node pop = stack.pop();
                fatherMap.put(pop, cur);
            }
            return cur;
        }

        public boolean isSameSet(Node a, Node b) {
            if (a == null || b == null) {
                return false;
            }
            return getFather(a) != getFather(b) ? false : true;
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node fatherA = getFather(a);
            Node fatherB = getFather(b);
            if (fatherA == fatherB) {
                return;
            }
            int sizeA = sizeMap.get(fatherA);
            int sizeB = sizeMap.get(fatherB);

            if (sizeA > sizeB) {
                fatherMap.put(fatherB, fatherA);
                sizeMap.put(fatherA, sizeA + sizeB);
                sizeMap.remove(fatherB);
            } else {
                fatherMap.put(fatherA, fatherB);
                sizeMap.put(fatherB, sizeA + sizeB);
                sizeMap.remove(fatherA);
            }
        }
    }

    public static Set<Edge> kruskal(Graph graph) {
        NodeUnionSet nodeUnionSet = new NodeUnionSet(graph.nodes.values().stream().collect(Collectors.toSet()));

        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        for (Edge edge : graph.edges) {
            minHeap.add(edge);
        }

        Set<Edge> result = new HashSet<>();

        while (!minHeap.isEmpty()) {
            Edge poll = minHeap.poll();
            if (! nodeUnionSet.isSameSet(poll.from, poll.to)) {
                result.add(poll);
                nodeUnionSet.union(poll.from, poll.to);
            }
        }

        return result;
    }

    /**
     * @Author:   on2020-12-08 18:07:39
     * @Param: null
     * @return:
     * description: 无向图的最小生成树问题 - prim算法
     */

    public static Set<Edge> prim(Graph graph) {
        HashSet<Node> nodeSet = new HashSet<>();

        PriorityQueue<Edge> minHeap = new PriorityQueue<>((e1, e2) -> e1.weight-e2.weight);

        Set<Edge> result = new HashSet<>();

        HashSet<Edge> edgeSet = new HashSet<>();//去重用

        for (Node node : graph.nodes.values()) {
            nodeSet.add(node);
            for (Edge edge : node.edges) {
                minHeap.add(edge);
                edgeSet.add(edge);
            }

            while (!minHeap.isEmpty()) {
                Edge poll = minHeap.poll();
                if (!nodeSet.contains(poll.to)) {
                    nodeSet.add(poll.to);
                    result.add(poll);
                    for (Edge edge : poll.to.edges) {
                        if (!edgeSet.contains(edge)) {
                            minHeap.add(edge);
                            edgeSet.add(edge);
                        }
                    }
                }
            }
            break;
        }

        return result;
    }

    @Test
    public void testKruscalAndPrim() {
        //生成一个无向图
        Integer[][] matrix = new Integer[][] {
                {10,0,1},{10,1,0},
                {20,0,5},{20,5,0},
                {50,1,3},{50,3,1},
                {10,2,1},{10,1,2},
                {20,2,4},{20,4,2},
                {30,3,2},{30,2,3},
                {40,3,4},{40,4,3},
                {50,4,5},{50,5,4},
                {100,5,6},{100,6,5}
        };

        Graph graph = GraphUtil.createGraph(matrix);
        System.out.println("------orignal--------");
        graph.edges.stream().forEach(edge -> {
            System.out.println(edge);
        });
        System.out.println("-------kruscal-------");
        Set<Edge> result = kruskal(graph);
        result.stream().forEach(edge -> {
            System.out.println(edge);
        });
        System.out.println("-------prim-------");
        Set<Edge> result2 = prim(graph);
        result2.stream().forEach(edge -> {
            System.out.println(edge);
        });
    }



}
