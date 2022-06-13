package com.tang.heimajuc.chap3;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

import static com.tang.heimajuc.chap3.Test03Join.sleep;

/**
 * @Title: Test06ParkTest
 * @Description:
 * @author: tangyao
 * @date: 2022/6/13 17:57
 * @Version: 1.0
 */
@Slf4j
public class Test06Park {

    public static void main(String[] args) {
        test02();
//        test01();
    }

    private static void test02() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                log.debug("park...");
                LockSupport.park();
                log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
            }


        }, "t1");

        t1.start();
        sleep(1);
        //打断 park 线程, 不会清空打断状态
        t1.interrupt();
    }

    private static void test01() {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());

        }, "t1");

        t1.start();
        sleep(0.5);
        //打断 park 线程, 不会清空打断状态
        t1.interrupt();
    }
}