package com.annabergite.jvm;

/**
 * @PACKAGE_NAME: com.annabergite.jvm
 * @USER: annabergite
 * @Description TODO
 * @Date 2023/12/16 16:48
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
public class ShutDownHookTest {
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Shutdown Hook now!");
            }
        });
        System.out.println("Going to exit");
    }
}