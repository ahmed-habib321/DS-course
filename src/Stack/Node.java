/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Stack;

/**
 *
 * @author Ahmed
 */
public class Node {
    private Object value;
    private Node previous;

    /**
     * Initializes a new Node with the given data.
     * @param data The data to be stored in the node.
     */
    public Node(Object data) {
        this.value = data;
    }
    
    /**
     * Gets the data stored in the node.
     * @return The data stored in the node.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Gets the previous node in the stack.
     * @return The previous node in the stack.
     */
    public Node getPrevious() {
        return previous;
    }

    /**
     * Sets the previous node in the stack.
     * @param previous The previous node to be set.
     */
    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}