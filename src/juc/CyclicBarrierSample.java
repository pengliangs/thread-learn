package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengliang
 * @date 2019/10/2 14:42
 */
public class CyclicBarrierSample {

    public static void main(String[] args) throws InterruptedException {
        //创建一个屏障，数量为5
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        ExecutorService threadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "：准备就绪...");
                try {
                    //设置屏障点,当累计到5个线程数量时才继续向后执行
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "：运行结束...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        threadPool.shutdown();
    }
}
