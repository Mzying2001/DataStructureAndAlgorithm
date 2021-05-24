package com.mzying2001.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {

        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //说明：先手动创建该二叉树，后面用递归的方式创建二叉树
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

//        //测试1
//        System.out.println("前序遍历"); //1 2 3 5 4
//        binaryTree.preOrder();
//
//        //测试2
//        System.out.println("中序遍历"); //2 1 5 3 4
//        binaryTree.infixOrder();
//
//        //测试3
//        System.out.println("后序遍历"); //2 5 4 3 1
//        binaryTree.postOrder();

//        //前序遍历查找
//        System.out.println("前序遍历方式");
//        HeroNode resNode = binaryTree.preOrderSearch(5);
//        if (resNode != null)
//            System.out.printf("找到了，信息为：no=%d name=%s\n", resNode.getNo(), resNode.getName());
//        else
//            System.out.printf("找不到no = %d 的英雄\n", 5);

//        //中序遍历查找
//        System.out.println("中序遍历方式");
//        HeroNode resNode = binaryTree.infixOrderSearch(5);
//        if (resNode != null)
//            System.out.printf("找到了，信息为：no=%d name=%s\n", resNode.getNo(), resNode.getName());
//        else
//            System.out.printf("找不到no = %d 的英雄\n", 5);

//        //后序遍历查找
//        System.out.println("后序遍历方式");
//        HeroNode resNode = binaryTree.postOrderSearch(5);
//        if (resNode != null)
//            System.out.printf("找到了，信息为：no=%d name=%s\n", resNode.getNo(), resNode.getName());
//        else
//            System.out.printf("找不到no = %d 的英雄\n", 5);

        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        binaryTree.delNode(5);
        //binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();

    }
}

//定义BinaryTree二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void delNode(int no) {
        if (root != null) {
            //如果只有一个root节点，这里立即判断root是不是要删除的节点
            if (root.getNo() == no)
                root = null;
            else
                root.delNode(no);
        } else {
            System.out.println("空树，不能删除。");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null)
            this.root.preOrder();
        else
            System.out.println("二叉树为空，无法遍历");
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null)
            this.root.infixOrder();
        else
            System.out.println("二叉树为空，无法遍历");
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null)
            this.root.postOrder();
        else
            System.out.println("二叉树为空，无法遍历");
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null)
            return root.preOrderSearch(no);
        else
            return null;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null)
            return root.infixOrderSearch(no);
        else
            return null;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null)
            return root.postOrderNode(no);
        else
            return null;
    }
}

//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点
    //1.如果删除节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no) {

        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) this.left.delNode(no);
        if (this.right != null) this.right.delNode(no);

    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this); //先输出父节点
        //递归向左子树前序遍历
        if (this.left != null)
            this.left.preOrder();
        //递归向右子树前序遍历
        if (this.right != null)
            this.right.preOrder();
    }

    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null)
            this.left.infixOrder();
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null)
            this.right.infixOrder();
    }

    //后续遍历
    public void postOrder() {
        if (this.left != null)
            this.left.postOrder();
        if (this.right != null)
            this.right.postOrder();
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no 查找no
     * @return 如果找到返回Node，否则返回null
     */
    public HeroNode preOrderSearch(int no) {
        //System.out.println("进入前序遍历查找");
        //比较当前节点是不是
        if (this.no == no) return this;
        //1.判断当前节点的子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到子节点，则返回
        HeroNode resNode = null;
        if (this.left != null)
            resNode = this.left.preOrderSearch(no);
        if (resNode != null)//说明左子树找到
            return resNode;
        //1.左递归前序查找，找到节点则返回，否则继续判断
        //2.当前节点的右子节点是否为空，如果不为空，则继续向右递归前序查找
        if (this.right != null)
            resNode = this.right.preOrderSearch(no);
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null)
            resNode = this.left.infixOrderSearch(no);
        if (resNode != null)
            return resNode;
        //System.out.println("进入中序遍历查找");
        if (this.no == no) return this;
        if (this.right != null)
            resNode = this.right.infixOrderSearch(no);
        return resNode;
    }

    //后续遍历查找
    public HeroNode postOrderNode(int no) {
        HeroNode resNode = null;
        if (this.left != null)
            resNode = this.left.postOrderNode(no);
        if (resNode != null)
            return resNode;
        if (this.right != null)
            resNode = this.right.postOrderNode(no);
        if (resNode != null) return resNode;
        //System.out.println("进入后序遍历查找");
        if (this.no == no) return this;
        else return null;
    }

}
