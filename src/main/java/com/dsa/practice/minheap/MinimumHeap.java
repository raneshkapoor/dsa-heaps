package com.dsa.practice.minheap;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MinimumHeap {

    private final int[][] heapArray;
    private int heapSize;

    private int currLevel;
    private int currPos;

    public MinimumHeap() {
        heapArray = new int[20][];
        heapSize = 0;
        currLevel = 0;
        currPos = 0;
        heapArray[currLevel] = new int[1];
    }

    public int getMin() throws Exception {
        if (heapSize == 0) {
            throw new Exception("Heap is Empty");
        }
        return heapArray[0][0];
    }

    public int popMin() throws Exception {
        if (heapSize == 0) {
            throw new Exception("Heap is Empty");
        }
        int min = heapArray[0][0];

        //  TODO Implement delete min

        return min;
    }

    public void insert(List<Integer> values) {
        values.forEach(this::insert);
    }

    public void insert(int value) {

        //  Check if current level is full, create next level with double size
        if ((currLevel == 0 && currPos == 1) || (currLevel != 0 && currPos == 2 * getParentLevelSize(currLevel))) {
            currLevel++;
            currPos = 0;
            heapArray[currLevel] = new int[2 * getParentLevelSize(currLevel)];
        }

        //  Insert new Element
        heapArray[currLevel][currPos] = value;
        currPos++;
        heapSize++;

        //  Check the Min Heap Properties and swap elements
        int elementLevel = currLevel;
        int elementPos = currPos - 1;
        while (elementLevel > 0) {
            if (heapArray[elementLevel][elementPos] < heapArray[elementLevel - 1][elementPos / 2]) {
                swapElementWithParent(elementLevel, elementPos);
            }
            elementLevel--;
            elementPos /= 2;
        }
    }

    private int getParentLevelSize(int level) {
        return level == 0 ? 0 : heapArray[level - 1].length;
    }

    private int getParentElement(int level, int pos) {
        if (level == 0) {
            return heapArray[0][0];
        }
        return heapArray[level - 1][pos / 2];
    }

    private void swapElementWithParent(int level, int pos) {
        int temp =  heapArray[level][pos];
        heapArray[level][pos] = heapArray[level - 1][pos / 2];
        heapArray[level - 1][pos / 2] = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(heapArray)
                .filter(Objects::nonNull)
                .map(Arrays::toString)
                .forEach(row -> sb.append(row).append("\n"));

        return sb.toString();
    }

}
