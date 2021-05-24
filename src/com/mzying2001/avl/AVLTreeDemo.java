package com.mzying2001.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4, 3, 6, 5, 7, 8};
        //int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        //创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        for (int i : arr) {
            avlTree.add(new Node(i));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在平衡处理后");
        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("左子树的高度=" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度=" + avlTree.getRoot().rightHeight());
        System.out.println("当前根节点=" + avlTree.getRoot());

    }
}

//创建avl树
class AVLTree {
    private Node root;

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public Node getRoot() {
        return root;
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

    //返回左子树的高度
    public int leftHeight() {
        return left == null ? 0 : left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        return right == null ? 0 : right.height();
    }

    //返回当前节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    private void leftRotate() {
        //创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前节点的右子树的右子树
        right = right.right;
        //把当前节点的左子树（节点）设置成新的节点
        left = newNode;
    }

    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
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

        //当添加完一个节点后，如果（右子树高度-左子树高度）>1，左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树高度大于它的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子节点进行右旋转
                right.rightRotate();
                //然后再对当前节点进行左旋转
                leftRotate();
            } else {
                //直接进行左旋转
                leftRotate();
            }
            return; //必须要！！！
        }

        //当添加完一个节点后如果（左子树高度-右子树高度）>1，右旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树 高于它的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对发当前节点的左节点旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            } else {
                //直接进行右旋转
                rightRotate();
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
