package com.demo.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author chenxin
 * @date 2019/08/11
 * 普通的java程序包含哪些线程？
 * 输出：
 * 7:JDWP Command Reader
 * 6:JDWP Event Helper Thread
 * 5:JDWP Transport Listener: dt_socket
 * 4:Signal Dispatcher //分发处理发送给JVM信号的线程
 * 3:Finalizer  //调用对象finalizer方法的线程
 * 2:Reference Handler  //清除reference的线程
 * 1:main  //main线程，用户程序入口
 */
public class MultiThread {
    public static void main(String[] args) {
        //获取java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        //遍历线程信息，打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + ":" + threadInfo.getThreadName());
        }
    }
}
