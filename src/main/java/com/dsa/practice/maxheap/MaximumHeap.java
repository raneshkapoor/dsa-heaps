package com.dsa.practice.maxheap;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MaximumHeap {

    private final int[][] heapArray;
    private int heapSize;

    private int currLevel;
    private int currPos;

    public MaximumHeap() {
        heapArray = new int[20][];
        heapSize = 0;
        currLevel = 0;
        currPos = 0;
        heapArray[currLevel] = new int[1];
    }

    public int getHeapSize() {
        return heapSize;
    }

    public int getMax() throws Exception {
        if (heapSize == 0) {
            throw new Exception("Heap is Empty");
        }
        return heapArray[0][0];
    }

    public int popMax() throws Exception {
        if (heapSize == 0) {
            throw new Exception("Heap is Empty");
        }
        int max = heapArray[0][0];
        heapArray[0][0] = getLastElement();
        heapArray[currLevel][currPos - 1] = 0;
        currPos--;
        heapSize--;
        if (currPos == 0) {
            currPos = getParentLevelSize(currLevel);
            heapArray[currLevel] = null;
            currLevel--;
        }
        heapify(0, 0);
        return max;
    }

    private void heapify(int level, int pos) throws Exception {

        if (level == currLevel || !hasLeftChild(level, pos)) {
            return;
        }
        if (hasRightChild(level, pos)) {

            int leftChild = getLeftChild(level, pos);
            int rightChild = getRightChild(level, pos);

            if (leftChild > heapArray[level][pos] || rightChild > heapArray[level][pos]) {
                if (leftChild > rightChild) {
                    swapElementWithParent(level + 1, pos * 2);
                    heapify(level + 1, pos * 2);
                } else {
                    swapElementWithParent(level + 1, pos * 2 + 1);
                    heapify(level + 1, pos * 2 + 1);
                }
            }
        } else if (hasLeftChild(level, pos) && getLeftChild(level, pos) > heapArray[level][pos]) {
            swapElementWithParent(level + 1, pos * 2);
            heapify(level + 1, pos * 2);
        }
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
            if (heapArray[elementLevel][elementPos] > heapArray[elementLevel - 1][elementPos / 2]) {
                swapElementWithParent(elementLevel, elementPos);
            }
            elementLevel--;
            elementPos /= 2;
        }
    }

    private int getParentLevelSize(int level) {
        return level == 0 ? 0 : heapArray[level - 1].length;
    }

    private int getLastElement() throws Exception {
        if (heapSize == 0) {
            throw new Exception("Heap is Empty");
        }
        return heapArray[currLevel][currPos - 1];
    }

    private boolean hasLeftChild(int level, int pos) {
        return level + 1 < currLevel || (level + 1 == currLevel && pos * 2 < currPos);
    }

    private boolean hasRightChild(int level, int pos) {
        return level + 1 < currLevel || (level + 1 == currLevel && (pos * 2) + 1 < currPos);
    }

    private int getLeftChild(int level, int pos) throws Exception {
        if (level == currLevel || (level + 1 == currLevel && pos * 2 >= currPos)) {
            throw new Exception("Left Child not exists");
        }
        return heapArray[level + 1][pos * 2];
    }

    private int getRightChild(int level, int pos) throws Exception {
        if (level == currLevel || (level + 1 == currLevel && (pos * 2) + 1 >= currPos)) {
            throw new Exception("Left Child not exists");
        }
        return heapArray[level + 1][pos * 2 + 1];
    }

    private void swapElementWithParent(int level, int pos) {
        int temp = heapArray[level][pos];
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
