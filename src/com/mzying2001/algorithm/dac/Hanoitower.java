package com.mzying2001.algorithm.dac;

public class Hanoitower {
    public static void main(String[] args) {
//        hanoitower(5, 'A', 'B', 'C');
        hanoi(9);
    }

//    //汉诺塔的移动方法
//    //使用分治算法
//    public static void hanoitower(int num, char a, char b, char c) {
//        if (num == 1) {
//            System.out.printf("第1个盘从 %c -> %c\n", a, c);
//        } else {
//            hanoitower(num - 1, a, c, b);
//            System.out.printf("第%d个盘从 %c -> %c\n", num, a, c);
//            hanoitower(num - 1, b, a, c);
//        }
//    }

    //自己写的
    public static void hanoi(int n, char a, char b, char c) {
        if (n == 1) {
            System.out.printf("第1个盘： %c => %c\n", a, c);
        } else {
            hanoi(n - 1, a, c, b);
            System.out.printf("第%d个盘： %c => %c\n", n, a, c);
            hanoi(n - 1, b, a, c);
        }
    }

    public static void hanoi(int n) {
        hanoi(n, 'A', 'B', 'C');
    }
}
