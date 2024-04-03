package com.annabergite.gupao;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class homework1test1{
    public static void main(String[] args) throws IOException {
        File aFile = new File("e:/a.txt");
        File bFile = new File("e:/b.txt");
        FileReader frA = new FileReader(aFile);
        FileReader frB = new FileReader(bFile);
        BufferedReader brA = new BufferedReader(frA);
        BufferedReader brB = new BufferedReader(frB);
        List<String> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();
        String temp;
        //把a和b的内容抖分别存到list里面
        while ((temp = brA.readLine()) != null) {
            listA.add(temp);
        }
        while ((temp = brB.readLine()) != null) {
            listB.add(temp);
        }
        int sizeB = listB.size();
        BufferedWriter bw = new BufferedWriter(new FileWriter("e:/a.txt"));
        //把a的给清空掉
        bw.flush();
        for (int i = 0; i < listA.size(); i++) {
            bw.write(listA.get(i) + "\n");
            if (i < sizeB) bw.write(listB.get(i) + "\n");
        }
        int dis = listA.size() - listB.size();
        //如果b还有
        if (dis < 0) {
            for (int i = (-dis); i < listB.size(); i++) {
                bw.write(listB.get(i) + "\n");
            }
        }
        frA.close();
        frB.close();
        brA.close();
        brB.close();
        bw.close();
    }
}
