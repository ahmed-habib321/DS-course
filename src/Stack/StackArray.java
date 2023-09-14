package Stack;

import java.util.EmptyStackException;
import java.util.Arrays;

/**
 *
 * @author Ahmed
 */
/**
 * A simple stack implementation using an array.
 *
 * @param <T> The type of elements to be stored in the stack.
 */
public class StackArray<T> {

    private Object[] elements;
    private int topIndex;

    /**
     * Creates a new stack with the specified capacity.
     *
     * @param capacity The initial capacity of the stack.
     */
    public StackArray(int capacity) {
        elements = new Object[capacity];
        topIndex = 0;
    }

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param data The element to be pushed onto the stack.
     */
    public void push(T data) {
        if (topIndex == elements.length) {
            // If the stack is full, double its capacity
            resizeArray(2 * elements.length);
        }
        elements[topIndex++] = data;
    }

    /**
     * Pops and returns the top element from the stack.
     *
     * @return The top element in the stack.
     * @throws EmptyStackException If the stack is empty.
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        T poppedItem = (T) elements[--topIndex];
        elements[topIndex] = null; // Avoid memory leak
        return poppedItem;
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return The top element in the stack.
     * @throws EmptyStackException If the stack is empty.
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        T topItem = (T) elements[topIndex - 1];
        return topItem;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return topIndex == 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return The number of elements in the stack.
     */
    public int size() {
        return topIndex;
    }

    // Private method to resize the underlying array when needed
    private void resizeArray(int newCapacity) {
        elements = Arrays.copyOf(elements, newCapacity);
    }

    /**
     * Returns a string representation of the stack, with elements listed top to
     * bottom.
     *
     * @return A string representation of the stack.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = topIndex - 1; i >= 0; i--) {
            output.append("↑\n").append(elements[i]).append("\n");
        }
        return output.toString();
    }
}
