package com.annabergite.springcli;

/**
 * @PACKAGE_NAME: com.annabergite.springcli
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/1/24 20:12
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/

package com.annabergite;


import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//实现根据商品id批量查询拍品数据的接口
//要求取先从缓存中获取数据，取不到，再从数据库中获取
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private ItemDao itemDdao;

    public static void main(String[] args) {

        List<Long> cacheList = new ArrayList<>();
        Map<Long, ItemInfo> itemInfoMap = cacheList.stream().collect(Collectors.toMap(ItemInfo::getItemId, each -> each, (value1, value2) -> value1));
    }

    /**
     * 批量根据商品id查询拍品数据，该接口会先查询缓存，缓存不存在时会查询数据库。
     *
     * @param itemIds
     * @return
     */
    @Override
    public Map<Long, ItemInfo> getItemInfos(List<Long> itemIds) {
        //先搞一个list用于存储不存在的id，另一个存储可以从cache里面找到的id
        List<Long> notExistItemIds = new ArrayList<>();
        List<Long> existItemIds = new ArrayList<>();
        notExistItemIds.addAll(itemIds);
        //获取到有的数据
        List<Serializable> cacheList = (ItemInfo) cacheManager.getCache(itemIds);
        //使用map承接
        Map<Long, ItemInfo> itemInfoMap = cacheList.stream().collect(Collectors.toMap(ItemInfo::getItemId, each -> each, (value1, value2) -> value1));
        existItemIds = cacheList.stream().map(ItemInfo::getItemId).collect(Collectors.toList());
        //取个差集，不过这个也可以使用collection的操作jar包实现
        notExistItemIds.removeAll(existItemIds);
        //从数据库里面查询一下，如果redis和MySQL之间存在布隆过滤器的话可以过一下，防止缓存穿透
        ItemInfo temp = null;
        for (Long itemId : notExistItemIds) {
            temp = itemDdao.getItemById(itemId);
            if (temp != null) {
                itemInfoMap.put(itemId, temp);
                //更新缓存,假设30s内会用到
                cacheManager.putCache(itemId, temp, 30L);
            }
        }
        return itemInfoMap;
    }
}


// 以下为提供的接口，无需写实现类。
@Data
public class ItemInfo implements Serializable {
    private long itemId;
    private String title;
    private String desc;
}

public interface CacheManager {
    /**
     * 批量从缓存中获取数据，只返回缓存中存在的数据
     *
     * @param keys 获取数据的key列表
     * @return
     */
    List<Serializable> getCache(List<Serializable> keys);

    /**
     * 从缓存中获取数据，缓存中不存在返回null
     *
     * @param key 获取数据的key
     * @return
     */
    Serializable getCache(Serializable key);

    /**
     * 将数据放入缓存中
     *
     * @param key        获取数据的key
     * @param data       需要放入到缓存中的数据
     * @param expireTime 缓存失效时间
     * @return
     */
    boolean putCache(Serializable key, Serializable data, long expireTime);

    /**
     * 失效商品缓存
     *
     * @param key 获取数据的key
     */
    boolean invalid(Serializable key);
}

public interface ItemDao {
    /**
     * 从数据库中查询商品数据，查询不到则返回null
     */
    ItemInfo getItemById(long itemId);
}


/*
 问题：日志文件中login代表登陆，visit表示访问。第3列为对应用户名。
 找出登陆最多的前10个用户。
 1、按要求输出结果
 2、文件较大时考虑使用多线程加速
 3、考虑多机环境下的实现（可使用文字描述思路，可使用上面的实现方法）
输入文件格式：
------------------
902 login lilei www.taobao.com
220 login hmm s.taobao.com
499 visit  cde i.taobao.com
879 login  lilei s.taobao.com
------------ ------


------------------
输出
    2 lilei
    1 hmm
————————-
*/

