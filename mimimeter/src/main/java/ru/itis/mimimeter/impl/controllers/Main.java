package ru.itis.mimimeter.impl.controllers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        List<Integer> cats = IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(cats);
        Map<Integer, Integer> pairs = new HashMap<>();
        Integer previous = null;
        int counter = 1;

        for (Integer cat : cats) {
            if (counter % 2 == 0) {
                pairs.put(previous, cat);
            } else {
                pairs.put(cat, null);
            }
            previous = cat;
            counter++;
        }

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(2);
        System.out.println(set);
//        System.out.println(pairs);
//        System.out.println(cats);
    }
}
