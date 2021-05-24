package com.mzying2001.sort;

import javax.swing.*;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {

//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        mergeSort(arr, 0, arr.length - 1, new int[arr.length]); //归并排序需要额外的空间
//
//        System.out.println("归并排序后 = " + Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * 8000000); //生成[0,8000000]的随机数

        long start = System.currentTimeMillis();

        mergeSort2(arr, 0, arr.length - 1, new int[arr.length]);
        //System.out.println(Arrays.toString(arr));

        System.out.println("用时：" + (System.currentTimeMillis() - start) + "ms");

    }

    //分+和的方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //到合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并的方法
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; //初始化i，左边有序序列的初始索引
        int j = mid + 1; //初始化j，右边有序序列的初始索引
        int t = 0; //指向temp数组的当前索引

        //（一）
        //先把左右两边的（有序）数据按照规则填充到temp数组
        //直到左右两边的有序序列又一边处理完毕为止
        while (i <= mid && j <= right) { //继续
            //如果左边有序序列的当前元素小于等于右边有序序列的当前元素
            //即将左边的当前元素拷贝到temp数组
            //然后t++，i++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else { //反之，将右边有序序列的当前元素填充到temp
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //（二）
        //把有剩余数据的一边的数据一次填充到temp
        while (i <= mid) { //说明左边有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) { //说明右边有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t++;
            j++;
        }

        //（三）
        //将temp数组的元素拷贝到arr
        //注意并不是每一次都拷贝所有
        t = 0;
        int tempLeft = left; //
        while (tempLeft <= right) { //第一次合并时tempLeft=0，right=1
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }

    //自己写的
    public static void mergeSort2(int[] arr, int start, int end, int[] temp) {

        if (start >= end) return;

        int mid = (start + end) / 2;
        mergeSort2(arr, start, mid, temp);
        mergeSort2(arr, mid + 1, end, temp);

        int i = start;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= end) {
            if (arr[i] < arr[j])
                temp[t++] = arr[i++];
            else
                temp[t++] = arr[j++];
        }

        while (i <= mid)
            temp[t++] = arr[i++];
        while (j <= end)
            temp[t++] = arr[j++];

        int index = end;
        while (index >= start)
            arr[index--] = temp[--t];

    }

}
