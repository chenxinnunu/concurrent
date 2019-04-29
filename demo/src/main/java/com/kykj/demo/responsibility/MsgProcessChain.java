package com.kykj.demo.responsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * 执行责任链的入口
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 10:50
 */
public class MsgProcessChain {
    private List<ProcessTest> chains = new ArrayList<>();

    /**
     * 添加责任链
     */
    public MsgProcessChain addChain(ProcessTest processTest) {
        chains.add(processTest);
        return this;
    }

    /**
     * 执行处理
     */
    public void process(String msg) {
        for (ProcessTest chain : chains) {
            chain.doProcess(msg);
        }
    }
}
