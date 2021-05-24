package com.mzying2001.queue;

public class CircleArrayQueue {
    private int maxSize; //数组的最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int[] arr; //用于存放数据

    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否已满
    public Boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //获取队列数据，出队列
    public int getQueue() {
        if (isEmpty())
            throw new RuntimeException("队列空，无法取出数据");
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，无数据");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列有效数据个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //获取头数据，不取出
    public int headQueue() {
        if (isEmpty())
            throw new RuntimeException("队列为空，无数据");
        return arr[front];
    }
}
