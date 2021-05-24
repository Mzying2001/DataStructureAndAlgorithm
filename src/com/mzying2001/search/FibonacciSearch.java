package com.mzying2001.search;

import java.util.Arrays;

public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {

        int[] arr = {1, 8, 10, 89, 1000};
        System.out.println("index = " + fibSearch(arr, 1));

    }

    //因为后面mid=low+F(k-1)-1，需要先获取一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 编写斐波那契查找算法
     * 使用非递归的方式编写算法
     *
     * @param a   数组
     * @param key 需要查找的关键码（值）
     * @return 返回对应的下标，如果没有返回-1
     */
    public static int fibSearch(int[] a, int key) {

        int low = 0;
        int high = a.length - 1;
        int k = 0; //斐波那契分割数值下标
        int mid = 0; //存放mid值

        int[] f = fib(); //获取斐波那契数列

        //获取到斐波那契分割数值下标
        while (high > f[k] - 1)
            k++;
        //因为f[k]值可能大于数组长度，因此，需要使用Arrays类，构造一个新的数组，并指向temp
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //需要使用a数组的最后的数填充temp
        for (int i = high + 1; i < temp.length; i++)
            temp[i] = a[high];

        //使用while循环处理，查找数key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //说明应该继续向数组的前面查找（左边）
                high = mid - 1;
                //为什么是k--
                //说明：
                //1.全部元素 = 前面的元素 + 后面的元素
                //2.f[k] = f[k-1] + f[k-2]
                //因为前面有f[k-1]个元素，所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1]的前面继续查找k--
                //即下次循环mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) { //说明应该向数组的右边查找
                low = mid + 1;
                //为什么是k -= 2
                //说明：
                //1.全部元素 = 前面的元素 + 后面的元素
                //2.f[k] = f[k-1] + f[k-2]
                //3.因为后面有f[k-2]个元素，所以可以继续拆分f[k-1] = f[k-3] + f[k-4]
                //4.即在f[k-2]的前面进行查找k -= 2
                //5.即下次循环mid = f[k-1-2]
                k -= 2;
            } else { //找到
                //需要确定返回的是那个下标
                if (mid <= high)
                    return mid;
                else
                    return high;
            }
        }
        return -1;
    }

}
