package com.free.pojo;

/**
 * @ClassNameNode
 * @Description
 * @Author Free
 * @Date2020/10/9 21:23
 * @Version V1.0
 **/
public class Node {
    //存储当前节点以下的计算结果
    public Fraction result;
    public Node left;
    public Node right;
    public int high;

    public Node() {
    }

    public Node(Fraction result, Node left, Node right, int high) {
        this.result = result;
        this.left = left;
        this.right = right;
        this.high = high;
    }

    //打印出表达式
    @Override
    public String toString() {
        return result.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (result != null ? !result.equals(node.result) : node.result != null)
            return false;
        if (right != null ? !right.equals(node.right) : node.right != null)
            return false;
        return left != null ? left.equals(node.left) : node.left == null;
    }


    @Override
    public int hashCode() {
        int result1 = result != null ? result.hashCode() : 0;
        result1 = 31 * result1 + (right != null ? right.hashCode() : 0);
        result1 = 31 * result1 + (left != null ? left.hashCode() : 0);
        return result1;
    }

}
