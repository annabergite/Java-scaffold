package com.annabergite;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.junit.Test;

/**
 * @PACKAGE_NAME: com.annabergite
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/3/21 23:26
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
public class JsonTest {
    @Test
    public void tests() {
        // 创建一个JSONArray示例
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONObject().put("key1", "value1"));
        jsonArray.add(new JSONObject().put("key2", "value2"));
        jsonArray.add(new JSONObject().put("key3", "value3"));

        // 遍历JSONArray
        jsonArray.forEach(item -> {
            // 假设item是一个JSONObject
            JSONObject jsonObject = (JSONObject) item;
            jsonObject.keySet().forEach(key -> {
                // 打印键值对
                System.out.println(key + ": " + jsonObject.get(key));
            });
        });
    }

}
