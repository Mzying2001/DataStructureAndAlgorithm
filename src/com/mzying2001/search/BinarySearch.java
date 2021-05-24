package com.mzying2001.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//注意：二分查找的前提是该数组是有序的
public class BinarySearch {
    public static void main(String[] args) {

        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};

//        int resIndex = binarySearch2(arr, 0, arr.length - 1, 89);
//        System.out.println("resIndex = " + resIndex);

        List<Integer> resIndexList = binarySearch3(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndexList = " + resIndexList);

    }

    //二分查找算法

    /**
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        //当left>right时，说明递归整个数组，但没有找到
        if (left > right) return -1;

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }

    //自己写的
    public static int binarySearch2(int[] arr, int start, int end, int value) {

        if (start == end) return arr[start] == value ? start : -1;

        int midIndex = (start + end) / 2;

        if (value < arr[midIndex]) return binarySearch2(arr, start, midIndex - 1, value);
        else if (value > arr[midIndex]) return binarySearch2(arr, midIndex + 1, end, value);
        else return midIndex;

    }

    //可以返回多个index
    public static List<Integer> binarySearch3(int[] arr, int left, int right, int findVal) {

        //当left>right时，说明递归整个数组，但没有找到
        if (left > right) return new ArrayList<>();

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            //向右递归
            return binarySearch3(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            //向左递归
            return binarySearch3(arr, left, mid - 1, findVal);
        } else {
            List<Integer> resIndexList = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal)
                    break;
                resIndexList.add(temp);
                temp--;
            }

            resIndexList.add(mid);

            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal)
                    break;
                resIndexList.add(temp);
                temp++;
            }

            return resIndexList;
        }

    }

}
