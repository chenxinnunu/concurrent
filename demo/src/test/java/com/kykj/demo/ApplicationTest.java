package com.kykj.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 由于一个项目中会写很多很多测试类，
 * 而测试类上面是需要以下几个注解的，每建一个类都去补注解，太麻烦，
 * 在这个类中加上注解，其他测试类直接继承这个类就好了
 * 如果是Web项目，Junit需要模拟ServletContext，因此我们需要在测试类加上@WebAppConfiguration。
 * @author chenxinnunu@gmail.com
 * @date 2019/4/2 12:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }
}
