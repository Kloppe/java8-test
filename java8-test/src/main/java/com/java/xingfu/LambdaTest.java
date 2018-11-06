package com.java.xingfu;

import com.google.common.collect.ImmutableList;
import com.java.xingfu.bo.AA;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 掘金
 * @description
 * @date 2018/11/6
 */
public class LambdaTest {
      static   AA a1 = new AA(123F, 2, "xuww");
    static AA a2 = new AA(113F, 2, "xuww");
    static AA a3 = new AA(133F, 2, "xuww");
    static AA a4 = new AA(1313F, 2, "xuww");
    static  AA a6 = new AA(1F, 2, "xuww");
    private static List<AA> list = ImmutableList.of(a1,a2,a3,a4,a6);

    public static void main(String[] args) {
        compareTest();
        toArray();
        joing();
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
