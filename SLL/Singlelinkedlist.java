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
public class Singlelinkedlist {

    private node Head = null;

    public void AddNode(Object data) {
        node node = new node(data);
        node pointer;
        if (Head == null) {
            Head = node;
        } else {
            pointer = Head;
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = node;
        }
    }

    public void InsertNode(Object data, int Location) {
        node node = new node(data);
        node pointer = Head;
        if (Head == null) {
            Head = node;
        } else {
            if (Location == 0) {
                node.next = Head;
                Head = node;
            } else {

                for (int i = 0; (i < Location - 1) && (pointer.next != null); i++) {
                    pointer = pointer.next;
                }
                if (pointer.next == null) {
                    pointer.next = node;
                } else {
                    node.next = pointer.next;
                    pointer.next = node;
                }
            }
        }
    }

    public boolean IsExist(Object data) {
        node node = Head;
        while (node != null) {
            if (node.data == data) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void DeleteNode(int Location) {
        Location--;
        Location--;
        node pointer1 = Head;
        node pointer2 = Head.next;
        if (Head != null) {
            if (Location == 0) {
                Head = Head.next;
            } else {

                for (int i = 0; i < Location && (pointer1.next != null); i++) {
                    pointer1 = pointer1.next;
                    pointer2 = pointer2.next;
                }
                if (pointer1.next != null) {
                    pointer1.next = pointer2.next;
                } else {
                    System.out.println("This Location is offlimit");
                }
            }
        }

    }

    public int size() {
        int counter = 0;
        node pointer = Head;
        while (pointer != null) {
            counter++;
            pointer = pointer.next;
        }
        return counter;
    }

    public Object FindMiddle() {
        node pointer = Head;
        int size = size();
        int mid;
        boolean isodd;
        if (size % 2 != 0) {
            mid = (size / 2) + 1;
            isodd = true;
        } else {
            mid = (size / 2);
            isodd = false;
        }
        for (int i = 1; i < mid; i++) {
            pointer = pointer.next;
        }
        if (isodd) {
            return pointer.data;
        }
        return pointer.data + " and " + pointer.next.data;
    }

    public String display() {
        node pointer = Head;
        String display = "";
        while (pointer != null) {
            display += pointer.data + " -> ";
            pointer = pointer.next;
        }
        return display;
    }

    public node getHead() {
        return Head;
    }
}
