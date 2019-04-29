package com.kykj.demo.template;

/**
 * 扩展模板方法
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 18:45
 */
public class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}
