package io.javabrains.reactiveworkshop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list1 = Collections.synchronizedList(new ArrayList<>());
        List<Integer> list2 = new CopyOnWriteArrayList<>();

        fillList(list1, 100);
        fillList(list2, 100);

        checkList(list1);
        checkList(list2);
    }

    private static void checkList(List<Integer> list) throws ExecutionException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Long> task1 = service.submit(new ListRunner(0, 50, list, latch));
        Future<Long> task2 = service.submit(new ListRunner(51, 100, list, latch));
        latch.countDown();
        System.out.println("task1 = " + task1.get());
        System.out.println("task2 = " + task2.get());
        service.shutdown();
    }

    static class ListRunner implements Callable<Long> {
        int min;
        int max;
        List<Integer> list;
        CountDownLatch latch;

        public ListRunner(int min, int max, List<Integer> list, CountDownLatch latch) {
            this.min = min;
            this.max = max;
            this.list = list;
            this.latch = latch;
        }

        @Override
        public Long call() throws Exception {
            latch.await();
            long start = System.nanoTime();

            for (int i = min; i < max; i++) {
                list.iterator().next();
            }

            return (System.nanoTime() - start);
        }
    }

    private static void fillList(List<Integer> list, int range) {
        for (int i = 0; i < range; i++) {
            list.add(i);
        }
    }
}



