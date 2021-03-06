package com.free.pojo;

import com.free.pojo.Expression;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassNameCreator
 * @Description
 * @Author Free
 * @Date2020/10/9 21:14
 * @Version V1.0
 **/
public class Creator {

    /**
     * 功能描述: <br>获得范围内的随机整数
     * 〈〉
     * @Param: [numRange]
     * @Return: int
     * @Author: Free
     * @Date: 2020/10/9 22:52
     */
    public static int getRandomInRange(int numRange) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(numRange);
    }

    /**
     * 生成题目和答案的映射关系
     * @param examNumber
     * @param answerRange
     * @return
     */
    public static HashMap<String, String> generateMap(int examNumber, int answerRange) {
        if (examNumber < 1) {
            throw new RuntimeException("生成题目的个数必须大于0");
        }
        if (answerRange < 1) {
            throw new RuntimeException("运算结果范围必须大于等于1");
        }
        HashMap<String, String> resMap = new HashMap<>();

        for (int i = 1; resMap.size() < examNumber; ) {
            //因为在运算的过程中会出现n÷0的情况，这时候就会抛异常
            Expression expression = new Expression(3, answerRange);

            Boolean tempFlag =(resMap.get(expression.toString()) != null || !"".equals(expression.toString()))
                    && ( !expression.isDivideZero());
            if (tempFlag) {
                resMap.put(expression.toString(), expression.getRoot().result.toString());
                i++;
            }
        }
        return resMap;
    }
}
