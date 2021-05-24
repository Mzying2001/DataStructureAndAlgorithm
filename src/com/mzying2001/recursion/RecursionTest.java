package com.mzying2001.recursion;

public class RecursionTest {
    public static void main(String[] args) {

//        //通过打印问题回顾递归的调用机制
//        test(4);

        int res = factorial(3);
        System.out.println("res = " + res);

    }

    public static void test(int n) {
        if (n > 2)
            test(n - 1);
        System.out.println("n = " + n);
    }

    public static int factorial(int n) {
        return n < 2 ? 1 : n * factorial(n - 1);
    }
}
