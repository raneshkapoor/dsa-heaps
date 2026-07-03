package com.dsa.practice.maxheap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class MaximumHeapTest {

    @Test
    void testMaximumHeapInsert() throws Exception {
        MaximumHeap maxHeap = new MaximumHeap();

        maxHeap.insert(List.of(10, 20, 30));
        System.out.println(maxHeap);
        Assertions.assertEquals(30, maxHeap.getMax());
        Assertions.assertEquals(3, maxHeap.getHeapSize());

        maxHeap.insert(List.of(40, 50, 60, 70));
        System.out.println(maxHeap);
        Assertions.assertEquals(70, maxHeap.getMax());

        maxHeap.insert(List.of(15, 25, 35, 45, 75));
        System.out.println(maxHeap);
        Assertions.assertEquals(75, maxHeap.getMax());
    }

    @Test
    void testMaximumHeapGetMaxException() {
        MaximumHeap maxHeap = new MaximumHeap();
        Assertions.assertThrows(Exception.class, maxHeap::getMax);
    }

    @Test
    void testMaximumHeapPopMax() throws Exception {

        MaximumHeap maxHeap = new MaximumHeap();
        maxHeap.insert(List.of(10, 20, 30, 40, 50, 60, 70));

        Assertions.assertEquals(70, maxHeap.popMax());
        Assertions.assertEquals(60, maxHeap.getMax());
        Assertions.assertEquals(6, maxHeap.getHeapSize());

        Assertions.assertEquals(60, maxHeap.popMax());
        Assertions.assertEquals(50, maxHeap.popMax());
        Assertions.assertEquals(40, maxHeap.popMax());
        Assertions.assertEquals(30, maxHeap.popMax());

        Assertions.assertEquals(2,  maxHeap.getHeapSize());
    }

    @Test
    void testMaximumHeapPopMaxException() {
        MaximumHeap maxHeap = new MaximumHeap();
        Assertions.assertThrows(Exception.class, maxHeap::popMax);
    }

    @Test
    void testMaximumHeapPopInsert() throws Exception {

        MaximumHeap maxHeap = new MaximumHeap();
        maxHeap.insert(List.of(10, 20, 30, 40, 50, 60, 70));

        maxHeap.popMax();
        maxHeap.popMax();
        maxHeap.popMax();

        maxHeap.insert(55);
        Assertions.assertEquals(5, maxHeap.getHeapSize());
        Assertions.assertEquals(55, maxHeap.popMax());
        Assertions.assertEquals(40, maxHeap.popMax());
        Assertions.assertEquals(30, maxHeap.popMax());
    }

    @Test
    void testMaximumHeapLoadTest() throws Exception {

        List<Integer> data = getRandomList(10000);

        MaximumHeap minHeap = new MaximumHeap();
        minHeap.insert(data);

        int previous = minHeap.popMax();
        int current;

        while (!minHeap.isEmpty()) {
            current = minHeap.popMax();
            Assertions.assertTrue(current < previous);
            previous = current;
        }
    }

    private List<Integer> getRandomList(int limit) {
        Set<Integer> set = new HashSet<>();
        Random random = new Random();

        IntStream.range(0, limit).forEach(i -> set.add(random.nextInt(limit)));

        return new ArrayList<>(set);
    }

}
