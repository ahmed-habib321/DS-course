/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Heap;

/**
 *
 * @author Ahmed
 */
/**
 * A simple max-heap data structure implementation.
 */
public class MaxHeap {

    private int[] heapArray; // The underlying array to store heap elements
    private int size = 0; // The current size of the heap

    /**
     * Creates a new MaxHeap with the specified capacity.
     *
     * @param capacity The maximum number of elements the heap can hold.
     */
    public MaxHeap(int capacity) {
        heapArray = new int[capacity];
    }

    /**
     * Checks if the heap is full.
     *
     * @return true if the heap is full, false otherwise.
     */
    public boolean isFull() {
        return size == heapArray.length;
    }

    /**
     * Calculates the index of the parent node for a given child index.
     *
     * @param childIndex The index of the child node.
     * @return The index of the parent node.
     */
    public int getParent(int childIndex) {
        return (childIndex - 1) / 2;
    }

    /**
     * Calculates the index of the left child node for a given parent index.
     *
     * @param parentIndex The index of the parent node.
     * @return The index of the left child node.
     */
    public int getLeftChild(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    /**
     * Calculates the index of the right child node for a given parent index.
     *
     * @param parentIndex The index of the parent node.
     * @return The index of the right child node.
     */
    public int getRightChild(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    /**
     * Inserts a value into the max-heap.
     *
     * @param value The value to insert.
     * @throws IndexOutOfBoundsException if the heap is full.
     */
    public void insert(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Heap is full");
        }
        heapArray[size] = value;
        fixHeapAbove(size);
        size++;
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retrieves the maximum element (root) of the heap.
     *
     * @return The maximum element.
     * @throws IndexOutOfBoundsException if the heap is empty.
     */
    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        return heapArray[0];
    }

    /**
     * Sorts the heap in ascending order using heap sort.
     */
    public void sort() {
        int lastHeapIndex = size - 1;
        for (int i = 0; i < lastHeapIndex; i++) {
            int tmp = heapArray[0];
            heapArray[0] = heapArray[lastHeapIndex - i];
            heapArray[lastHeapIndex - i] = tmp;
            fixHeapBelow(0, (lastHeapIndex - i - 1));
        }
    }

    /**
     * Fixes the heap property above the specified index.
     *
     * @param index The index to start fixing the heap from.
     */
    private void fixHeapAbove(int index) {
        int newValue = heapArray[index];
        while (index > 0 && newValue > heapArray[getParent(index)]) {
            heapArray[index] = heapArray[getParent(index)];
            index = getParent(index);
        }
        heapArray[index] = newValue;
    }

    /**
     * Fixes the heap property below the specified index.
     *
     * @param index The index to start fixing the heap from.
     * @param lastHeapIndex The index of the last element in the heap.
     */
    private void fixHeapBelow(int index, int lastHeapIndex) {
        int childToSwap;
        while (index <= lastHeapIndex) {
            int leftChild = getLeftChild(index);
            int rightChild = getRightChild(index);
            if (leftChild <= lastHeapIndex) {
                if (rightChild > lastHeapIndex) {
                    childToSwap = leftChild;
                } else {
                    childToSwap = (heapArray[leftChild] > heapArray[rightChild]) ? leftChild : rightChild;
                }

                if (heapArray[index] < heapArray[childToSwap]) {
                    int tmp = heapArray[index];
                    heapArray[index] = heapArray[childToSwap];
                    heapArray[childToSwap] = tmp;
                } else {
                    break;
                }
                index = childToSwap;
            } else {
                break;
            }
        }
    }
    
    /**
     * Deletes an element at a specific index from the heap.
     *
     * @param index The index of the element to delete.
     * @throws IndexOutOfBoundsException if the heap is empty.
     */
    public void delete(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }

        int parent = getParent(index);
        int deletedValue = heapArray[index];
        heapArray[index] = heapArray[size - 1];

        if (index == 0 || heapArray[index] < heapArray[parent]) {
            fixHeapBelow(index, size - 1);
        } else {
            fixHeapAbove(index);
        }
        size--;
    }
    
    /**
     * Prints the elements of the heap.
     */
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heapArray[i] + " , ");
        }
        System.out.println();
    }
}
