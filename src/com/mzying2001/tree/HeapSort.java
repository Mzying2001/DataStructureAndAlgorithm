package com.mzying2001.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {

        //要求将数组进行升序排列
//        int[] arr = {4, 6, 8, 5, 9};
//        heapSort(arr);

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * 8000000); //生成[0,8000000]的随机数

        long start = System.currentTimeMillis();

        heapSort(arr);
        //System.out.println(Arrays.toString(arr));

        System.out.println("用时：" + (System.currentTimeMillis() - start) + "ms");

    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr) {

        int temp = 0;

//        System.out.println("堆排序！！！");

//        //分步完成
//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第一次：" + Arrays.toString(arr));
//
//        adjustHeap(arr, 0, arr.length);
//        System.out.println("第二次：" + Arrays.toString(arr));

        //最终代码
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

//        System.out.println("arr = " + Arrays.toString(arr));

    }

    /**
     * 将一个数组（二叉树）调整成一个大顶堆
     * 功能：完成将以 i 对应的非叶子节点的数调整成大顶堆
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，length是在逐渐地减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {

        int temp = arr[i]; //先取出当前元素的值，保存在临时变量

        //开始调整
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {

            if (k + 1 < length && arr[k] < arr[k + 1]) { //说明左子节点的值小于右子节点的值
                k++; //k指向右子节点
            }

            if (arr[k] > temp) { //如果子节点大于父节点
                arr[i] = arr[k]; //把较大的值赋给当前节点
                i = k; //i指向k继续循环比较
            } else {
                break;
            }

        }
        //当for循环结束后，已经将以i为父节点的数的最大值放到了最顶上（局部）
        arr[i] = temp; //将temp值放到调整后的位置

    }

}
