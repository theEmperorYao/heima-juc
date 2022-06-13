package com.tang.heimajuc.chap3;


import lombok.extern.slf4j.Slf4j;

/**
 * @Title: Test05TwoPhaseTermination
 * @Description:
 * @author: tangyao
 * @date: 2022/6/13 17:35
 * @Version: 1.0
 */
@Slf4j
public class Test05TwoPhaseTermination {

    private Thread thread;

    public void start1() {
        thread = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("将结果保存");
                } catch (Exception e) {
                    current.interrupt();
                }
            }


        }, "监控线程");
        thread.start();

    }

    public void stop1() {
        //interrupt 可以打断正在执行的线程，无论这个线程是在 sleep，wait，还是正常运行
        thread.interrupt();
    }

    public void stop2() {
        stop = true;
        //interrupt 可以打断正在执行的线程，无论这个线程是在 sleep，wait，还是正常运行
        thread.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        test02();
//        test01();

    }

    private volatile boolean stop = false;

    private void start2() {

        thread = new Thread(() -> {
            while (true) {
                Thread currentThread = Thread.currentThread();
                if (stop) {
                    log.debug("料理后事");
                    break;
                }

                try {
                    Thread.sleep(1000);
                    log.debug("将结果保存");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }, "监控线程");

        thread.start();

    }

    private static void test02() throws InterruptedException {
        Test05TwoPhaseTermination t = new Test05TwoPhaseTermination();
        t.start2();
        Thread.sleep(3500);
        log.debug("stop");
        t.stop2();
    }

    private static void test01() throws InterruptedException {
        Test05TwoPhaseTermination t = new Test05TwoPhaseTermination();
        t.start1();
        Thread.sleep(3500);
        log.debug("stop");
        t.stop1();
    }
}