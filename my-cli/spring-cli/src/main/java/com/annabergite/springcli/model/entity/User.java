package com.annabergite.springcli.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PACKAGE_NAME: com.annabergite.springcli.model.entity
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/2/25 21:36
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer age;
    private String name;
}
