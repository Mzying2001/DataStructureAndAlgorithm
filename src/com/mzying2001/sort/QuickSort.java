package com.mzying2001.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {

//        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};
//
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println("arr = " + Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * 8000000); //生成[0,8000000]的随机数

        long start = System.currentTimeMillis();

        quickSort2(arr, 0, arr.length - 1);
        //System.out.println(Arrays.toString(arr));

        System.out.println("用时：" + (System.currentTimeMillis() - start) + "ms");

    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量
        //while循环的目的是让比pivot值小的放到左边，大的放右边
        while (l < r) {
            //在pivot的左边一直找，找到一个大于等于pivot的值，才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找，找到一个小于等于pivot的值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l>=r说明pivot左右两边的值已经按照左边全部是小于等于pivot值，右边全部是大于等于pivot值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后发现arr[l]==pivot的值，r--，前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后发现arr[r]==pivot的值，r++，后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        //如果l==r，必须l++，r--
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }

    }

    //自己写的
    public static void quickSort2(int[] arr, int left, int right) {

        if (left >= right) return;

        int l, r, p;
        l = left;
        r = right;
        p = arr[r];

        while (l < r) {

            while (l < r && arr[l] <= p) l++;
            if (l < r) arr[r--] = arr[l];

            while (l < r && arr[r] >= p) r--;
            if (l < r) arr[l++] = arr[r];

        }

        arr[l] = p; //此处l=r
        quickSort2(arr, left, l - 1);
        quickSort2(arr, r + 1, right);

    }

}
