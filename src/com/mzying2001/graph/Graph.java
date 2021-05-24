package com.mzying2001.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    private ArrayList<String> vertexList; //存储顶点的集合
    private int[][] edges; //存储图对应的邻接矩阵
    private int numOfEdges; //表示边的数目

    //定义一个bool数组，记录某个节点是否已被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        //测试
        //String[] Vertexes = {"A", "B", "C", "D", "E"};
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Graph graph = new Graph(vertexes.length);
        //循环的添加节点
        for (String value : vertexes) {
            graph.insertVertex(value);
        }

//        //添加边
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        //显示邻接矩阵
        graph.showGraph();

        //测试dfs
        System.out.println("深度遍历");
        //graph.dfs();
        graph.dfs2(0);

        System.out.println("广度优先");
        //graph.bfs();
        graph.bfs2(0);
    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        isVisited = new boolean[n];
    }

    /**
     * 得到第一个邻接节点的下标w
     *
     * @param index
     * @return 如果存在，则返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    private void dfs(boolean[] isVisited, int i) {
        //首先访问该节点
        System.out.print(getValueByIndex(i) + " -> ");
        //将该节点设置为已访问
        isVisited[i] = true;
        //查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行重载，遍历所有节点，并进行dfs
    public void dfs() {
        isVisited = new boolean[getNumOfVertex()];
        //遍历所有的节点进行dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //自己写的
    public void dfs2(boolean[] isVisited, int index) {
        isVisited[index] = true;
        System.out.print(getValueByIndex(index) + " -> ");

        for (int j = 0; j < getNumOfVertex(); j++) {
            if (edges[index][j] > 0 && !isVisited[j])
                dfs2(isVisited, j);
        }
    }

    public void dfs2(int index) {
        dfs2(new boolean[getNumOfVertex()], index);
        System.out.println();
    }

    //对一个节点进行广度优先遍历
    private void bfs(boolean[] isVisited, int i) {
        int u;//表示队列头节点对应地下标
        int w;//邻接节点w
        //队列，记录节点访问地顺序
        LinkedList queue = new LinkedList();
        //访问节点，输出节点地信息
        System.out.print(getValueByIndex(i) + " => ");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            //取出队列地头节点下标
            u = (int) queue.removeFirst();
            //得到第一个邻接节点地下标w
            w = getFirstNeighbor(u);
            while (w != -1) {//找到
                //判断是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " => ");
                    //标记已访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻接点
                w = getNextNeighbor(u, w);//体现出广度优先
            }
        }
    }

    //遍历所有节点，进行广度有限搜索
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //自己写的
    public void bfs2(int index) {
        boolean[] isVisited = new boolean[getNumOfVertex()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);

        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (isVisited[i]) continue;

            isVisited[i] = true;
            System.out.print(getValueByIndex(i) + " => ");

            for (int j = 0; j < getNumOfVertex(); j++) {
                if (edges[i][j] > 0 && !isVisited[j])
                    queue.add(j);
            }
        }
        System.out.println();
    }

    //图中常用的方法
    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i（下标）对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示点的下标，即是第几个顶点
     * @param v2     第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
