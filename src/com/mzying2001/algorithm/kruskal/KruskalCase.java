package com.mzying2001.algorithm.kruskal;

import java.util.Arrays;

public class KruskalCase {

    private int edgeNum; //边的个数
    private char[] vertexs; //顶点数组
    private int[][] matrix; //邻接矩阵

    //使用INF表示两个顶点不连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        //克鲁斯卡尔算法的邻接矩阵
        int[][] matrix = {
                       /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}
        };

        //创建一个KruskalCase对象实例
        KruskalCase kruskalCase = new KruskalCase(vertex, matrix);
        kruskalCase.print();
        kruskalCase.kruskal();
    }

    //构造器
    public KruskalCase(char[] vertexs, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen = vertexs.length;

        //初始化顶点
        this.vertexs = new char[vlen];
        System.arraycopy(vertexs, 0, this.vertexs, 0, vertexs.length);

        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, vlen);
        }

        //统计边的条数
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal() {
        int index = 0; //表示最后结果数组的索引
        int[] ends = new int[edgeNum]; //用于保存已有最小生成树中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图中所有的边的集合
        EData[] edges = getEdges();
        System.out.println("图的边的集合：" + Arrays.toString(edges) + "，共" + edges.length);

        //按照边的权值大小进行排序（从小到大）
        sortEdges(edges);

        //遍历edges数组。将边添加到最小生成树中时判断是否形成了回路，如果没有就加入rets
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点（起点）
            int p1 = getPosition(edges[i].start);
            //获取到第i条边的第二个顶点
            int p2 = getPosition(edges[i].end);

            //获取p1这个顶点在已有的最小生成树中的终点
            int m = getEnd(ends, p1);
            //获取p2这个顶点在已有的最小生成树中的终点
            int n = getEnd(ends, p2);
            //是否构成回路
            if (m != n) { //没有构成回路
                ends[m] = n; //设置m在已有最小生成树中的终点
                rets[index++] = edges[i]; //有一条边加入到rets数组
            }
        }

        //统计并打印最小生成树，输出rets
        System.out.println("最小生成树为：");
        for (int i = 0; i < index; i++)
            System.out.println(rets[i]);
    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵位：");
        for (int[] arr : matrix) {
            for (int i : arr)
                System.out.printf("%12d\t", i);
            System.out.println();
        }
    }

    /**
     * 对边进行排序处理
     *
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges) {
        Arrays.sort(edges);
    }

    /**
     * @param ch 顶点的值
     * @return 返回ch顶点对应的下标，若找不到则返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch)
                return i;
        }
        return -1;
    }

    /**
     * 功能：获取图中的边，放到EData[]数组中
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能：获取下标为 i 的顶点的终点，用于后面判断两个顶点的终点是否相同
     *
     * @param ends 记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中逐步形成的
     * @param i    表示传入的顶点对应的下标
     * @return 返回下标为 i 的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

//创建一个类EData，它的对象实力就表示一条边
class EData implements Comparable<EData> {
    char start; //边的一端
    char end; //边的另一端
    int weight; //边的权值

    //构造器
    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edata{" +
                "<" + start +
                ", " + end +
                "> = " + weight +
                '}';
    }

    @Override
    public int compareTo(EData o) {
        return this.weight - o.weight;
    }
}
