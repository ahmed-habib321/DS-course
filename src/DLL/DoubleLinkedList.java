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
 * Represents a Doubly Linked List.
 */
public class DoubleLinkedList {

    private Node head = null;
    private Node tail = null;

    /**
     * Adds a new node with the given data to the end of the list.
     *
     * @param data The data to be added to the list.
     */
    public void addNode(Object data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    /**
     * Inserts a new node with the given data at the specified location in the
     * list.
     *
     * @param data The data to be inserted.
     * @param location The position at which to insert the data.
     */
    public void insertNode(Object data, int location) {
        location--;
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            if (location == 0) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else {
                Node pointer = head;
                for (int i = 0; i < location - 1 && pointer != null; i++) {
                    pointer = pointer.next;
                }
                if (pointer == null || pointer == tail) {
                    addNode(data);
                } else {
                    pointer.next.prev = newNode;
                    newNode.next = pointer.next;
                    pointer.next = newNode;
                    newNode.prev = pointer;
                }
            }
        }
    }

    /**
     * Deletes a node at the specified location in the list.
     *
     * @param location The position of the node to be deleted.
     */
    public void deleteNode(int location) {
        location--;
        if (head != null) {
            if (location == 0) {
                head = head.next;
                if (head != null) {
                    head.prev = null;
                }
            } else {
                Node pointer = head;
                for (int i = 0; i < location && pointer != null; i++) {
                    pointer = pointer.next;
                }
                if (pointer == tail) {
                    tail = tail.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                } else if (pointer != null) {
                    pointer.prev.next = pointer.next;
                    if (pointer.next != null) {
                        pointer.next.prev = pointer.prev;
                    }
                }
            }
        }
    }

    /**
     * Checks if a given data element exists in the list (iterative).
     *
     * @param data The data element to search for.
     * @return True if the data exists in the list, false otherwise.
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
     * Checks if a given data element exists in the list (recursive).
     *
     * @param pointer The current node being checked.
     * @param data The data element to search for.
     * @return True if the data exists in the list, false otherwise.
     */
    public boolean isExistRecursive(Node pointer, Object data) {
        if (pointer == null) {
            return false;
        } else if (pointer.data.equals(data)) {
            return true;
        } else {
            return isExistRecursive(pointer.next, data);
        }
    }

    /**
     * Reverses the list in place (iterative).
     */
    public void reverse() {
        Node frontPointer = head;
        Node rearPointer = tail;
        Object temp;
        while (frontPointer != rearPointer && frontPointer.next != rearPointer) {
            temp = frontPointer.data;
            frontPointer.data = rearPointer.data;
            rearPointer.data = temp;
            frontPointer = frontPointer.next;
            rearPointer = rearPointer.prev;
        }
    }

    /**
     * Reverses the list in place (recursive).
     *
     * @param frontPointer The front pointer.
     * @param rearPointer The rear pointer.
     */
    public void reverseRecursive(Node frontPointer, Node rearPointer) {
        Object dataHolder;
        if (!(frontPointer == rearPointer || frontPointer.next == rearPointer)) {
            dataHolder = frontPointer.data;
            frontPointer.data = rearPointer.data;
            rearPointer.data = dataHolder;
            reverseRecursive(frontPointer.next, rearPointer.prev);
        }
    }

    /**
     * Counts the number of nodes in the list.
     *
     * @return The number of nodes in the list.
     */
    public int count() {
        int counter = 0;
        Node pointer = head;
        while (pointer != null) {
            counter++;
            pointer = pointer.next;
        }
        return counter;
    }

    /**
     * Checks if the current list is the inverse of another list (iterative).
     *
     * @param other The other list to compare with.
     * @return True if the lists are inverses of each other, false otherwise.
     */
    public boolean isInverse(DoubleLinkedList other) {
        Node frontPointer = head;
        Node rearPointer = other.tail;
        if (count() != other.count()) {
            return false;
        }
        while (frontPointer != null && rearPointer != null) {
            if (!frontPointer.data.equals(rearPointer.data)) {
                return false;
            }
            frontPointer = frontPointer.next;
            rearPointer = rearPointer.prev;
        }
        return true;
    }

    /**
     * Checks if the current list is the inverse of another list (recursive).
     *
     * @param frontPointer The front pointer of the current list.
     * @param rearPointer The rear pointer of the other list.
     * @return True if the lists are inverses of each other, false otherwise.
     */
    public boolean isInverseRecursive(Node frontPointer, Node rearPointer) {
        if (frontPointer == null && rearPointer == null) {
            return true;
        } else if ((frontPointer == null && rearPointer != null) || (frontPointer != null && rearPointer == null)) {
            return false;
        } else {
            return frontPointer.data.equals(rearPointer.data) && isInverseRecursive(frontPointer.next, rearPointer.prev);
        }
    }

    /**
     * Checks if the list is a palindrome (iterative).
     *
     * @return True if the list is a palindrome, false otherwise.
     */
    public boolean isPalindrome() {
        Node frontPointer = head;
        Node rearPointer = tail;
        while (frontPointer != rearPointer && frontPointer.next != rearPointer) {
            if (!frontPointer.data.equals(rearPointer.data)) {
                return false;
            }
            frontPointer = frontPointer.next;
            rearPointer = rearPointer.prev;
        }
        return true;
    }

    /**
     * Checks if the list is a palindrome (recursive).
     *
     * @param frontPointer The front pointer.
     * @param rearPointer The rear pointer.
     * @return True if the list is a palindrome, false otherwise.
     */
    public boolean isPalindromeRecursive(Node frontPointer, Node rearPointer) {
        if (frontPointer == rearPointer || frontPointer.next == rearPointer) {
            return true;
        } else {
            return frontPointer.data.equals(rearPointer.data) && isPalindromeRecursive(frontPointer.next, rearPointer.prev);
        }
    }

    /**
     * Returns the head node of the list.
     *
     * @return The head node.
     */
    public Node getHead() {
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * @return The tail node.
     */
    public Node getTail() {
        return tail;
    }

    @Override
    public String toString() {
        Node pointer = head;
        StringBuilder display = new StringBuilder();
        while (pointer != null) {
            display.append(pointer.data);
            pointer = pointer.next;
        }
        return display.toString();
    }
    
    /**
     * Returns a string representation of the list.
     *
     * @return A string representation of the list.
     */
    public String display() {
        Node pointer = head;
        StringBuilder display = new StringBuilder();
        while (pointer != null) {
            display.append(pointer.data).append(" <-> ");
            pointer = pointer.next;
        }
        return display.toString();
    }
}
