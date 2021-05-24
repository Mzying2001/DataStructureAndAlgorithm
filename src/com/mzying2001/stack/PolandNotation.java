package com.mzying2001.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        //完成一个中缀表达式转成后缀表达式的功能

        String expression = "4/2*2";
        List<String> infixExpressionList = toInfixExpression(expression);
        System.out.println("中缀表达式对应的List：" + infixExpressionList);

        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List：" + suffixExpressionList);

        System.out.printf("expression = %d", calculate(suffixExpressionList));

//        //先定义一个逆波兰表达式
//        //(3+4)*5-6 => 3 4 + 5 * 6 -
//        //为了方便，逆波兰表达式的数字和符号使用空格隔开
//        String suffixExpression = "3 4 + 5 * 6 -";
//        //思路
//        //1.先将“3 4 + 5 * 6 -” => 放到ArrayList中
//        //2.将ArrayList传递给一个方法，遍历ArrayList 配合栈完成计算
//
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println("rpnList = " + rpnList);
//
//        int res = calculate(rpnList);
//        System.out.println("计算的结果=" + res);

    }

    //方法：将得到的中缀表达式转化成对应的后缀表达式List
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义俩栈
        Stack<String> s1 = new Stack<String>(); //符号栈
        //因为s2这个栈在整个转换过程中无pop操作，且最终须逆序输出，可直接使用ArrayList
        List<String> s2 = new ArrayList<String>(); //储存中间结果的List2

        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入到s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //!!!将"("弹出s1，消除小括号
            } else {
                //当item的优先级小于等于栈顶的运算符的优先级
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        //将s1中剩余的运算符加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;

    }

    //方法：将中缀表达式转成对应的List
    public static List<String> toInfixExpression(String s) {
        //定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0; //指针，用于遍历中缀表达式s
        String str; //对多位数的拼接
        char c; //每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，就需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else { //如果是一个书需要考虑多位数
                str = ""; //先将str置成“”
                while (i < s.length() && ((c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57)) {
                    str += c; //拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //将逆波兰表达式依次将数据放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String item : ls) {
            //这里使用一个正则表达式来取出数
            if (item.matches("\\d+")) { //匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误。");
                }
                //把res入栈
                stack.push(String.valueOf(res));
            }
        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation，返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级
    public static int getValue(String operation) {
        return switch (operation) {
            case "+" -> ADD;
            case "-" -> SUB;
            case "*" -> MUL;
            case "/" -> DIV;
            default -> 0;
        };
    }
}