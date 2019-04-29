package com.kykj.demo.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 创建线程池
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/2 16:25
 */
@Configuration
public class TreadPoolConfig {
    /**
     * 消费队列线程
     *
     * @return
     */
    @Bean(value = "consumerQueueThreadPool")
    public ExecutorService buildConsumerQueueThreadPool() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("consumer-queue-thread-%d").build();
        /*corePoolSize 为线程池的基本大小。
        maximumPoolSize 为线程池最大线程大小。
        keepAliveTime 和 unit 则是线程空闲后的存活时间。
        workQueue 用于存放任务的阻塞队列。
        handler 当队列和最大线程池都满了之后的饱和策略。*/
        ExecutorService pool = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }
}
