package com.annabergite;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @PACKAGE_NAME: com.annabergite
 * @USER: annabergite
 * @Description TODO
 * @Date 2023/12/25 21:25
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
public class CollectionTest {

    @Test
    public void tests() {
        String a = "abcde";
        String s = a.replaceAll("c", "f");
        System.out.println(s);
    }

    @Test
    public void juctests() {
        for (int i = 0; i < 100; i++) {
            int j = 0;
            System.out.println((j++) + (++j));
        }
    }

    @Test
    public void test2() {
        Collection coll = new ArrayList();
        coll.add(1);
        coll.add(2);

        System.out.println("coll集合元素的个数：" + coll.size());

        Collection other = new ArrayList();
        other.add(1);
        other.add(2);
        other.add(3);

        coll.addAll(other);
//		coll.add(other);
        System.out.println("coll集合元素的个数：" + coll.size());
    }

    @Test
    public void test5() {
        Collection coll = new ArrayList();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);
        coll.add(5);
        System.out.println("coll集合元素的个数：" + coll.size());//5

        Collection other = new ArrayList();
        other.add(1);
        other.add(2);
        other.add(8);

        coll.retainAll(other);//保留交集
        System.out.println("coll集合元素的个数：" + coll.size());//2
    }

    @Test
    public void test02() {
        Collection<String> coll = new ArrayList<>();
        coll.add("陈琦");
        coll.add("李晨");
        coll.add("邓超");
        coll.add("黄晓明");

        //删除名字有三个字的
//		coll.remove(o)//无法编写

        Iterator<String> iterator = coll.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.length() == 3) {
//				coll.remove(element);//错误的
                iterator.remove();
            }
        }
        System.out.println(coll);
    }

}
