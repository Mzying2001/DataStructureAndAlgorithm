package com.mzying2001.algorithm.binarysearchnorecursion;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        System.out.println("index = " + binarySearch(arr, 8));
        System.out.println("index = " + binarySearch2(arr, 8));
    }

    /**
     * 二分查找的非递归实现
     *
     * @param arr    待查找的数组，arr是升序排序
     * @param target 需要查找的数
     * @return 返回对应下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {//说明继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;//需要向左边查找
            } else {
                left = mid + 1;//需要向右边查找
            }
        }
        return -1;
    }

    //自己写的
    public static int binarySearch2(int[] arr, int n) {
        int l = 0;
        int r = arr.length - 1;
        int m;

        while (l <= r) {

            m = (l + r) / 2;

            if (n == arr[m])
                return m;
            else if (n < arr[m])
                r = m - 1;
            else
                l = m + 1;
        }
        return -1;
    }
}
