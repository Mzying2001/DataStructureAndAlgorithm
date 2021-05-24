package com.mzying2001.search;

import java.util.Arrays;

public class InsertValueSraech {
    public static void main(String[] args) {

        int[] arr = new int[100];
        for (int i = 0; i < 100; i++)
            arr[i] = i + 1;

        int index = insertValueSearch2(arr, 0, arr.length - 1, 100);
        //int index = insertValueSearch2(new int[]{1, 2, 3, 4, 5, 8755, 8888}, 0, 6, 5);
        System.out.println("index = " + index);

        //System.out.println(Arrays.toString(arr));

    }

    //编写插值查找算法
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        System.out.println("CALL");

        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) return -1;

        //求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }

    //自己写的
    public static int insertValueSearch2(int[] arr, int start, int end, int value) {

        System.out.println("call~~");

        if (start > end || value < arr[0] || value > arr[arr.length - 1]) return -1;

        int midIndex = start + (end - start) * (value - arr[start]) / (arr[end] - arr[start]);

        if (value < arr[midIndex]) return insertValueSearch2(arr, start, midIndex - 1, value);
        else if (value > arr[midIndex]) return insertValueSearch2(arr, midIndex + 1, end, value);
        else return midIndex;

    }

}
