package com.dsa.practice.maxheap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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

}
