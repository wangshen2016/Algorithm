package com.wangxshen.graph;

import com.wangxshen.graph.struct.Edge;
import com.wangxshen.graph.struct.Graph;
import com.wangxshen.graph.struct.GraphUtil;
import com.wangxshen.graph.struct.Node;
import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

/**
 * @Author WangShen
 * @Date 2020/12/9 18:38
 * @Version 1.0
 */
public class DijkstraOptimized {
    public static class NodeRecode {
        Node node;
        int distance;

        public NodeRecode(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Node[] heap;
        private int size;
        //记录index
        private HashMap<Node, Integer> indexMap;
        private HashMap<Node, Integer> distanceMap;

        public NodeHeap(int size) {
            this.size = size;
            this.heap = new Node[size];
            this.indexMap = new HashMap<>();
            this.distanceMap = new HashMap<>();
            this.size = 0;
        }

        public void add(Node node, int distance) {
            //node在堆上
            if (inHeap(node)) {
                this.distanceMap.put(node, Math.min(this.distanceMap.get(node), distance));
                heapInsert(node, indexMap.get(node));
            }
            //node没有加入过
            if (!isEntered(node)) {
                this.heap[this.size] = node;
                this.indexMap.put(node, this.size);
                this.distanceMap.put(node, distance);
                heapInsert(node, this.size++);
            }
        }

        public NodeRecode pop() {
            Node pop = this.heap[0];
            swap(0, --this.size);
            this.indexMap.put(pop, -1);
            heapify(0, this.size);
            this.heap[this.size] = null;
            return new NodeRecode(pop, this.distanceMap.get(pop));
        }

        public void heapInsert(Node node, int index) {
            while (index > 0 && this.distanceMap.get(node) < this.distanceMap.get(this.heap[(index-1)/2])) {
                swap(index, (index-1)/2);
                index = (index-1)/2;
            }
        }

        public void heapify(int index, int size) {
            int lchild = 2 * index + 1;
            while (lchild < size
                    && this.distanceMap.get(this.heap[index]) > this.distanceMap.get(this.heap[lchild])) {
                int minChild = lchild + 1 < size
                        && this.distanceMap.get(this.heap[lchild+1]) < this.distanceMap.get(this.heap[lchild])
                        ? lchild + 1 : lchild;

                swap(index, minChild);
                index = minChild;
                lchild = 2 * index + 1;
            }
        }

        public void swap(int i, int j) {
            Node tmp = this.heap[i];
            this.heap[i] = this.heap[j];
            this.heap[j] = tmp;
            this.indexMap.put(this.heap[i], j);
            this.indexMap.put(this.heap[j], i);
        }

        public boolean isEntered(Node node) {
            return distanceMap.containsKey(node);
        }

        public boolean inHeap(Node node) {
            return isEntered(node) && distanceMap.get(node) != -1;
        }
    }

    public static HashMap<Node, Integer> dijkstra(Node start, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.add(start, 0);

        HashMap<Node, Integer> result = new HashMap<>();

        while (nodeHeap.size != 0) {
            NodeRecode recode = nodeHeap.pop();
            Node fromNode = recode.node;
            int distance = recode.distance;

            for (Edge edge : fromNode.edges) {
                Node toNode = edge.to;
                nodeHeap.add(toNode, distance + edge.weight);
            }

            result.put(fromNode, distance);
        }

        return result;
    }

    @Test
    public void test() {
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

        for (Node node : graph.nodes.values()) {
            HashMap<Node, Integer> distance = GraphAlgorithm.dijkstra(node);
            HashMap<Node, Integer> distance2 = dijkstra(node, graph.nodes.size());
            Set<Node> nodes = distance.keySet();
            for (Node nd : nodes) {
                if (!distance.get(nd).equals(distance2.get(nd))) {
                    System.out.println("--------fail----------");
                    System.out.println(distance);
                    System.out.println("<><><>");
                    System.out.println(distance2);
                    return;
                }
            }
        }
        System.out.println("success");
    }
}
