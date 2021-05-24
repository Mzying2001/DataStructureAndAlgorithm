package com.mzying2001.algorithm.prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试图是否创建成功
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示，用10000表示两点不联通
        final int M = 10000;
        int[][] weight = new int[][]{
                {M, 5, 7, M, M, M, 2},
                {5, M, M, 9, M, M, 3},
                {7, M, M, M, 8, M, M},
                {M, 9, M, M, M, 4, M},
                {M, M, 8, M, M, 5, 4},
                {M, M, M, 4, 5, M, 6},
                {2, 3, M, M, 4, 6, M}
        };
        //创建MGraph对象
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        //输出
        minTree.showGraph(graph);
        //测试普利姆算法
        minTree.prim(graph, 0);
    }
}

//创建最小生成树
class MinTree {
    /**
     * 创建图对的邻接矩阵
     *
     * @param graph  图对象
     * @param verxs  图对应顶点的个数
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {//顶点
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的方法
    public void showGraph(MGraph graph) {
        for (var item : graph.weight) {
            System.out.println(Arrays.toString(item));
        }
    }

    /**
     * 编写prim算法，得到最小生成树
     *
     * @param graph 图
     * @param v     表示从图的第几个第几个顶点生成
     */
    public void prim(MGraph graph, int v) {
        //标记节点是否被访问过
        int[] visited = new int[graph.verxs];
        //把当前节点标记为已访问
        visited[v] = 1;
        //h1和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000; //将minWeight初始化成较大的数，后面遍历过程中会被替换
        for (int k = 1; k < graph.verxs; k++) { //因为有graph.verxs个顶点，prim算法后有graph.verxs-1边
            //这里是确定每一次生成的子图和哪个节点的距离最近
            for (int i = 0; i < graph.verxs; i++) { //i节点表示被访问过的节点
                for (int j = 0; j < graph.verxs; j++) { //j节点表示没有访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换minWeight（寻找已访问过的节点和未访问过的节点的权值最小的边）
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小
            System.out.printf("边<%c, %c> 权值：%d\n", graph.data[h1], graph.data[h2], minWeight);
            //将当前找到的节点标记为已访问
            visited[h2] = 1;
            //minWeight重新设置成大值
            minWeight = 10000;
        }
    }
}

class MGraph {
    int verxs; //表示图的节点个数
    char[] data; //保存节点的数据
    int[][] weight; //存放边，即邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}