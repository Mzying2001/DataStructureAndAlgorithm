package com.mzying2001.sparsearray;

public class SparseArray {
    public static void main(String[] args) {

        //创建一个原始的二维数组11*11
        //0表示没有棋子，1表示黑子，2表示蓝子

        int[][] chessArr1 = new int[11][11];

        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;

        System.out.println("原始的二维数组：");
        for (var raw : chessArr1) {
            for (var data : raw) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将为伪数组转化为稀疏数组
        //1、线遍历二维数组，得到非0的个数
        int sum = 0;
        for (var col : chessArr1) {
            for (var data : col) {
                if (data != 0)
                    sum++;
            }
        }

        //2、创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非零的值存放到sparseArr中
        for (int i = 0, count = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for (var col : sparseArr) {
            System.out.printf("%d\t%d\t%d\t\n", col[0], col[1], col[2]);
        }

        //将稀疏数组恢复成原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组：");
        for (var raw : chessArr2) {
            for (var data : raw) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }
}
