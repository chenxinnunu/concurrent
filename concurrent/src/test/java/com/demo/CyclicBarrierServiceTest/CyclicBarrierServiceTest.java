package com.demo.CyclicBarrierServiceTest;

import com.demo.ApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.*;

/**
 * CyclicBarrier的应用场景
 * CyclicBarrier可以用于多线程计算数据，最后合并计算结果的场景。
 * 例如：用一个Excel保存了用户所有银行流水，每个sheet保存一个账号近一年的没笔银行流水，
 * 现在需要统计用户的日均银行流水，先用多线程处理每个sheet里的银行流水，都执行之后，
 * 得到每个sheet的日均银行流水，最后，再用barrierAction用这些线程的计算结果，计算出整个Excel的日均银行流水
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/21 17:38
 */
public class CyclicBarrierServiceTest extends ApplicationTest implements Runnable{

    @Resource(name = "threadPool")
    private ExecutorService threadPool;
    /**
     * 创建4个屏障，处理完之后执行当前类的run方法
     */
    private CyclicBarrier c = new CyclicBarrier(4, this);

    /**
     * 用多线程安全的ConcurrentHashMap保存每个sheet计算出的流水结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    public void count() {
        //假设只有4个sheet，从线程池启动线程
        for (int i = 0; i < 4; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //计算当前sheet的流水数据,假设计算结果是1，放入map
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                    System.out.println("fasfasfafaf");
                    //流水计算完，插入一个屏障
                    try {
                        c.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    @Override
    public void run() {
        int result = 0;
        //汇总每个sheet计算出的结果
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        //输出结果
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }

    @Test
    public void get() {
        CyclicBarrierServiceTest cyclicBarrierServiceTest = new CyclicBarrierServiceTest();
        cyclicBarrierServiceTest.count();
    }
}
