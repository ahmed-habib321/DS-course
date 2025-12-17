package Queue;

import java.util.NoSuchElementException;

/**
 * A simple implementation of a dynamic-sized queue using an array.
 */
public class DynamicArrayQueue<T> {

    private T[] elements;
    private int rear; // Index of the rear element.
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs a new queue with the specified initial capacity.
     *
     * @param capacity The initial capacity of the queue.
     */
    public DynamicArrayQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.elements = (T[]) new Object[capacity];
        this.rear = 0;
    }

     /**
     * Adds an element to the rear of the queue.
     *
     * @param data The data to enqueue.
     */
    public void enqueue(T data) {
        if (rear == elements.length) {
            resizeArray();
        }
        elements[rear++] = data;
    }

    /**
     * Removes and returns the front element of the queue.
     *
     * @return The front element of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        T frontElement = elements[0];
        System.arraycopy(elements, 1, elements, 0, rear - 1);
        rear--;
        return frontElement;
    }
    
    /**
     * Retrieves the front element of the queue without removing it.
     *
     * @return The front element of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return elements[0];
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return rear == 0;
    }

    /**
     * Gets the current size of the queue.
     *
     * @return The number of elements in the queue.
     */
    public int size() {
        return rear;
    }

    /**
     * Increases the capacity of the underlying array when needed.
     */
    private void resizeArray() {
        int newCapacity = elements.length + 10;
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, rear);
        elements = newArray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rear; i++) {
            sb.append(elements[i]);
            if (i < rear - 1) {
                sb.append(" <-- ");
            }
        }
        return sb.toString();
    }

}
