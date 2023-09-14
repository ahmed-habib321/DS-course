/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DLL;

/**
 *
 * @author Ahmed
 */
/**
 * Represents a node in a doubly linked list.
 */
public class Node {
    public Object data;
    public Node next;
    public Node prev;

    /**
     * Constructs a new Node with the given data.
     *
     * @param data The data to be stored in the node.
     */
    public Node(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
