package com.kykj.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author chenxinnunu@gmail.com
 * @date 2019/4/24 10:46
 */
public class Test {
    public static void main(String[] args) {
/*        Work w = new Work();
        w.setName(null);
        w.setAge(null);
        System.out.println(w.getAge());*/
        System.out.println(get());

    }

    private static int get() {
        int a = 1;
        try {
            return a;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            a++;
        }
        return a;
    }
}
