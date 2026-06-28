package com.dsa.practice.minheap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MinimumHeapTest {

    @Test
    void testMinimumHeapInsert() throws Exception {
        MinimumHeap minHeap = new MinimumHeap();

        minHeap.insert(List.of(10, 20, 30));
        System.out.println(minHeap);
        Assertions.assertEquals(10, minHeap.getMin());

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

}
