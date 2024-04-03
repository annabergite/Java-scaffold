package com.annabergite.springcli.redis.lua;

import com.annabergite.springcli.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @PACKAGE_NAME: com.annabergite.springcli.redis.lua
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/2/25 21:35
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
@SpringBootTest
public class AppTests_Lua {

    @Resource(name = "redisTemplate")
    private ValueOperations<String, User> valueOperations;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void test1() {
        for (int i =1 ;i<11;i++){
            User user = User.builder().name(i+"号张").age(18).build();
            valueOperations.set("user."+i,user);
        }
    }
    @Test
    void test2(){
        String lua = "return redis.call('scan',ARGV[1],'match',KEYS[1],'count',ARGV[2])";
        RedisScript<List> redisScript = RedisScript.of(lua,List.class);
        List<String> keys = new ArrayList<>();
        keys.add("user.*");
        List execute = stringRedisTemplate.execute(redisScript, keys, "0", "5");
    }
}