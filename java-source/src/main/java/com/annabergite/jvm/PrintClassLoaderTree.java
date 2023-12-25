package com.annabergite.jvm;

/**
 * @PACKAGE_NAME: com.annabergite.jvm
 * @USER: annabergite
 * @Description PrintClassLoaderTree
 * @Date 2023/12/17 17:57
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
public class PrintClassLoaderTree {

    public static void main(String[] args) {

        ClassLoader classLoader = PrintClassLoaderTree.class.getClassLoader();

        StringBuilder split = new StringBuilder("|--");
        boolean needContinue = true;
        while (needContinue){
            System.out.println(split.toString() + classLoader);
            if(classLoader == null){
                needContinue = false;
            }else{
                classLoader = classLoader.getParent();
                split.insert(0, "\t");
            }
        }
    }

}