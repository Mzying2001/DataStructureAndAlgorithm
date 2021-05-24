package com.mzying2001.queue;

import java.util.Scanner;

public class QueueDemo {
    public static void main(String[] args) {

        //创建一个队列
        //ArrayQueue aq = new ArrayQueue(3);
        CircleArrayQueue aq = new CircleArrayQueue(4);

        Scanner sc = new Scanner(System.in);

        Boolean loop = true;
        while (loop) {

            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add):  添加数据到队列");
            System.out.println("g(get):  从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");

            char key = sc.next().charAt(0);

            switch (key) {

                case 's':
                    aq.showQueue();
                    break;

                case 'a':
                    System.out.print("输入一个数：");
                    aq.addQueue(sc.nextInt());
                    break;

                case 'g':
                    try {
                        System.out.printf("取出的数据是：%d\n", aq.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'h':
                    try {
                        System.out.printf("队列头的数据是：%d\n", aq.headQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'e':
                    loop = false;
                    break;

                default:
                    break;

            }

        }

        System.out.println("程序退出");
        sc.close();

    }
}