package com.mzying2001.sort;

import java.util.*;

public class RadixSort {
    public static void main(String[] args) {

//        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * 8000000); //生成[0,8000000]的随机数

        long start = System.currentTimeMillis();

        radixSort2(arr);
        //System.out.println(Arrays.toString(arr));

        System.out.println("用时：" + (System.currentTimeMillis() - start) + "ms");

    }

    //基数排序方法
    public static void radixSort(int[] arr) {

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1.二维数组包含十个一维数组
        //2.为了防止在放入数的时候数据溢出，则每个一维数组（桶），大小定为arr.length
        //3.很明显，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际存放了多少个数据，定义一个一维数组来记录各个桶的数据个数
        //可以这样理解
        //bucketElementCounts[0]记录的就是bucket[0]桶的放入数据的个数
        int[] bucketElementCounts = new int[10];

//        //第一轮排序（针对每个元素的个位进行处理）
//        for (int j = 0; j < arr.length; j++) {
//            //取出每个元素的个位的值
//            int digitOfElement = arr[j] % 10;
//            //放入到对应的桶中
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        //按照桶的顺序依次取出，放入原来数组
//        int index = 0;
//        //遍历每一个桶，并将桶中的数据放入到原来的数组
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            //如果桶中有数据，才放入到原数组
//            if (bucketElementCounts[k] != 0) {
//                //循环该桶即第k个桶（即第k个一维数组），放入
//                for (int l = 0; l < bucketElementCounts[k]; l++) {
//                    //取出元素放入到arr
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//            //第一轮处理后需要将每一个bucketElementCounts[k]置零！！！
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("第一轮，对个位的排序处理arr = " + Arrays.toString(arr));
//
//        /*==========================================================================*/
//
//        //第二轮排序（针对每个元素的十位进行处理）
//        for (int j = 0; j < arr.length; j++) {
//            //取出每个元素的十位的值
//            int digitOfElement = arr[j] / 10 % 10;
//            //放入到对应的桶中
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        //按照桶的顺序依次取出，放入原来数组
//        index = 0;
//        //遍历每一个桶，并将桶中的数据放入到原来的数组
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            //如果桶中有数据，才放入到原数组
//            if (bucketElementCounts[k] != 0) {
//                //循环该桶即第k个桶（即第k个一维数组），放入
//                for (int l = 0; l < bucketElementCounts[k]; l++) {
//                    //取出元素放入到arr
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("第二轮，对个位的排序处理arr = " + Arrays.toString(arr));
//
//        /*==========================================================================*/
//
//        //第三轮排序（针对每个元素的百位进行处理）
//        for (int j = 0; j < arr.length; j++) {
//            //取出每个元素的百位的值
//            int digitOfElement = arr[j] / 100 % 10;
//            //放入到对应的桶中
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        //按照桶的顺序依次取出，放入原来数组
//        index = 0;
//        //遍历每一个桶，并将桶中的数据放入到原来的数组
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            //如果桶中有数据，才放入到原数组
//            if (bucketElementCounts[k] != 0) {
//                //循环该桶即第k个桶（即第k个一维数组），放入
//                for (int l = 0; l < bucketElementCounts[k]; l++) {
//                    //取出元素放入到arr
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("第三轮，对个位的排序处理arr = " + Arrays.toString(arr));

        //根据前面的推到内容，可以得到技术排序的位置
        //1.得到数组中最大的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        //得到最大的数是几位数
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序依次取出，放入原来数组
            int index = 0;
            //遍历每一个桶，并将桶中的数据放入到原来的数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶（即第k个一维数组），放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }

    }

    //自己写的
    public static void radixSort2(int[] arr) {

        int[][] bucket = new int[10][arr.length];
        int[] elementNumOfBucket = new int[10];

        int max = 0;
        for (int i : arr) {
            if (i > max) max = i;
        }
        int maxLen = (max + "").length();

        for (int i = 0, n = 1, temp; i < maxLen; i++, n *= 10) {

            for (int item : arr) {
                temp = item / n % 10;
                bucket[temp][elementNumOfBucket[temp]++] = item;
            }

            int index = 0;
            for (int j = 0; j < 10; j++) {
                if (elementNumOfBucket[j] != 0) {
                    for (int k = 0; k < elementNumOfBucket[j]; k++)
                        arr[index++] = bucket[j][k];
                }
                elementNumOfBucket[j] = 0;
            }
        }


    }

    //自己写的（较慢）
    public static void radixSort3(int[] arr) {

        List<Queue<Integer>> bucket = new ArrayList<>();

        for (int i = 0; i < 10; i++)
            bucket.add(new ArrayDeque<>());

        int max = 0;
        for (int i : arr) {
            if (i > max) max = i;
        }
        int maxLen = (max + "").length();

        for (int t = 0, n = 1; t < maxLen; t++, n *= 10) {

            for (int i : arr)
                bucket.get(i / n % 10).add(i);

            int index = 0;
            for (var item : bucket) {
                while (!item.isEmpty())
                    arr[index++] = item.poll();
            }

        }

    }

}
