package thread;

import java.util.concurrent.*;

/**
 * @author pengliang
 * @date 2019/10/1 20:44
 */
public class CreateThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadOne threadOne = new ThreadOne();
        threadOne.start();

        Thread threadTwo = new Thread(new ThreadTwo());
        threadTwo.start();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Integer> future = executorService.submit(new ThreadThree());
        System.out.println("线程执行返回结果："+future.get());
        executorService.shutdown();
    }

}

class ThreadOne extends Thread {

    @Override
    public void run() {
        System.out.println(this.getName() + "，ThreadOne执行");
    }

}

class ThreadTwo implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "，ThreadTwo执行");
    }

}

class ThreadThree implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("执行ThreadThree");
        return 3;
    }

}