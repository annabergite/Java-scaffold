package com.annabergite.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @PACKAGE_NAME: com.annabergite.collection
 * @USER: annabergite
 * @Description TODO
 * @Date 2023/12/25 21:27
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
public class IteratorDemo {
    public static void main(String[] args) {
        // 使用多态方式 创建对象
        Collection<String> coll = new ArrayList<String>();

        // 添加元素到集合
        coll.add("串串星人");
        coll.add("吐槽星人");
        coll.add("汪星人");
        //遍历
        //使用迭代器 遍历   每个集合对象都有自己的迭代器
        Iterator<String> it = coll.iterator();
        //  泛型指的是 迭代出 元素的数据类型
        while(it.hasNext()){ //判断是否有迭代元素
            String s = it.next();//获取迭代出的元素
            System.out.println(s);
        }
    }
}
