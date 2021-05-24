package com.mzying2001.algorithm.dynamic;

import java.util.Arrays;

public class KnapsackProblem {
    public static void main(String[] args) {
        /*
        int[] w = {1, 4, 3}; //物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值
        int m = 4; //背包的容量
        int n = val.length; //物品的个数

        //创建为二维数组
        //v[i][j]表示在前i个物品中能够装入容量为j的背包的物品价值的最大值
        int[][] v = new int[n + 1][m + 1];

        //为了记录放入商品的情况，定义一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行和第一列，在本程序中可以不去处理，因为默认就是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0; //将第一列设置为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; //将第一行设置为0
        }

        //动态规划
        for (int i = 1; i < v.length; i++) { //不处理第一行，i从1开始
            for (int j = 1; j < v[i].length; j++) {//不处理第一列，j从1开始
                //公式
                if (w[i - 1] > j) { //因为i是从1开始的，因此原来公式的w[i]修改成w[i-1]
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存入到背包的情况，不能简单地使用上面的公式，须用if-else来体现
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //输出v查看目前的情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++)
                System.out.printf("%d\t", v[i][j]);
            System.out.println();
        }

        System.out.println("===============");

        //输出最后放入了那些商品
//        //遍历path，这样输出会把所有的放入情况都得到，其实只需要最后的放入
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if (path[i][j] == 1)
//                    System.out.printf("第%d个商品放入到背包\n", i);
//            }
//        }

        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1; //列的最大下标
        while (i > 0 && j > 0) { //从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
        */

        //以下是自己写的

        int[] w = {0, 1, 4, 3}; //物品的重量
        int[] v = {0, 1500, 3000, 2000}; //物品的价值

        int m = 4; //背包最大容量
        int n = w.length - 1; //物品的种类数

        int[][] dp = new int[n + 1][m + 1];

        boolean[][] path = new boolean[n + 1][m + 1]; //记录物品是否放入

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (j < w[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int tmp = v[i] + dp[i - 1][j - w[i]];
                    if (dp[i - 1][j] > tmp) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = tmp;
                        path[i][j] = true;
                    }
                }
            }
        }

        //输出dp
        for (int[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }

        System.out.println("-".repeat(30));

//        for (boolean[] arr : path) {
//            System.out.println(Arrays.toString(arr));
//        }

        //打印出放入的物品
        int i = n;
        int j = m;
        while (i > 0 && j > 0) {
            if (path[i][j]) {
                System.out.printf("放入第 %d 个物品。\n", i);
                j -= w[i];
            }
            i--;
        }
    }
}
