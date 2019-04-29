package com.kykj.demo.template;

/**
 * 优点：
 * 1、封装不变部分，扩展可变部分。
 * 2、提取公共代码，便于维护。
 * 3、行为由父类控制，子类实现。
 * 使用场景：
 * 1、有多个子类共有的方法，且逻辑相同。
 * 2、重要的、复杂的方法，可以考虑作为模板方法。
 * 注意事项：为防止恶意操作，一般模板方法都加上 final 关键词。
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 18:46
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}
