package com.mzying2001.recursion;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;

        //输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }

        //使用递归回溯给小球找路
//        setWay(map, 1, 1);
        setWay2(map,1,1);

        //输出新的地图
        System.out.println("小球走过的地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路
    //目的地：map[6][5]
    //约定：0为未走过的路，1为墙，2表示走通的路，3表示该位置已经走过，但是走不通
    //策略：下->右->上->左,如果该点走不通,再回溯

    /**
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 返回是否找到路
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { //通路已ok
            return true;
        } else {
            if (map[i][j] == 0) { //该点没有走过
                //策略：下->右->上->左
                map[i][j] = 2; //假定该点可以走通
                if (setWay(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay(map, i - 1, j)) { //向上走
                    return true;
                } else if (setWay(map, i, j - 1)) { //向左走
                    return true;
                } else {
                    //说明该点走不通,是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { //如果map[i][j] != 0, 可能是1, 2, 3
                return false;
            }
        }
    }

    //修改找路策略, 改成 上->右->下->左
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) { //通路已ok
            return true;
        } else {
            if (map[i][j] == 0) { //该点没有走过
                //策略：下->右->上->左
                map[i][j] = 2; //假定该点可以走通
                if (setWay2(map, i - 1, j)) { //向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay2(map, i, j - 1)) { //向左走
                    return true;
                } else {
                    //说明该点走不通,是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { //如果map[i][j] != 0, 可能是1, 2, 3
                return false;
            }
        }
    }

}
