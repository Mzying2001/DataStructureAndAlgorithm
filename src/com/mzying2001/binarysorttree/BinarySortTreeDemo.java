package com.mzying2001.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环添加节点到二叉排序树
        for (int i : arr) {
            binarySortTree.add(new Node(i));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

        //测试删除节点
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        System.out.println("删除节点后");
        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //查找要删除的节点
    public Node search(int value) {
        return root == null ? null : root.search(value);
    }

    //查找父节点
    public Node searchParent(int value) {
        return root == null ? null : root.searchParent(value);
    }

    /**
     * @param node 传入的节点（当作二叉排序树的根节点）
     * @return 返回以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环地查找左节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向了最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) return;

        //1、需要先找到要删除的节点targetNode
        Node targetNode = search(value);
        //如果没有找到要删除的节点
        if (targetNode == null) return;

        //如果发现当前这颗二叉排序树只有一个节点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }

        //去找到targetNode的父节点
        Node parent = searchParent(value);
        //如果要删除的节点是叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            //判断targetNode是父节点的左子节点还是右子节点
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            targetNode.value = delRightTreeMin(targetNode.right);
        } else { //删除只有一颗子树的节点
            //如果要删除的节点有左子节点
            if (targetNode.left != null) {
                if (parent == null) {
                    root = targetNode.left;
                    return;
                }
                if (parent.left.value == value) {
                    parent.left = targetNode.left;
                } else {
                    parent.right = targetNode.left;
                }
            } else { //如果要删除的节点有右子节点
                if (parent == null) {
                    root = targetNode.right;
                    return;
                }
                if (parent.left.value == value) {
                    parent.left = targetNode.right;
                } else {
                    parent.right = targetNode.right;
                }
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历。");
        }
    }
}

//创建Node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加节点的方法
    //递归的形式添加节点，注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) return;

        //判断传入的节点的值和当前子树的根节点的值的关系
        if (node.value < this.value) {
            //如果当前节点左子节点为空
            if (this.left == null) {
                this.left = node;
            } else {
                //递归向左子树添加
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                //递归向右子树添加
                this.right.add(node);
            }
        }
    }

    /**
     * 查找要删除的节点
     *
     * @param value 欲找到节点的值
     * @return 如果找到返回节点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value)
            return this;
        if (value < this.value) {
            //如果查找的值小于当前节点，向左子树递归查找
            //如果左子节点为空
            if (this.left == null)
                return null;
            else
                return this.left.search(value);
        } else {
            if (this.right == null)
                return null;
            else
                return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value 要找到的节点值
     * @return 如果找到则返回对应节点的父节点，否则返回null
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的做子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); //向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); //向右递归查找
            } else {
                return null; //没有找到
            }
        }
    }

    public void infixOrder() {
        if (this.left != null) this.left.infixOrder();
        System.out.println(this);
        if (this.right != null) this.right.infixOrder();
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
