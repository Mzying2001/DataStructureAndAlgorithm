package com.mzying2001.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {

        //测试ArrayStack是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;

        Scanner sc = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 添加数据到栈（入栈）");
            System.out.println("pop:  从栈取出数据（出栈）");
            System.out.println("请输入你的选择");
            key = sc.next();

            switch (key) {
                case "show":
                    stack.list();
                    break;

                case "push":
                    try {
                        System.out.println("请输入一个数");
                        stack.push(sc.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "pop":
                    try {
                        System.out.printf("出栈的数据是 %d\n", stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "exit":
                    loop = false;
                    break;
            }
        }

        System.out.println("程序退出");
        sc.close();

    }
}

//定义一个类，表示栈
class ArrayStack {
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈，数据放在该数组
    private int top = -1; //表示栈顶，初始化为-1

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断栈是否慢
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        //先判断栈是否空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，无数据。");
        }
        return stack[top--];
    }

    //显示栈的情况（遍历栈），遍历时需要从栈顶显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，无数据。");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }
}
