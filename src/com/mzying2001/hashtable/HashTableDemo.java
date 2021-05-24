package com.mzying2001.hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {

        //创建一个哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单菜单来测试
        Scanner sc = new Scanner(System.in);

        loop:
        while (true) {

            System.out.println("add： 添加雇员");
            System.out.println("list：显示雇员");
            System.out.println("find：查找雇员");
            System.out.println("del： 删除雇员");
            System.out.println("exit：退出程序");

            switch (sc.next()) {

                case "add" -> {
                    System.out.println("输入id");
                    int id = sc.nextInt();
                    System.out.println("输入名字");
                    String name = sc.next();

                    //创建一个雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                }

                case "list" -> hashTab.list();

                case "find" -> {
                    System.out.println("请输入id");
                    hashTab.findEmpById(sc.nextInt());
                }

                case "del" -> {
                    System.out.println("请输入雇员id");
                    hashTab.del(sc.nextInt());
                }

                case "exit" -> {
                    break loop;
                }

            }

        }

        sc.close();

    }
}

//创建HashTab，用来管理多条链表
class HashTab {

    private EmpLinkedList[] empLinkedListsArray;
    private int size; //表示共有多少条链表

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListsArray
        empLinkedListsArray = new EmpLinkedList[size];
        //初始化每一个链表
        for (int i = 0; i < empLinkedListsArray.length; i++)
            empLinkedListsArray[i] = new EmpLinkedList();
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id得到该员工应当添加到那条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListsArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表，即遍历hashtab
    public void list() {
        for (int i = 0; i < size; i++) empLinkedListsArray[i].list(i);
    }

    //根据输入的id查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListsArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            //找到
            System.out.printf("在第%d条链表中找到雇员id=%d\n", empLinkedListNO + 1, id);
        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    //通过id删除雇员
    public void del(int id) {
        if (empLinkedListsArray[hashFun(id)].del(id))
            System.out.println("删除成功");
        else
            System.out.println("删除失败");
    }

    //编写一个散列函数，使用简单的取模法
    public int hashFun(int id) {
        return id % size;
    }

}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next; //next默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建一个EmpLinkedList，表示链表
class EmpLinkedList {
    //头指针指向第一个Emp，因此这个链表的head是直接指向第一个雇员的
    private Emp head; //默认为空

    //添加员工到链表
    //说明
    //1.假定当添加雇员时id是自增长的，即id的分配总是从小到大
    //2.因此直接将该雇员直接加入到最后即可
    public void add(Emp emp) {
        //如果是第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针来定位到最后
        Emp curEmp = head;
        while (curEmp.next != null)
            curEmp = curEmp.next;
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.printf("第%d条链表为空\n", no + 1);
            return;
        }
        System.out.printf("第%d条链表：\t", no + 1);
        Emp curEmp = head; //辅助指针
        do {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            curEmp = curEmp.next;
        } while (curEmp != null);
        System.out.println();
    }

    //更具id查找雇员
    //如果查找到就返回Emp，否则返回null
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        do {
            if (curEmp.id == id) {
                //找到
                return curEmp;
            }
            curEmp = curEmp.next;
        } while (curEmp != null);
        return null;
    }

    //删除Emp
    public boolean del(int id) {
        if (head == null) return false;
        if (head.id == id) {
            head = head.next;
            return true;
        }
        Emp cur = head;
        do {
            if (cur.next.id == id) {
                cur.next = cur.next.next;
                return true;
            }
            cur = cur.next;
        } while (cur.next != null);
        return false;
    }
}