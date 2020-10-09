package com.free.util;

import com.free.pojo.Expression;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassNameGenerateUtils
 * @Description
 * @Author Free
 * @Date2020/10/9 21:14
 * @Version V1.0
 **/
public class GenerateUtils {
    /**
     * 功能描述: <br>获得范围内的随机整数
     * 〈〉
     * @Param: [range]
     * @Return: int
     * @Author: Free
     * @Date: 2020/10/9 22:52
     */
    public static int getRandomInRange(int range) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(range);
    }
    //生成题目和答案的映射关系
    public static HashMap<String, String> generateMap(int exam_number, int answer_range) {
        if (exam_number < 1) {
            throw new RuntimeException("生成题目的个数必须大于0");
        }
        if (answer_range < 1) {
            throw new RuntimeException("运算结果范围必须大于等于1");
        }
        HashMap<String, String> hashMap = new HashMap<>();

        for (int i = 1; hashMap.size() < exam_number; ) {
            //因为在运算的过程中会出现n÷0的情况，这时候就会抛异常
            Expression expression = new Expression(3, answer_range);
            if ((hashMap.get(expression.toString()) != null || !"".equals(expression.toString()))
                    &&
                    !expression.isDivideForZero()) {
                hashMap.put(expression.toString(), expression.getRoot().result.toString());
                i++;
            }
        }
        return hashMap;
    }
}
