package com.xuan.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * jdk8 Stream
 */
public class StreamApplication {
    public static void main(String[] args) {
        List<String> names = generateNames();
        testMap(names);
        testSorted(names);
        testFilter(names);
        testDistinct(names);
        testCount(names);
        testReduce(names);
    }

    public static void testMap(List<String> names) {
        List<String> namesNew = names.stream().map(String::toLowerCase).collect(Collectors.toList());
        System.out.println(namesNew);
        List<Integer> namesLength = names.stream().map(String::length).collect(Collectors.toList());
        System.out.println(namesLength);
    }

    public static void testSorted(List<String> names) {
       List<String> namesNew = names.stream().sorted().collect(Collectors.toList());
       System.out.println(namesNew);
    }

    public static void testFilter(List<String> names) {
        List<String> namesNew = names.stream().filter(name->name.length()<4).collect(Collectors.toList());
        System.out.println(namesNew);
    }

    public static void testDistinct(List<String> names) {
        List<String> namesNew = names.stream().distinct().collect(Collectors.toList());
        System.out.println(namesNew);
    }

    public static void testCount(List<String> names) {
        long namesCount = names.stream().count();
        System.out.println(namesCount);
    }

    public static void testReduce(List<String> names) {
        String sum  =  names.stream().reduce(null,(a, b)->a+"_"+b);
        System.out.println(sum);
    }


    public static List<String> generateNames() {
        List<String> names = Arrays.asList("Grace", "Bob", "Charlie", "David",
                "Eve", "Frank", "Frank", "Alice", "Henry", "Ivy","Grace", "Jack");
        return names;
    }

}
