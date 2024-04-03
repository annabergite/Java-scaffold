package com.annabergite.gupao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class homework1test2 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();


        File aFile = new File("e:/a.txt");
        FileReader frA = new FileReader(aFile);
        BufferedReader brA = new BufferedReader(frA);
        List<String> listA = new ArrayList<>();
        //当前行数
        int nowNum = 0;
        //确定的是那几行
        Integer lineNum = null;
        //确定是哪一句，哪一行
        String theWords = null;

        String temp;
        //把a和b的内容抖分别存到list里面
        while ((temp = brA.readLine()) != null) {
            if (temp.contains(str)) {
                lineNum = nowNum;
                theWords = temp;
            } else {
                nowNum++;
            }
        }
        if (theWords == null) {
            System.out.println("没有读取到对应关键词");
        } else {
            System.out.println("该关键词出现在第" + lineNum + "行，该行内容为：" + theWords);
        }


        frA.close();
        brA.close();
    }
}
