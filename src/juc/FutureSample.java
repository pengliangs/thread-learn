package juc;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author pengliang
 * @date 2019/10/2 15:46
 */
public class FutureSample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 1000; i++) {
            Future<Boolean> future = threadPool.submit(new EvenNumber(i));
            // future.get()等待获取返回结果，直到线程call方法调用完毕
            if(Objects.equals(future.get(),Boolean.TRUE)){
                System.out.println(i);
            }
        }

        threadPool.shutdown();
    }
}

class EvenNumber implements Callable<Boolean> {

    private int num;

    public EvenNumber(int num) {
        this.num = num;
    }

    @Override
    public Boolean call() throws Exception {
        return num % 2 == 0;
    }
}