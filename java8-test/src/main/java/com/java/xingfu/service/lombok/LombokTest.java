package com.java.xingfu.service.lombok;

import com.java.xingfu.annotation.Getter;

/**
 * @author 掘金
 * @description
 * @date 2018/11/10
 */
@Getter
public class LombokTest {

    private String value;

    private LombokTest(String value){
        this.value = value;
    }

    public static void main(String[] args) {
        LombokTest lombok = new LombokTest("its work");
        System.out.println(lombok.getValue());
    }
}
