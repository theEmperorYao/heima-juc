package com.tang.heimajuc.chap3;


/**
 * @Title: Test02SleepYield
 * @Description:
 * @author: tangyao
 * @date: 2022/6/8 16:30
 * @Version: 1.0
 */

public class Test02SleepYield {

    public static void main(String[] args) {

        Runnable task1 = () -> {
            int count = 0;
            while (true) {
                Thread.yield();
                System.out.println("------>" + count++);
            }
        };

        Runnable task2 = () -> {
            int count = 0;
            while (true) {
//                Thread.yield();
                System.out.println("                   ------>" + count++);
            }
        };

        Thread t1 = new Thread(task1, "t1");
        Thread t2 = new Thread(task2, "t2");

        //线程优先级会提示（hint）调度器优先调度该线程，但它仅仅是一个提示，调度器可以忽略它
        //如果 cpu 比较忙，那么优先级高的线程会获得更多的时间片，但 cpu 闲时，优先级几乎没作用
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();


    }
}