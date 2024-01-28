package com.annabergite.springcli.source;


import java.io.Serializable;

/**
 * @PACKAGE_NAME: PACKAGE_NAME
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/1/24 20:29
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
// 以下为提供的接口，无需写实现类。
public class ItemInfo implements Serializable {
    private long itemId;
    private String title;
    private String desc;

    public long getItemId() {
        return itemId;
    }
}
