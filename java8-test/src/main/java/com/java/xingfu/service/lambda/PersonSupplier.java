package com.java.xingfu.service.lambda;

import com.java.xingfu.service.lambda.PersonSupplier.Person;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author 掘金
 * @description
 * @date 2018/11/7
 */
public class PersonSupplier implements Supplier<Person> {

    private int index = 0;
    private Random random = new Random();

    @Override
    public Person get() {
        return new Person(index++,"StormTestUser" +index);
    }

    public static class Person{
        private int no;
        private String name;
        public Person(){

        }

        public Person(int no,String name){
            this.no = no;
            this.name =name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "name:" + name +"no" + no;
        }
    }
}
