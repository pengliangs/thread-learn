package juc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author pengliang
 * @date 2019/10/2 16:13
 */
public class CopyArrayListSample {

    public static void main(String[] args) {
        List<Integer> numbers = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 100; i++) {
            numbers.add(i);
        }

        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            numbers.remove(num);
        }
        System.out.println(numbers);
    }
}
