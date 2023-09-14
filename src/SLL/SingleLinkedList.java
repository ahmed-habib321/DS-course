/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SLL;

/**
 *
 * @author Ahmed
 */
/**
 * Represents a singly linked list.
 */
public class SingleLinkedList {

    private Node head = null;

    /**
     * Adds a new node with the given data to the end of the linked list.
     *
     * @param data The data to be added.
     */
    public void addNode(Object data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node pointer = head;
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = newNode;
        }
    }

    /**
     * Inserts a new node with the given data at the specified location in the linked list.
     *
     * @param data     The data to be inserted.
     * @param location The location at which to insert the new node.
     */
    public void insertNode(Object data, int location) {
        Node newNode = new Node(data);
        Node pointer = head;
        if (head == null) {
            head = newNode;
        } else {
            if (location == 0) {
                newNode.next = head;
                head = newNode;
            } else {
                for (int i = 0; (i < location - 1) && (pointer.next != null); i++) {
                    pointer = pointer.next;
                }
                newNode.next = pointer.next;
                pointer.next = newNode;
            }
        }
    }

    /**
     * Checks if a node with the given data exists in the linked list.
     *
     * @param data The data to search for.
     * @return true if the data exists, false otherwise.
     */
    public boolean isExist(Object data) {
        Node pointer = head;
        while (pointer != null) {
            if (pointer.data.equals(data)) {
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    /**
     * Deletes the node at the specified location in the linked list.
     *
     * @param location The location of the node to be deleted.
     */
    public void deleteNode(int location) {
        if (location == 0 && head != null) {
            head = head.next;
        } else {
            Node pointer1 = head;
            Node pointer2 = head.next;
            for (int i = 0; i < location - 1 && pointer1 != null; i++) {
                pointer1 = pointer1.next;
                if (pointer2 != null) {
                    pointer2 = pointer2.next;
                }
            }
            if (pointer1 != null) {
                pointer1.next = (pointer2 != null) ? pointer2.next : null;
            } else {
                System.out.println("Invalid location.");
            }
        }
    }

    /**
     * Returns the number of nodes in the linked list.
     *
     * @return The size of the linked list.
     */
    public int size() {
        int counter = 0;
        Node pointer = head;
        while (pointer != null) {
            counter++;
            pointer = pointer.next;
        }
        return counter;
    }

    /**
     * Finds and returns the middle node(s) in the linked list.
     *
     * @return The middle node(s) or their data.
     */
    public Object findMiddle() {
        Node slowPointer = head;
        Node fastPointer = head;
        boolean isOdd = true;

        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            isOdd = !isOdd;
        }

        if (isOdd) {
            return slowPointer.data;
        } else {
            return slowPointer.data + " and " + slowPointer.next.data;
        }
    }

    /**
     * Displays the contents of the linked list.
     *
     * @return A string representation of the linked list.
     */
    public String display() {
        StringBuilder display = new StringBuilder();
        Node pointer = head;
        while (pointer != null) {
            display.append(pointer.data).append(" -> ");
            pointer = pointer.next;
        }
        return display.toString();
    }

    /**
     * Gets the head node of the linked list.
     *
     * @return The head node.
     */
    public Node getHead() {
        return head;
    }
}
