package com.kykj.demo.template;

/**
 * @author chenxinnunu@gmail.com
 * @date 2019/4/4 18:40
 */
public abstract class Game {
    /**
     * 初始化游戏
     */
    abstract void initialize();

    /**
     * 开始游戏
     */
    abstract void startPlay();

    /**
     * 结束游戏
     */
    abstract void endPlay();

    /**
     * 模板，设置成final，防止串改
     */
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
}
