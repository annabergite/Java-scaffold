package com.annabergite.springcli.exam2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @PACKAGE_NAME: com.annabergite.springcli.exam2
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/1/27 13:54
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/

/**
 * [10011,1,70]
 * [91011,2,90]
 * [20011,2,60]
 * [30011,1,50]
 * [40011,1,10]
 * [15011,2,20]
 * [16011,1,30]
 * [80011,2,80]
 * [90011,1,15]
 */
@Data
public class ItemRerank {
    public static void main(String[] args) {
        List<ItemDO> itemList = new ArrayList<>();
        itemList.add(new ItemDO(10011, 1, 10));
        itemList.add(new ItemDO(91011, 2, 20));
        itemList.add(new ItemDO(20011, 3, 30));
        itemList.add(new ItemDO(30011, 2, 23));
        itemList.add(new ItemDO(40011, 1, 50));
        itemList.add(new ItemDO(15011, 1, 45));
        itemList.add(new ItemDO(16011, 2, 50));
        itemList.add(new ItemDO(80011, 3, 66));
        itemList.add(new ItemDO(90011, 2, 888));
        List<ItemDO> rerank = rerank(itemList);
        for (ItemDO itemDO : rerank) {
            System.out.println(itemDO);
        }
    }

    public static List<ItemDO> rerank(List<ItemDO> itemList) {
        if (itemList == null || itemList.size() == 0) {
            return itemList;
        }
        //过滤掉空类型
        itemList = itemList.stream().filter(x -> x != null).collect(Collectors.toList());
        if (itemList.size() == 0) {
            return new ArrayList<>();
        }
        //把原先的类型的类型都给存储一下
        List<Integer> typeList = itemList.stream().map(ItemDO::getType).collect(Collectors.toList());
        Set<Integer> types = typeList.stream().collect(Collectors.toSet());

        HashMap<Integer, List<ItemDO>> mapList = new HashMap<>(types.size());
        HashMap<Integer, Integer> arrayNo = new HashMap<>(types.size());
        for (Integer type : types) {
            mapList.put(type, itemList.stream().filter(x -> x.getType() == type).sorted(Comparator.comparing(ItemDO::getScore).reversed()).collect(Collectors.toList()));
            arrayNo.put(type, 0);
        }
        List<ItemDO> result = new ArrayList<>(itemList.size());
        //合并两个集合,并且按照type往里面加
        for (Integer itemType : typeList) {
            for (Integer type : types) {
                //如果在传入参数list的位置的type和set中的某一个type重合了，说明可以往返回参数result里面添加值，并且推出typeset的循环
                if (type == itemType) {
                    result.add(mapList.get(type).get(arrayNo.get(type)));
                    //自增一
                    arrayNo.put(type, arrayNo.get(type) + 1);
                    break;
                }
            }
        }
        itemList = result;
        return itemList;
    }

}