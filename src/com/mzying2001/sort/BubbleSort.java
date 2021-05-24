package com.mzying2001.sort;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {

//        int[] arr = {3, 9, -1, 10, 20};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
//        //测试冒泡排序
//        bubbleSort(arr);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));

        //测试一下冒泡排序的速度O(n^2)，给8万个数据测试
        //创建一个8万个随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * 8000000); //生成[0,8000000]的随机数

        long start = System.currentTimeMillis();

        bubbleSort(arr);
        //System.out.println(Arrays.toString(arr));

        System.out.println("用时：" + (System.currentTimeMillis() - start) + "ms");

//        int temp = 0;
//        boolean flag = false;
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    flag = true;
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//            System.out.println("第 " + (i + 1) + " 趟排序后的数组");
//            System.out.println(Arrays.toString(arr));
//
//            if (!flag) {
//                break;
//            } else {
//                flag = false;
//            }
//        }

//        for (int i = 0, tmp; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - i - 1; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    tmp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = tmp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(arr));

    }

    //将冒泡排序算法封装成一个方法
    public static void bubbleSort(int[] arr) {
        for (int i = 0, tmp; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            if (flag)
                break;
        }
    }

}