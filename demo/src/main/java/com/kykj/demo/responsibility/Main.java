package com.kykj.demo.responsibility;

/**
 * 测试
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/3 10:54
 */
public class Main {
    public static void main(String[] args) {
        String msg = "又下雨了";

        MsgProcessChain msgProcessChain = new MsgProcessChain()
                .addChain(new SensitiveWordProcess())
                .addChain(new TypoProcess())
                .addChain(new CopyrightProcess());

        msgProcessChain.process(msg);
    }
}
