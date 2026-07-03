package com.dsa.practice.minheap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class MinimumHeapTest {

    @Test
    void testMinimumHeapInsert() throws Exception {
        MinimumHeap minHeap = new MinimumHeap();

        minHeap.insert(List.of(10, 20, 30));
        System.out.println(minHeap);
        Assertions.assertEquals(10, minHeap.getMin());
        Assertions.assertEquals(3, minHeap.getHeapSize());

        minHeap.insert(List.of(40, 50, 60, 70));
        System.out.println(minHeap);
        Assertions.assertEquals(10, minHeap.getMin());

        minHeap.insert(5);
        System.out.println(minHeap);
        Assertions.assertEquals(5, minHeap.getMin());

        minHeap.insert(List.of(15, 25, 35, 45));
        System.out.println(minHeap);
        Assertions.assertEquals(5, minHeap.getMin());
    }

    @Test
    void testMinimumHeapGetMinException() {
        MinimumHeap minHeap = new MinimumHeap();
        Assertions.assertThrows(Exception.class, minHeap::getMin);
    }

    @Test
    void testMinimumHeapPopMin() throws Exception {

        MinimumHeap minHeap = new MinimumHeap();
        minHeap.insert(List.of(10, 20, 30, 40, 50, 60, 70));

        Assertions.assertEquals(10, minHeap.popMin());
        Assertions.assertEquals(20, minHeap.getMin());
        Assertions.assertEquals(6, minHeap.getHeapSize());

        Assertions.assertEquals(20, minHeap.popMin());
        Assertions.assertEquals(30, minHeap.popMin());
        Assertions.assertEquals(40, minHeap.popMin());
        Assertions.assertEquals(50, minHeap.popMin());

        Assertions.assertEquals(2,  minHeap.getHeapSize());
    }

    @Test
    void testMinimumHeapPopMinException() {
        MinimumHeap minHeap = new MinimumHeap();
        Assertions.assertThrows(Exception.class, minHeap::popMin);
    }

    @Test
    void testMinimumHeapPopInsert() throws Exception {

        MinimumHeap minHeap = new MinimumHeap();
        minHeap.insert(List.of(10, 20, 30, 40, 50, 60, 70));

        minHeap.popMin();
        minHeap.popMin();
        minHeap.popMin();

        minHeap.insert(55);
        Assertions.assertEquals(5, minHeap.getHeapSize());
        Assertions.assertEquals(40, minHeap.popMin());
        Assertions.assertEquals(50, minHeap.popMin());
        Assertions.assertEquals(55, minHeap.popMin());
    }

    @Test
    void testMinimumHeapLoadTest() throws Exception {

        List<Integer> data = getRandomList(10000);

        MinimumHeap minHeap = new MinimumHeap();
        minHeap.insert(data);

        int previous = minHeap.popMin();
        int current;

        while (!minHeap.isEmpty()) {
            current = minHeap.popMin();
            Assertions.assertTrue(current > previous);
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
