/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Queue;

import java.util.NoSuchElementException;

/**
 *
 * @author Ahmed
 */

/**
 * Represents a Queue implemented using a linked list.
 *
 * @param <T> The type of elements to be stored in the queue.
 */
public class QueueList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size;

    /**
     * Enqueues an element into the queue.
     *
     * @param data The element to be enqueued.
     */
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Dequeues an element from the queue.
     *
     * @return The element dequeued from the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        T dequeuedData = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return dequeuedData;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Retrieves the element at the front of the queue without removing it.
     *
     * @return The element at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return head.data;
    }

    /**
     * Returns the current size of the queue.
     *
     * @return The size of the queue.
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<T> temp = head;
        while (temp != null) {
            result.append(temp.data).append("<--");
            temp = temp.next;
        }
        return result.toString();
    }
}
