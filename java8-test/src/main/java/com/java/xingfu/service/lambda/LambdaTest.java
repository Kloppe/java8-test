package com.java.xingfu.service.lambda;

import com.google.common.collect.ImmutableList;
import com.java.xingfu.bo.AA;
import com.java.xingfu.service.lambda.PersonSupplier.Person;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 掘金
 * @description
 * @date 2018/11/6
 */
public class LambdaTest {
      static   AA a1 = new AA(123F, 2, "xuww");
    static AA a2 = new AA(133F, 2, "xuww");
    static AA a3 = new AA(133F, 2, "xuww");
    static AA a4 = new AA(1313F, 2, "xuww");
    static  AA a6 = new AA(1F, 2, "xuww");
    private static List<AA> list = ImmutableList.of(a1,a2,a3,a4,a6);

    public static void main(String[] args) {
        compareTest();
        toArray();
        joing();
        toUpperCase();
        flatMap();
        System.out.println(optional(null));
        reduce();
        limit();
        sorted();
        max();
        match();
        generate();
        iterate();
        grouping();
        partitioning();
    }

    private static void partitioning() {
        Map<Boolean,List<Person>> children = Stream.generate(new PersonSupplier()).limit(10).collect(Collectors.partitioningBy(p -> p.getName().length()>0));
        System.out.println(children.get(true));
        System.out.println(children.get(false));
    }

    private static void grouping() {
        Map<Float,List<AA>> aa = list.stream().collect(Collectors.groupingBy(AA::getPrice));
        System.out.println(aa);
    }

    private static void iterate() {
        Stream.iterate(0,n -> n+3).limit(10).forEach(x -> System.out.print(x + "  "));
    }

    private static void generate() {
        Random random  = new Random(1000);
        Supplier<Integer> supplier = random::nextInt;
        Stream.generate(supplier).limit(10).forEach(System.out::println);

        Stream.generate(new PersonSupplier()).limit(10).forEach(e -> System.out.println(e.getName()));
    }

    private static void match() {
        Boolean b = list.stream().allMatch(e -> e.getPrice() > 0);
        System.out.println(b);
    }

    private static void max() {
        int sum = list.stream().mapToInt(AA::getNum).sum();
        System.out.println(sum);
    }

    private static void sorted() {
        list.stream().sorted((p1,p2) -> p1.getPrice().compareTo(p2.getPrice())).map(AA::getPrice).forEach(System.out::println);
    }

    private static void limit() {
        list.stream().limit(2).skip(1).forEach(System.out::print);
    }

    private static void reduce() {
        String concat = Stream.of("1","2","a","b").reduce("",String::concat);
        System.out.print(concat);
        double minValue =Stream.of(3.2,1.1,1.1,-3.0,-341.1).reduce(Double.MAX_VALUE,Double::min);
        System.out.print(minValue);
        System.out.println(Stream.of(1,2,3,5).reduce(0,Integer::sum));

    }

    private static int optional(String text) {
        return Optional.ofNullable(text).map(String::length).orElse(-1);
    }

    private static void flatMap() {
        Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1,2),Arrays.asList(4,6,7), Collections.singletonList(2),ImmutableList.of(1,2,3));
        inputStream.flatMap(Collection::stream).forEach(System.out::print);

    }

    private static void toUpperCase() {
        List<String> upperCaseNames = list.stream().map(AA::getName).map(String::toUpperCase).collect(Collectors.toList());
        upperCaseNames.forEach(System.out::print);
    }

    private static void joing() {
        System.out.println(list.stream().map(AA::getName).collect(Collectors.joining(",")));

    }

    private static void toArray() {
        AA a1 = new AA(123F,2,"xuww");
        AA a2 = new AA(113F,2,"xuww");
        AA a3 = new AA(133F,2,"xuww");
        AA a4 = new AA(1313F,2,"xuww");
        AA a6 = new AA(1F,2,"xuww");

        List<AA> list1 = ImmutableList.of(a1,a2,a3,a4,a6);
        String[] strArray1 = list1.stream().map(AA::getName).toArray(String[]::new);
        System.out.println("toArray########");
        Stream.of(strArray1).forEach(System.out::println);

    }

    private static void compareTest() {
        AA a1 = new AA(123F,2,"xuww");
        AA a2 = new AA(113F,2,"xuww");
        AA a3 = new AA(133F,2,"xuww");
        AA a4 = new AA(1313F,2,"xuww");
        AA a6 = new AA(1F,2,"xuww");

        List<AA> list1 = ImmutableList.of(a1,a2,a3,a4,a6);
        List<AA> a = list1.stream().filter(e -> e.getName().equals("xuww")).sorted(Comparator.comparing(AA::getPrice).reversed()).collect(Collectors.toList());
        List<Float> prices = list1.stream().filter(e -> e.getName().equals("xuww")).sorted(Comparator.comparing(AA::getPrice).reversed()).map(AA::getPrice).collect(
            Collectors.toList());
        System.out.println("###compare####");
        System.out.println(a);
        System.out.println(prices);

    }

}
