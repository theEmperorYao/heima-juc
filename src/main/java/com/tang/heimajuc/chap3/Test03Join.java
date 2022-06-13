package com.tang.heimajuc.chap3;


import lombok.extern.slf4j.Slf4j;

/**
 * @Title: Test03Join
 * @Description:
 * @author: tangyao
 * @date: 2022/6/8 16:46
 * @Version: 1.0
 */
@Slf4j
public class Test03Join {

    static int r = 0;
    static int r1 = 0;
    static int r2 = 0;

    public static void main(String[] args) {
        test03();
//        test02();
//        test01();
    }

    private static void test03() {
        Thread t1 = new Thread(() -> {
            sleep(2);
            r1 = 10;
        }, "t1");
        long start = System.currentTimeMillis();
        t1.start();
        try {
            t1.join(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        log.debug("r1 {} r2 {} cost {}", r1, r2, System.currentTimeMillis() - start);
    }

    private static void test02() {
        Thread t1 = new Thread(() -> {
            sleep(1);
            r1 = 10;
        }, "t1");
        Thread t2 = new Thread(() -> {
            sleep(2);
            r2 = 20;
        }, "t2");

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("r1 {} r2 {} cost {}", r1, r2, System.currentTimeMillis() - start);


    }

    public static void sleep(double n) {
        try {
            Thread.sleep((int) n * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test01() {
        log.debug("开始");
        Thread t1 = new Thread(() -> {
            log.debug("开始");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("结束");
            r = 10;
        }, "A");

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("结果为：{}", r);
        log.debug("结束");
    }
}