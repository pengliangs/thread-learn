package threadpool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author pengliang
 * @date 2019/10/2 10:07
 */
public class ThreadPoolSample {
    public static void main(String[] args) {
        scheduledThreadPool();
    }

    private static void scheduledThreadPool() {
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(5);

        //scheduledPool.schedule(() -> System.out.println("延迟三秒执行")
        //,3, TimeUnit.SECONDS);
        //每3秒执行一次
        scheduledPool.scheduleWithFixedDelay(() ->
                System.out.println(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"延迟三秒执行")
                ,1,3, TimeUnit.SECONDS);
        // 等待所有线程执行完毕，关闭线程池
        //scheduledPool.shutdown();
    }

    private static void singleThreadPool() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            final int index = i;
            threadPool.submit(() -> System.out.println(Thread.currentThread().getName() + ":" + index));
        }
        // 等待所有线程执行完毕，关闭线程池
        threadPool.shutdown();
    }

    private static void fixedThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 1000; i++) {
            final int index = i;
            threadPool.submit(() -> System.out.println(Thread.currentThread().getName() + ":" + index));
        }
        // 等待所有线程执行完毕，关闭线程池
        threadPool.shutdown();
    }

    private static void cachedThreadPool() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            final int index = i;
            threadPool.submit(() -> System.out.println(Thread.currentThread().getName() + ":" + index));
        }
        // 立即关闭线程池，不等待线程执行是否完成，不推荐使用
        // threadPool.shutdownNow();
        // 等待所有线程执行完毕，关闭线程池
        threadPool.shutdown();
    }
}
