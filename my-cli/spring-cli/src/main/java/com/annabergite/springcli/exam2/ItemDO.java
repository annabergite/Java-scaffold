package com.annabergite.springcli.exam2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PACKAGE_NAME: com.annabergite.springcli.exam2
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/1/27 14:24
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDO {
    private long id;
    private int type;
    private double score;

}