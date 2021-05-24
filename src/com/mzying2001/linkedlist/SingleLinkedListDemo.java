package com.mzying2001.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

//        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);


        //按照加入的顺序加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        //显示链表
        singleLinkedList.list();

//        //反转链表
//        reverseList(singleLinkedList.getHead());
//        System.out.println("反转后的链表：");
//        singleLinkedList.list();

        System.out.println("测试逆序打印单链表：");
        reversePrint(singleLinkedList.getHead());

//        //测试修改节点的代码
//        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
//        singleLinkedList.update(newHeroNode);
//
//        System.out.println("修改后的链表：");
//        singleLinkedList.list();
//
//        //删除一个节点
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
//
//        System.out.println("删除后的链表：");
//        singleLinkedList.list();
//
//        //获取链表节点个数
//        System.out.println("有效的节点个数 = " + getLength(singleLinkedList.getHead()));
//
//        //测试是否能得到倒数第k个节点
//        HeroNode res = findLastIndex(singleLinkedList.getHead(), 1);
//        System.out.println("result=" + res);

    }

    //使用栈来实现逆序打印
    public static void reversePrint(HeroNode head) {
        if (head.next == null)
            return; //空链表
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点进行打印
        while (stack.size() > 0)
            System.out.println(stack.pop()); //stack的特点是先进后出
    }

    //将单链表反转
    public static void reverseList(HeroNode head) {
//        if (head.next == null || head.next.next == null)
//            return;
//
//        //定义一个辅助指针，用于遍历原来的链表
//        HeroNode cur = head.next;
//        HeroNode next = null; //指向当前节点的下一个节点
//        HeroNode reverseHead = new HeroNode(0, "", "");
//        //遍历原来的链表
//        while (cur != null) {
//            next = cur.next; //先暂时保存当前节点的下一个节点
//            cur.next = reverseHead.next;
//            reverseHead.next = cur;
//            cur = next;
//        }
//        //将head.next指向reverseHead.next，实现链表的反转
//        head.next = reverseHead.next;

        //以下是自己默写的代码
        if (head.next == null || head.next.next == null)
            return;

        HeroNode now = head.next;
        HeroNode next;
        HeroNode newHead = new HeroNode(0, "", "");

        while (now != null) {
            next = now.next;
            now.next = newHead.next;
            newHead.next = now;
            now = next;
        }
        head.next = newHead.next;
    }

    //查找单链表中的倒数第k个节点【新浪面试题】
    //思路
    //1、编写一个方法，接受head节点，同时接受一个index
    //2、index表示倒数第index个节点
    //3、先把链表从头到尾遍历，得到链表的总长度size
    //4、得到size后，从链表第一个遍历（size-index）个，就可以得到
    //5、如果找到了，返回该节点，否则返回null
    public static HeroNode findLastIndex(HeroNode head, int index) {
        //判断如果链表为空，返回null
        if (head == null)
            return null;
        //第一次遍历，得到链表的长度（节点个数）
        int size = getLength(head);
        //第二次遍历size-index位置，就是倒数第k个节点
        //先做一个index的校验
        if (index <= 0 || index > size)
            return null;
        //定义一个辅助变量，for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++)
            cur = cur.next;
        return cur;
    }

    //获取单链表的节点个数（如果时带头的节点，需求不统计节点的个数）

    /**
     * @param head 链表的头节点
     * @return 有效节点的个数
     */
    public static int getLength(HeroNode head) {
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

}

//定义SingleLinkedList，管理英雄
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向列表
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历列表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到将temp下移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向链表的最后
        temp.next = heroNode;
    }

    //第二种添加方式：在添加英雄时根据排名将英雄插入到指定位置
    //（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此通过一个辅助变量来找到添加的位置
        //因为单列表，因此找到的temp是位于添加位置的点一个节点，否则无法插入
        HeroNode temp = head;
        Boolean flag = false; //标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) { //说明temp已经在链表的最后
                break;
            } else if (temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { //说明要添加的heroNode的编号已存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        if (flag) {
            System.out.printf("欲插入的英雄编号 %d 已存在，无法加入\n", heroNode.no);
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点，根据no编号来修改，即no不能改
    public void update(HeroNode newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        Boolean flag = false; //表示是否找到节点
        while (true) {
            if (temp == null) {
                break; //已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号为 %d 的节点\n", newHeroNode.no);
        }
    }

    //删除节点
    public void del(int no) {
        HeroNode temp = head;
        Boolean flag = false; //标志是否找到节点
        while (true) {
            if (temp.next == null) {
                //已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                //找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) { //找到
            //可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点 %d 不存在\n", no);
        }
    }

    //显示链表（遍历）
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}

//定义HeroNode，每个HeroNode就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方便，重写toString
    @Override
    public String toString() {
        return String.format("HeroNode [no=%d, name=%s, nickname=%s]", no, name, nickname);
    }
}
