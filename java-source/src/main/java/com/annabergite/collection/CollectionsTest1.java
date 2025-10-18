package com.annabergite.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

public class CollectionsTest1 {

    public static void main(String[] args) {
        ArrayList<ItemTest> itemTests = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            itemTests.add(new ItemTest((int) (Math.random()*100),(int) (Math.random()*100),(int) (Math.random()*100)));
        }
//        itemTests.stream().collect(PairListCollectors.create(ItemTest::getItem, ItemTest::getKey));
//        Pair<List<Integer>, List<String>> idNameList=itemTests.stream().collect()
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class ItemTest{
    private Integer item;
    private Integer value;
    private Integer key;
}