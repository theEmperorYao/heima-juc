package com.tang.heimajuc.chap3;


import lombok.extern.slf4j.Slf4j;

import static com.tang.heimajuc.chap3.Test03Join.sleep;

/**
 * @Title: Test04Interrupt
 * @Description:
 * @author: tangyao
 * @date: 2022/6/13 17:14
 * @Version: 1.0
 */
@Slf4j
public class Test04Interrupt {

    public static void main(String[] args) {

        test02();
//        test01();

    }

    private static void test02() {

        Thread t2 = new Thread(() -> {
            while (true) {
                Thread thread = Thread.currentThread();
                boolean interrupted = thread.isInterrupted();
                if (interrupted) {
                    log.debug(" 打断状态 ：{}", interrupted);
                    break;
                }

            }
        }, "t2");
        t2.start();
        sleep(3);
        //打断 sleep 的线程, 会清空打断状态
        t2.interrupt();

    }


    private static void test01() {
        // sleep wait join 都会让线程就如阻塞状态
        Thread t1 = new Thread(() -> {
            sleep(1);
        }, "t1");
        t1.start();
        sleep(0.5);
        //打断正常运行的线程, 不会清空打断状态
        t1.interrupt();
        log.debug("打断状态：{}", t1.isInterrupted());
    }
}