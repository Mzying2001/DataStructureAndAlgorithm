package com.mzying2001.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        //测试
        System.out.println("双向链表测试");

        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");

        //创建一个双向列表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();

    }
}

//创建一个双向链表的类
class DoubleLinkedList {

    //初始化头节点
    private HeroNode2 head = new HeroNode2(1, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表的方法
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空。");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添节点到当前链表
    public void add(HeroNode2 item) {
        if (head.next == null) {
            head.next = item;
            item.pre = head;
        } else {
            HeroNode2 temp = head.next;
            while (temp.next != null && temp.next.no < item.no)
                temp = temp.next;
            if (temp.next == null) {
                temp.next = item;
                item.pre = temp;
            } else {
                item.next = temp.next;
                item.next.pre = item;
                temp.next = item;
                item.pre = temp;
            }
        }
    }

    //修改一个节点的内容
    public void update(HeroNode2 item) {
        if (head.next == null) {
            System.out.println("链表为空。");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.printf("找不到编号为 %d 的节点。\n", item.no);
                break;
            } else if (temp.no == item.no) {
                temp.name = item.name;
                temp.nickname = item.nickname;
                break;
            } else {
                temp = temp.next;
            }
        }
    }

    //从双向链表中删除一个节点
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除。");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.printf("找不到编号为 %d 的节点。\n", no);
                return;
            } else if (temp.no == no) {
                if (temp.next != null)
                    temp.next.pre = temp.pre;
                temp.pre.next = temp.next;
                return;
            } else {
                temp = temp.next;
            }
        }
    }

}

//定义HeroNode，每个HeroNode就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre; //指向前一个节点

    //构造器
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方便，重写toString
    @Override
    public String toString() {
        return String.format("HeroNode2 [no=%d, name=%s, nickname=%s]", no, name, nickname);
    }
}
