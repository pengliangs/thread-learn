package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author pengliang
 * @date 2019/10/2 14:07
 */
public class SemaphoreSample {


    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        Semaphore semaphoreSample = new Semaphore(5);

        for (int i = 0; i < 20; i++) {
            threadPool.execute(()->{
                try {
                    // 获取信号量
                    //semaphoreSample.acquire();
                    // 尝试获取信号量，等待5秒
                    if(semaphoreSample.tryAcquire(5, TimeUnit.SECONDS)){
                        play();
                        // 释放信号量
                        semaphoreSample.release();
                    }else {
                        System.out.println("服务器爆满，请稍后再试...");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        threadPool.shutdown();
    }

    private static void play(){
        System.out.println(Thread.currentThread().getName()+":进入紫荆之巅...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":推出紫荆之巅...");
    }
}
