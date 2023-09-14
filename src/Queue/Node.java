/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Queue;

/**
 *
 * @author Ahmed
 */


/**
 * Represents a node in a linked list.
 */
class Node<T> {
    /** The data stored in the node. */
    public T data;
    /** Reference to the next node in the linked list. */
    public Node<T> next;

    /**
     * Initializes a new node with the given data.
     *
     * @param data The data to be stored in the node.
     */
    public Node(T data) {
        this.data = data;
    }
}
