package com.annabergite.gupao;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @PACKAGE_NAME: com.annabergite.gupao
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/3/24 14:53
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/

@Data
public class GoodsInfo implements Serializable {
    //             商品的SKUID
    private Long skuId;
    //         商品价格，用于计算（单位：分）
    private Long salePrice;

    public static Map<Long, List<GoodsInfo>> question1(String str) {
        GoodsInfo goodsInfo1 = new GoodsInfo();
        goodsInfo1.setSkuId(1L);
        goodsInfo1.setSalePrice(10L);
        GoodsInfo goodsInfo2 = new GoodsInfo();
        goodsInfo2.setSkuId(1L);
        goodsInfo2.setSalePrice(11L);
        GoodsInfo goodsInfo3 = new GoodsInfo();
        goodsInfo3.setSkuId(2L);
        goodsInfo3.setSalePrice(22L);
        GoodsInfo goodsInfo4 = new GoodsInfo();
        goodsInfo4.setSkuId(2L);
        goodsInfo4.setSalePrice(23L);
        GoodsInfo goodsInfo5 = new GoodsInfo();
        goodsInfo5.setSkuId(2L);
        goodsInfo5.setSalePrice(24L);

        List<GoodsInfo> items = Lists.newArrayList(goodsInfo1, goodsInfo2, goodsInfo3, goodsInfo4, goodsInfo5);


// 按照商品类型分组，给出对应的价格
        Map<Long, List<GoodsInfo>> map2 = items.stream().sorted(Comparator.comparing(GoodsInfo::getSalePrice)).collect(Collectors.groupingBy(GoodsInfo::getSkuId));
        System.out.println(map2);
        return map2;
    }

    // 将下面的代码改成lamda表达式实现
    public static int question2(String str) {
        List<String> list = Lists.newArrayList("a", "b", "c", "d");
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(str, list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    // 将下面的代码改成lamda表达式实现
    public static int question2Turn2Good(String str) {
        List<String> list = Lists.newArrayList("a", "b", "c", "d");
        AtomicInteger index = new AtomicInteger(0);

        list.stream()
                //指定匹配逻辑
                .filter(s -> {
                    //每比对一个元素，数值加1
                    index.getAndIncrement();

                    return s.equals(str);
                })
                .findFirst();
        if (index.get() == 0 && list.get(0).equals(str)) {
            return -1;
        }
        return index.get();
    }

    public static void main(String[] args) {
        System.out.println(question2("tesdt"));
        ;
    }

}
