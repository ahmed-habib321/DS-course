/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Stack;

/**
 *
 * @author Ahmed
 */
public class StackList {

    private Node top = null;

    /**
     * Pushes a new element onto the stack.
     * @param data The data to be pushed onto the stack.
     */
    public void push(Object data) {
        Node newNode = new Node(data);
        newNode.setPrevious(top);
        top = newNode;
    }

    /**
     * Pops the top element from the stack.
     */
    public void pop() {
        if (top != null) {
            top = top.getPrevious();
        }
    }

     /**
     * Retrieves the top element of the stack without removing it.
     * @return The top element of the stack.
     */
    public Object peek() {
        if (top != null) {
            return top.getValue();
        } else {
            throw new IllegalStateException("Stack is empty.");
        }
    }
}
