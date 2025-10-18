package com.annabergite.functional;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        List<Integer> collect = integers.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
