package com.mzying2001.linkedlist;

import java.util.Stack;

//演示栈的基本使用
public class TestStack {
    public static void main(String[] args) {

        Stack<String> stack = new Stack();
        //入栈
        stack.add("Jack");
        stack.add("Tom");
        stack.add("Smith");

        //出栈
        //Smith, Tom, Jack
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }
}
