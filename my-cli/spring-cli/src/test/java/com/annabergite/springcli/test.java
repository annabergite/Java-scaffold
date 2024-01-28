package com.annabergite.springcli;

import com.annabergite.springcli.exam2.ItemDO;
import com.annabergite.springcli.exam2.ItemRerank;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @PACKAGE_NAME: com.annabergite.springcli
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/1/27 14:23
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
@SpringBootTest
public class test {

    @Test
    void main() {
        List<ItemDO> itemList = new ArrayList<>();
        itemList.add(new ItemDO(1, "a", 10));
        itemList.add(new ItemDO(2, "b", 20));
        itemList.add(new ItemDO(3, "c", 30));
        itemList.add(new ItemDO(4, "d", 40));
        itemList.add(new ItemDO(5, "e", 50));

    }

}
