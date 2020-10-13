package com.free;

import com.free.pojo.Check;
import com.free.pojo.Creator;
import com.free.util.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @ClassNamemain
 * @Description
 * @Author Free
 * @Date2020/10/9 20:17
 * @Version V1.0
 **/
public class MainStart {
    public static void main(String[] args) throws IOException {
        //数字范围大小
        int numRange=0;
        //题目数量
        int testNum=0;
        //接收参数
        while (true) {
            Scanner sc = new Scanner(System.in);
            String string = sc.nextLine();
            args = string.split("\\s+");
            //判断参数是否正确
            if (args.length < 2) {
                System.out.println("请重新输入正确的参数...");
            }
            else {
                break;
            }
        }
        //获取参数
        for(int i=0;i<args.length;i++){
            if("-n".equals(args[i])){
                testNum= Integer.parseInt(args[i+1]);
                i++;
            } else if ("-r".equals(args[i])) {
                numRange= Integer.parseInt(args[i+1]);
                i++;
            }
            else {
                break;
            }
        }
        //判断是否生成题目,如果不是生成题目，则是对照答案
        //Myapp.exe -e <exercisefile>.txt -a <answerfile>.txt
        if(numRange==0&&testNum==0){
            String answerFileName;
            String execiseFileName;
            if ("-e".equals(args[0])){
                execiseFileName=args[1];
                answerFileName=args[3];
            }else {
                execiseFileName=args[3];
                answerFileName=args[1];
            }
            File answerFile=new File(answerFileName);
            File exerciseFile=new File(execiseFileName);
            Check.checkAnswer(answerFile,exerciseFile);
        }

        else {
            HashMap<String, String> map= Creator.generateMap(testNum,numRange);
            File file=new File("Exercises.txt");
            File answerFile=new File("answerfile.txt");
            try {
                FileWriter fileWriter=new FileWriter(file,true);
                PrintWriter printWriter=new PrintWriter(fileWriter);
                FileUtils.writeTitle(printWriter,map);
                printWriter.flush();
                fileWriter.flush();
                printWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileWriter fileWriter=new FileWriter(answerFile,true);
                PrintWriter printWriter=new PrintWriter(fileWriter);
                FileUtils.writeAnswer(printWriter,map);
                printWriter.flush();
                fileWriter.flush();
                printWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
