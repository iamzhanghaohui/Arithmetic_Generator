package com.free.pojo;

import lombok.Data;

/**
 * @ClassNameExpression 四则运算表达式的生成
 *                        构建二叉树
 * @Description 表达式
 * @Author Free
 * @Date2020/10/9 21:13
 * @Version V1.0
 **/
@Data
public class Expression {

    private static final String ADDITION = "＋";

    private static final String SUBTRACT = "-";

    private static final String MULTIPLY = "×";

    private static final String DIVIDE = "÷";

    private static final String LEFT_BRACKETS = "(";

    private static final String RIGHT_BRACKETS = ")";

    //运算符的种类
    private static final String[] OPERATORS = {ADDITION, SUBTRACT, MULTIPLY, DIVIDE};

    //根节点
    private Node root;

    //出现n除以0的情况
    private boolean isDivideZero = false;
    //生成答案的范围
    public static int numRange;

    /**
     * 功能描述: <br>生成表达式
     * 〈〉
     * @Param: [operatorNum 运算符个数, answerRange 运算结果范围]
     * @Author: Free
     * @Date: 2020/10/9 21:31
     */
    public Expression(int operatorNum, int answerRange) {
        if (operatorNum < 1) {
            throw new RuntimeException("运算符个数必须大于0");
        }
        if (answerRange < 1) {
            throw new RuntimeException("运算结果范围必须大于等于1");
        }
        numRange = answerRange;

        if (operatorNum == 1) {
            root = generateNode(operatorNum);
        } else {
            root = generateNode(Creator.getRandomInRange(operatorNum) + 1);
        }
    }
    /**
     * 功能描述: <br>构建生成四则运算表达式的二叉树
     * 〈〉
     * @Param: [testNum] 运算符的个数
     * @Return: com.free.pojo.Node 二叉树的头节点
     * @Author: Free
     * @Date: 2020/10/9 21:34
     */
    public Node generateNode(int testNum) {
        //如果是0就构造叶子节点
        if (testNum == 0) {
            return new Node(MyNumber.generateMyNumber(), null, null, 1);
        }
        //其他都是构造符号节点
        //随机选一种运算符号
        Onode parent = new Onode(null, null, OPERATORS[Creator.getRandomInRange(4)]);
        int left = Creator.getRandomInRange(testNum);
        //递归下去构造左孩子和右孩子
        parent.left = generateNode(left);
        //总数要减去当前已经构建出来的这一个节点
        parent.right = generateNode(testNum - 1 - left);

        //然后计算结果
        MyNumber result = calculate(parent.operator, parent.left.result, parent.right.result);
        //如果是负数 这时候交换左右孩子
        if (result.isNegative()) {
            Node tmp = parent.left;
            parent.left = parent.right;
            parent.right = tmp;
        }
        parent.result = result;
        //计算树高
        parent.high = Math.max(parent.left.high, parent.right.high) + 1;
        return parent;
    }

    /**
     * 功能描述: <br>进行两个元素的计算
     * 〈〉
     * @Param: [operator, leftMyNumber, rightMyNumber]
     * @Return: com.free.pojo.MyNumber
     * @Author: Free
     * @Date: 2020/10/9 21:52
     */
    private MyNumber calculate(String operator, MyNumber leftMyNumber, MyNumber rightMyNumber) {
        switch (operator) {
            case ADDITION:
                return leftMyNumber.add(rightMyNumber);
            case SUBTRACT:
                return leftMyNumber.subtract(rightMyNumber);
            case MULTIPLY:
                return leftMyNumber.multiply(rightMyNumber);
            //可能会出现除以0的情况，即rightMyNumber可能为0
            case DIVIDE:
                if (rightMyNumber.getUp() == 0) {
                    this.isDivideZero = true;
                    rightMyNumber.setUp(1);
                }
                return leftMyNumber.divide(rightMyNumber);
            default:
                throw new RuntimeException("该操作符不存在");
        }
    }

    //打印出中缀表达式，包括括号
    @Override
    public String toString() {
        return print(root);
    }

    /**
     * 功能描述: <br>
     * 〈〉中序遍历二叉树,左中右
     * @Param: [localRootNode] 当前所在的最高节点，可以不是根节点
     * @Return: java.lang.String
     * @Author: Free
     * @Date: 2020/10/9 22:49
     */
    private String print(Node localRootNode) {

        if (localRootNode == null) {
            return "";
        }
        String left = print(localRootNode.left);
        String mid = localRootNode.toString();
        //需要加括号的情况,一个节点的操作符为乘除，其子节点的操作符是加减
        if (localRootNode.left instanceof Onode && localRootNode instanceof Onode) {
            if (leftBrackets(((Onode) localRootNode.left).operator, ((Onode) localRootNode).operator)) {
                left = LEFT_BRACKETS + " " + left + " " + RIGHT_BRACKETS;
            }
        }
        String right = print(localRootNode.right);
        if (localRootNode.right instanceof Onode && localRootNode instanceof Onode) {
            if (rightBrackets(((Onode) localRootNode.right).operator, ((Onode) localRootNode).operator)) {
                right = LEFT_BRACKETS + " " + right + " " + RIGHT_BRACKETS;
            }
        }
        return left + mid + right;
    }
    /**
     * 功能描述: <br>向左遍历
     * 〈〉
     * @Param: [left, mid]
     * @Return: boolean
     * @Author: Free
     * @Date: 2020/10/9 22:47
     */
    private boolean leftBrackets(String left, String mid) {
        return (isAddOrSubtract(left) && isMultiplyOrDivide(mid));
    }

    /**
     * 功能描述: <br>向右遍历
     * 〈〉
     * @Param: [right, mid]
     * @Return: boolean
     * @Author: Free
     * @Date: 2020/10/9 22:47
     */
    private boolean rightBrackets(String right, String mid) {
        //有可能出现2*3 /( 4*5 )的情况，所以不用加括号只有当
        return !(isAddOrSubtract(mid) && isMultiplyOrDivide(right));
    }

    /**
     * 功能描述: <br>是加减运算符
     * 〈〉
     * @Param: [operator]
     * @Return: boolean
     * @Author: Free
     * @Date: 2020/10/9 22:48
     */
    private boolean isAddOrSubtract(String operator) {
        return operator.equals(ADDITION) || operator.equals(SUBTRACT);
    }

    /**
     * 功能描述: <br>是乘除运算符
     * 〈〉
     * @Param: [operator]
     * @Return: boolean
     * @Author: Free
     * @Date: 2020/10/9 22:48
     */
    private boolean isMultiplyOrDivide(String operator) {
        return operator.equals(MULTIPLY) || operator.equals(DIVIDE);
    }


}
