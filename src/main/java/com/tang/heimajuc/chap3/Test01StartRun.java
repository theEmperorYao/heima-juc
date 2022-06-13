package com.tang.heimajuc.chap3;


import lombok.extern.slf4j.Slf4j;

/**
 * @Title: Test01StartRun
 * @Description:
 * @author: tangyao
 * @date: 2022/6/8 16:25
 * @Version: 1.0
 */
@Slf4j
public class Test01StartRun {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug(Thread.currentThread().getName());
        }, "A");

        //直接调用 run 是在主线程中执行了 run，没有启动新的线程
        //使用 start 是启动新的线程，通过新的线程间接执行 run 中的代码
        t1.run();
//        t1.start();

        log.debug("do other things");

    }

}