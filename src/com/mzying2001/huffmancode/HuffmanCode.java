package com.mzying2001.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {

//        //测试压缩文件
//        String srcFile = "D://0.jpg";
//        String dstFile = "D://1.zip";
//        zipFile(srcFile, dstFile);
//        System.out.println("成功");

        //测试解压文件
        String zipFile = "D://1.zip";
        String dstFile = "D://2.jpg";
        unZipFile(zipFile, dstFile);
        System.out.println("成功");

//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length); //40
//
//        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
//        System.out.println("压缩后的结果是：" + Arrays.toString(huffmanCodesBytes));
//        System.out.println("长度：" + huffmanCodesBytes.length);
//
//        //测试byteToBitString方法
//        //System.out.println(byteToBitString((byte) 1));
//        byte[] sourceByte = decode(huffmanCodes, huffmanCodesBytes);
//        System.out.println("原来的i字符串=" + new String(sourceByte));

//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println(nodes);
//
//        //测试创建的二叉树
//        System.out.println("赫夫曼树");
//        Node huffmanTreeRoot = createHuffmanTree(nodes);
//        preOrder(huffmanTreeRoot);
//
//        //测试是否生成了对应的赫夫曼编码
//        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
//        System.out.println("生成的赫夫曼编码表：" + huffmanCodes);
//
//        //测试
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
//        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));

    }

    /**
     * 编写一个方法，完成对压缩文件的解压
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件放到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义一个文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 编写一个方法，将一个文件进行压缩
     *
     * @param srcFile  传入的欲压缩文件的全路径
     * @param destFile 压缩后的文件放到那个目录
     */
    public static void zipFile(String srcFile, String destFile) {
        //创建输出流
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(destFile);
            //创建一个和文件输出流关联的object
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //以对象流的形式写入赫夫曼编码，是为了恢复源文件时使用
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //完成数据的解压
    //思路
    //1、将huffmanCodeBytes重新转成赫夫曼编码对应的二进制字符串
    //2、将二进制字符串对照赫夫曼编码重新转成字符串

    /**
     * 编写一个方法，完成对压缩数据的解码
     *
     * @param huffmanCodes 赫夫曼编码表map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1、先得到huffmanBytes对应的二进制的字符串，形式如”10101000...“
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串按照指定的赫夫曼编码进行解码
        Map<String, Byte> map = new HashMap<>();
        for (var entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //取出字符
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        //当for循环结束后，list中就存放了所有的字符
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转成一个二进制的字符串
     *
     * @param flag 标志是否需要补高位,最后一个字节无需补高位
     * @param b    传入的byte
     * @return b对应的字符串（按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用一个变量保持b
        int temp = b;

        if (flag) temp |= 256;

        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制补码

        if (flag)
            return str.substring(str.length() - 8);
        else
            return str;
    }

    //使用一个方法，将前面的方法封装起来，便于调用
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    //编写一个方法，通过赫夫曼编码表将对应的byte[]压缩
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历byres数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println("测试stringBuilder="+stringBuilder.toString());

        //统计byte[] huffmanCodeBytes长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; //记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转成一个byte，放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //生成赫夫曼数对应的赫夫曼编码
    //思路：
    //1、将赫夫曼编码表存在Map<Byte,String>
    //2、在生成赫夫曼编码表时，需要拼接，定义一个StringBuilder存储某个叶子节点的路径
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，重载调用
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) return null;

        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);

        return huffmanCodes;
    }

    /**
     * 功能：将传入的node节点的所有叶子节点的赫夫曼编码存放到huffmanCodes
     *
     * @param node          传入节点
     * @param code          路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null) { //非叶子节点
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { //说明是叶子节点
                //就表示早到了某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //前序遍历的方法
    private static void preOrder(Node root) {
        if (root != null)
            root.preOrder();
        else
            System.out.println("赫夫曼树为空。");
    }

    /**
     * @param bytes 接受字节数组
     * @return 返回List形式
     */
    public static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();

        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null)
                counts.put(b, 1);
            else
                counts.put(b, count + 1);
        }

        //把每一个键值对转成Node
        for (var item : counts.entrySet()) {
            nodes.add(new Node(item.getKey(), item.getValue()));
        }

        return nodes;
    }

    //通过List创建赫夫曼树
    public static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            Collections.sort(nodes);

            Node leftName = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftName.weight + rightNode.weight);

            parent.left = leftName;
            parent.right = rightNode;

            nodes.remove(leftName);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

//创建Node，带数据和权值
class Node implements Comparable<Node> {

    Byte data; //存放数据（字符）本身
    int weight; //权值，表示字符出现的次数

    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight; //从小到大排序
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (left != null) left.preOrder();
        if (right != null) right.preOrder();
    }
}