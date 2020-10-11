package com.free.pojo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @ClassNameCheck
 * @Description
 * @Author Free
 * @Date2020/10/10 14:13
 * @Version V1.0
 **/
public class Check {

    /**
     * 功能描述: <br>
     * 〈〉学生的答案，和标准答案
     * @Param: [answerFile, exerciseFile]
     * @Return: void
     * @Author: Free
     * @Date: 2020/10/10 17:00
     */
    public static void checkAnswer(File answerFile, File exerciseFile) throws IOException {
        if (!exerciseFile.exists()) {
            System.out.println("练习答案文件不存在");
            return;
        }
        if (!answerFile.exists()) {
            System.out.println("答案文件不存在");
            return;
        }
        //key是题号，value是答案
        Map<Integer, String> exerciseMap = new HashMap<>();
        Map<Integer, String> answerMap = new HashMap<>();

        InputStreamReader exerciseIn = new InputStreamReader(new FileInputStream(exerciseFile.getAbsolutePath()), StandardCharsets.UTF_8);
        InputStreamReader answerIn = new InputStreamReader(new FileInputStream(answerFile.getAbsolutePath()), StandardCharsets.UTF_8);
        BufferedReader exerciseReader = new BufferedReader(exerciseIn);
        BufferedReader answerReader = new BufferedReader(answerIn);
        String tempString = null;
        //存储练习的答案
        while ((tempString = exerciseReader.readLine()) != null) {
            tempString = tempString.replaceAll(" +", "");
            tempString = tempString.replaceAll("\uFEFF", "");
            String TEXT=tempString.split("[:]")[0];
            exerciseMap.put(Integer.valueOf(tempString.split("[:]")[0]), tempString.split(":")[1]);
        }
        while ((tempString = answerReader.readLine()) != null) {
            tempString = tempString.replaceAll(" +", "");
            tempString = tempString.replaceAll("\uFEFF", "");
            answerMap.put(Integer.valueOf(tempString.split("[:]")[0]), tempString.split(":")[1]);
        }
        exerciseReader.close();
        answerReader.close();

        //调用
        compareAndOutput(exerciseMap,answerMap);
    }
    /**
     * 功能描述: <br>
     * 〈〉学生的答案，和标准答案
     * @Param: [exerciseMap, answerMap]
     * @Return: void
     * @Author: Free
     * @Date: 2020/10/10 16:59
     */
    public static void compareAndOutput( Map<Integer, String> exerciseMap , Map<Integer, String> answerMap )throws IOException{
        //对比的结果
        List<Integer> rightRsult=new LinkedList<>();
        List<Integer>  errorRsult=new LinkedList<>();
        //比较答案
        for (int i = 1; i <= answerMap.size(); i++){
            if(exerciseMap.containsKey(i)){
                if(exerciseMap.get(i).equals(answerMap.get(i))){
                    rightRsult.add(i);
                }else {
                    errorRsult.add(i);
                }
            }
        }
        //将比较结果存储到文件中
        File file=new File("Grade.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(" ");
        printWriter.print("Correct:正确题数："+rightRsult.size()+"(");
        for (int str: rightRsult) {
            printWriter.print(str+",");
        }
        printWriter.println(")");
        printWriter.print("Wrong:错误题数："+errorRsult.size()+"(");
        for (int str: errorRsult) {
            printWriter.print(str+",");
        }
        printWriter.print(")");
        printWriter.flush();
        fileWriter.flush();
        printWriter.close();
        fileWriter.close();

    }
}
