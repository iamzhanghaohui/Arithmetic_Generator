package com.free.pojo;

/**
 * @ClassNameOperatorNode
 * @Description 存放运算符的节点
 * @Author Free
 * @Date2020/10/9 21:36
 * @Version V1.0
 **/
public class OperatorNode extends Node {
    //运算符
    public String operator;
    public OperatorNode(Node left, Node right, String operator) {
        //父类中无用的常量设置为null
        super(null, left, right, 0);
        this.operator = operator;
    }
    //中间节点存放运算符，需空格隔开
    @Override
    public String toString() {
        return " " + operator + " ";
    }
}
