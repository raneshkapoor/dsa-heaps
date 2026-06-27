package com.dsa.practice.minheap;

public class MinimumHeap {

    private final int[][] heapArray;
    private int heapSize;

    private int currLevel;
    private int currPos;

    public MinimumHeap() {
        heapArray = new int[100][];
        heapSize = 0;
        currLevel = 0;
        currPos = 0;
        heapArray[currLevel] = new int[1];
    }

    public void insert(int value) {

        //  Check if current level is full, create next level with double size
        if ((currLevel == 0 && currPos == 1) || (currPos == 2 * getParentLevelSize(currLevel))) {
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

}
