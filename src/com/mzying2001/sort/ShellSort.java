package com.mzying2001.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {

//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(arr);

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * 8000000); //生成[0,8000000]的随机数

        long start = System.currentTimeMillis();

        shellSort3(arr);
        //System.out.println(Arrays.toString(arr));

        System.out.println("用时：" + (System.currentTimeMillis() - start) + "ms");

    }

    //使用逐步推导的方式来编写希尔排序
    public static void shellSort(int[] arr) {

//        //希尔排序的第一轮排序
//        for (int i = 5; i < arr.length; i++) {
//            //遍历各组中所有的元素（共有5组，每组有俩元素），步长时5
//            int tmp;
//            for (int j = i - 5; j >= 0; j -= 5) {
//                //如果当前这个元素大于加上步长后的元素，说明要交换
//                if (arr[j] > arr[j + 5]) {
//                    tmp = arr[j];
//                    arr[j] = arr[j + 5];
//                    arr[j + 5] = tmp;
//                }
//            }
//        }
//        System.out.println("希尔排序一轮后 = " + Arrays.toString(arr));
//
//        //希尔排序的第二轮排序
//        for (int i = 2; i < arr.length; i++) {
//            //遍历各组中所有的元素（共有5组，每组有俩元素），步长时5
//            int tmp;
//            for (int j = i - 2; j >= 0; j -= 2) {
//                //如果当前这个元素大于加上步长后的元素，说明要交换
//                if (arr[j] > arr[j + 2]) {
//                    tmp = arr[j];
//                    arr[j] = arr[j + 2];
//                    arr[j + 2] = tmp;
//                }
//            }
//        }
//        System.out.println("希尔排序二轮后 = " + Arrays.toString(arr));
//
//        //希尔排序的第三轮排序
//        for (int i = 1; i < arr.length; i++) {
//            //遍历各组中所有的元素（共有5组，每组有俩元素），步长时5
//            int tmp;
//            for (int j = i - 1; j >= 0; j -= 1) {
//                //如果当前这个元素大于加上步长后的元素，说明要交换
//                if (arr[j] > arr[j + 1]) {
//                    tmp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = tmp;
//                }
//            }
//        }
//        System.out.println("希尔排序三轮后 = " + Arrays.toString(arr));

        //根据前面的分析，使用循环处理
        int temp = 0;
        //int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("希尔排序第" + (++count) + "轮后 = " + Arrays.toString(arr));
        }

    }

    //对交换式的希尔排序进行优化=>位移法
    public static void shellSort2(int[] arr) {
        int tmp, j;
        int gap = arr.length;
        while ((gap /= 2) > 0) {
            //从第gap个元素逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                j = i;
                tmp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && tmp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给tmp找到了位置
                    arr[j] = tmp;
                }
            }
        }
    }

    //自己写的
    public static void shellSort3(int[] arr) {
        int gap = arr.length;
        int insertIndex, insertValue;
        while ((gap /= 2) > 0) {
            for (int i = gap; i < arr.length; i++) {
                insertIndex = i - gap;
                insertValue = arr[i];
                while (insertIndex >= 0 && arr[insertIndex] > insertValue) {
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }
                arr[insertIndex + gap] = insertValue;
            }
        }
    }

}
