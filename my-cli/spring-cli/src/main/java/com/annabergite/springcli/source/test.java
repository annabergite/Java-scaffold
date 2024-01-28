package com.annabergite.springcli.source;

import org.springframework.cache.CacheManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * @PACKAGE_NAME: com.annabergite.springcli.source
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/1/24 20:32
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
public class test {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add(1);
        list.get(0);
    }

    public static final String LOGIN = "login";
    CacheManager cacheManager;

    public static void read(String fileName, String charset) {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(fileName, "r");
            long len = randomAccessFile.length();
            long start = randomAccessFile.getFilePointer();
            long nextend = start + len - 1;
            HashMap<String, Long> map = new HashMap<>();
            String line;
            randomAccessFile.seek(nextend);
            int c = -1;
            if (c == '\n' || c == '\r') {
                line = randomAccessFile.readLine();
                if (line != null) {
                    List<String> list = new ArrayList<>(Arrays.asList(line.split(" ")));
                    //如果第二个是login
                    if (LOGIN.equals(list.get(1))) {
                        map.put(list.get(2), map.get(list.get(2)) == null ? 1 : map.get(list.get(2)) + 1);
                    }
                }
            }
            map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).forEach(entry->map.put(entry.getKey(), entry.getValue()));
            System.out.println(map);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
