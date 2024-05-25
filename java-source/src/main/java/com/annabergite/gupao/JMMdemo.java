package com.annabergite.gupao;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JMMdemo {
    public static void main(String[] args) throws IOException {

        Map<String, Integer> hashMap = new ConcurrentHashMap<>();
        File file = new File("C:\\Users\\zhubaiyi\\Desktop\\罗马假日英文剧本.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        List<String> results = Arrays.asList(line.split(" "));
        System.out.println(results);
        results.forEach(x -> {
            x = x.replace(" ", "");
            System.out.println(x);
            System.out.println(hashMap.containsKey(x));
            if (hashMap.containsKey(x)) {
                hashMap.put(x, hashMap.get(x) + 1);
            } else {
                hashMap.put(x, 1);
            }
        });
    }
}
