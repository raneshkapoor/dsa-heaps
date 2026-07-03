package com.dsa.practice.minheap;

import com.dsa.practice.Heap;

public class MinimumHeap extends Heap {

    public MinimumHeap() {
        super();
    }

    public int getMin() throws Exception {
        return get();
    }

    public int popMin() throws Exception {
        return pop();
    }

    @Override
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

    @Override
    protected void heapify(int level, int pos) throws Exception {

        if (level == currLevel || !hasLeftChild(level, pos)) {
            return;
        }
        if (hasRightChild(level, pos)) {

            int leftChild = getLeftChild(level, pos);
            int rightChild = getRightChild(level, pos);

            if (leftChild < heapArray[level][pos] || rightChild < heapArray[level][pos]) {
                if (leftChild < rightChild) {
                    swapElementWithParent(level + 1, pos * 2);
                    heapify(level + 1, pos * 2);
                } else {
                    swapElementWithParent(level + 1, pos * 2 + 1);
                    heapify(level + 1, pos * 2 + 1);
                }
            }
        } else if (hasLeftChild(level, pos) && getLeftChild(level, pos) < heapArray[level][pos]) {
            swapElementWithParent(level + 1, pos * 2);
            heapify(level + 1, pos * 2);
        }
    }

}
