package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pengliang
 * @date 2019/10/2 15:21
 */
public class ConditionSample {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 =  lock.newCondition();
        Condition c3 =  lock.newCondition();

        //使用condition对线程中的诗句有序输出
        new Thread(()-> {
            lock.lock();
            try {
                c1.await();
                System.out.println("汗滴禾下土");
                Thread.sleep(1000);
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(()-> {
            lock.lock();
            try{
                System.out.println("锄禾日当午");
                Thread.sleep(1000);
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try{
                c3.await();
                System.out.println("粒粒皆辛苦");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(()-> {
            lock.lock();
            try {
                c2.await();
                System.out.println("谁知盘中餐");
                Thread.sleep(1000);
                c3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }

}

