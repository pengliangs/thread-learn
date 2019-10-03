package juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengliang
 * @date 2019/10/2 10:43
 */
public class CountDownLatchSample {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        int total = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(total);
        for (int i = 1; i <= total; i++) {
            final int index = i;
            threadPool.execute(()->{
               synchronized (CountDownLatchSample.class){
                   try {
                       count += index;
                   } finally{
                       // 提交计数器，每次提交总数-1;确保每次提交
                       countDownLatch.countDown();
                   }
               }
            });
        }

        // 堵塞当前线程，直到 countDownLatch = 0 的适合继续往下走
        countDownLatch.await();
        System.out.println(count);
        threadPool.shutdown();
    }
}
