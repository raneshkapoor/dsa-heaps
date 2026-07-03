package com.dsa.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Heap {

    protected final int[][] heapArray;
    protected int heapSize;

    protected int currLevel;
    protected int currPos;

    public Heap() {
        heapArray = new int[20][];
        heapSize = 0;
        currLevel = 0;
        currPos = 0;
        heapArray[currLevel] = new int[1];
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void insert(List<Integer> values) {
        values.forEach(this::insert);
    }

    public abstract void insert(int value);

    protected int get() throws Exception {
        if (heapSize == 0) {
            throw new Exception("Heap is Empty");
        }
        return heapArray[0][0];
    }

    protected int pop() throws Exception {
        if (heapSize == 0) {
            throw new Exception("Heap is Empty");
        }
        int min = heapArray[0][0];
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
        return min;
    }

    protected abstract void heapify(int level, int pos) throws Exception;

    protected boolean hasLeftChild(int level, int pos) {
        return level + 1 < currLevel || (level + 1 == currLevel && pos * 2 < currPos);
    }

    protected boolean hasRightChild(int level, int pos) {
        return level + 1 < currLevel || (level + 1 == currLevel && (pos * 2) + 1 < currPos);
    }

    protected int getLeftChild(int level, int pos) throws Exception {
        if (level == currLevel || (level + 1 == currLevel && pos * 2 >= currPos)) {
            throw new Exception("Left Child not exists");
        }
        return heapArray[level + 1][pos * 2];
    }

    protected int getRightChild(int level, int pos) throws Exception {
        if (level == currLevel || (level + 1 == currLevel && (pos * 2) + 1 >= currPos)) {
            throw new Exception("Left Child not exists");
        }
        return heapArray[level + 1][pos * 2 + 1];
    }

    protected void swapElementWithParent(int level, int pos) {
        int temp = heapArray[level][pos];
        heapArray[level][pos] = heapArray[level - 1][pos / 2];
        heapArray[level - 1][pos / 2] = temp;
    }

    protected int getParentLevelSize(int level) {
        return level == 0 ? 0 : heapArray[level - 1].length;
    }

    protected int getLastElement() throws Exception {
        if (heapSize == 0) {
            throw new Exception("Heap is Empty");
        }
        return heapArray[currLevel][currPos - 1];
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
