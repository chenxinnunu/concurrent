package com.kykj.demo.future;

import java.util.concurrent.Callable;

/**
 * 有返回值的任务
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/2 16:05
 */
public class CallableTest implements Callable {
    @Override
    public Object call() throws Exception {
        Thread.sleep(500);
        int a = 1;
        int b = 2;
        int c = a + b;
        return c;
    }
}
