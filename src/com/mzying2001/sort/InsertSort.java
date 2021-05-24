package com.mzying2001.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * 8000000); //生成[0,8000000]的随机数

        long start = System.currentTimeMillis();

        insertSort(arr);
        //System.out.println(Arrays.toString(arr));

        System.out.println("用时：" + (System.currentTimeMillis() - start) + "ms");

    }

    //插入排序
    public static void insertSort(int[] arr) {

//        for (int i = 1; i < arr.length; i++) {
//            //定义待插入的数
//            int insertVal = arr[i];
//            int insertIndex = i - 1;
//
//            //给insertVal找到插入的位置
//            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
//                arr[insertIndex + 1] = arr[insertIndex];
//                insertIndex--;
//            }
//            //当退出while循环时，说明插入的位置找到
//            arr[insertIndex + 1] = insertVal;
//        }

        if (arr.length < 2)
            return;
        int insertIndex, insertValue;
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertValue = arr[i];
            while (insertIndex >= 0 && arr[insertIndex] > insertValue) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertValue;
        }

    }
}
