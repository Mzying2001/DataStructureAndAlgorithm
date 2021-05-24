package com.mzying2001.queue;

//使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue {
    private int maxSize; //数组的最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int[] arr; //用于存放数据

    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; //指向队列头的前一个位置
        rear = -1; //指向队列尾
    }

    //判断队列是否已满
    public Boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public Boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("数列已满，无法加入");
            return;
        }
        arr[++rear] = n;
    }

    //获取队列数据，出队列
    public int getQueue() {
        if (isEmpty())
            throw new RuntimeException("队列空，无法取出数据");
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，无数据");
            return;
        }
        for (int i = 0; i < arr.length; i++)
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
    }

    //获取头数据，不取出
    public int headQueue() {
        if (isEmpty())
            throw new RuntimeException("队列为空，无数据");
        return arr[front + 1];
    }
}
