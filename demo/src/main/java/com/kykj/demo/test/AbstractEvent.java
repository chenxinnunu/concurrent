package com.kykj.demo.test;

import org.springframework.context.ApplicationEvent;

/**
 * 抽象类继承ApplicationEvent类，其对象是一个Spring容器事件，可以规定泛型
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/2 11:01
 */
public abstract class AbstractEvent<T> extends ApplicationEvent {
    private T load;

    public AbstractEvent(T source) {
        super(source);
        this.load = source;
    }

    public T getLoad() {
        return load;
    }

    public void setLoad(T load) {
        this.load = load;
    }
}
