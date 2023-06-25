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
public class DoubleLinkedList {

    private node head = null;
    private node tail = null;

    

    public void addnode(Object data) {
        node node = new node(data);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    

    public void insertnode(Object data, int Location) {
        Location--;
        node n = new node(data);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            if (Location == 0) {
                n.next = head;
                head.prev = n;
                head = n;
            } else {
                node pointer = head;
                for (int i = 0; i < Location - 1 && pointer != null; i++) {
                    pointer = pointer.next;
                }
                if (pointer == null || pointer == tail) {
                    addnode(data);
                } else {
                    pointer.next.prev = n;
                    n.next = pointer.next;
                    pointer.next = n;
                    n.prev = pointer;
                }
            }
        }
    }

    public void deletenode(int Location) {
        Location--;
        if (head != null) {
            if (Location == 0) {
                head = head.next;
                head.prev = null;
            } else {
                node pointer = head;
                for (int i = 0; i < Location && pointer != null; i++) {
                    pointer = pointer.next;
                }
                if (pointer == tail) {

                    tail = tail.prev;
                    tail.next = null;

                } else if (pointer == null) {

                } else {
                    pointer.prev.next = pointer.next;
                    pointer.next.prev = pointer.prev;
                }
            }
        }
    }

    public boolean IsExist(Object data) {
        // iterative
        node pointer = head;
        while (pointer != null) {
            if (pointer.data == data) {
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    public boolean IsExist(node pointer, Object data) {
        // recursion
        if (pointer == null) {
            return false;
        } else if (pointer.data == data) {
            return true;
        } else {
            return IsExist(pointer.next, data);
        }
    }

    public void Reverse() {
        // iterative
        node FrontPointer, RearPointer;
        Object temp;
        FrontPointer = head;
        RearPointer = tail;
        while (FrontPointer != RearPointer && FrontPointer.next != RearPointer) {
            temp = FrontPointer.data;
            FrontPointer.data = RearPointer.data;
            RearPointer.data = temp;
            FrontPointer = FrontPointer.next;
            RearPointer = RearPointer.prev;
        }
    }

    public void Reverse(node FrontPointer, node RearPointer) {
        // recursion
        Object dataholder;
        if (!(FrontPointer == RearPointer || FrontPointer.next == RearPointer)) {
            dataholder = FrontPointer.data;
            FrontPointer.data = RearPointer.data;
            RearPointer.data = dataholder;
            Reverse(FrontPointer.next, RearPointer.prev);
        }
    }

    public int count() {
        int Counter = 0;
        node pointer = head;
        while (pointer != null) {
            Counter++;
            pointer = pointer.next;
        }
        return Counter;
    }

    public boolean IsInverse(DoubleLinkedList other) {
        // check if two lists are inverse of each other (iterative)
        node FrontPointer = head, RearPointer = other.tail;
        // FrontPointer point at the head of the first list 
        // RearPointer point at the tail of the second list 
        if (count() != other.count()) {
            return false;
        }
        while (FrontPointer != null && RearPointer != null) {
            if (FrontPointer.data != RearPointer.data) {
                return false;
            }
            FrontPointer = FrontPointer.next;
            RearPointer = RearPointer.prev;
        }
        return true;
    }

    public boolean IsInverse(node FrontPointer, node RearPointer) {
        // check if two lists are inverse of each other (recursive)
        // FrontPointer point at the head of the first list 
        // RearPointer point at the tail of the second list 
        if (FrontPointer == null && RearPointer == null) {
            return true;
        } else if (FrontPointer == null && RearPointer != null || FrontPointer != null && RearPointer == null) {
            return false;
        } else {
            return (FrontPointer.data == RearPointer.data) && (IsInverse(FrontPointer.next, RearPointer.prev));
        }

    }

    public boolean Ispalindrome() {
        // check for odd and even palindrome (iterative)
        node FrontPointer = head;
        node RearPointer = tail;
        while (FrontPointer != RearPointer && FrontPointer.next != RearPointer) {
            if (FrontPointer.data != RearPointer.data) {
                return false;
            }
            FrontPointer = FrontPointer.next;
            RearPointer = RearPointer.prev;
        }
        return true;
    }

    public boolean Ispalindrome(node FrontPointer, node RearPointer) {
        // check for odd and even palindrome (recursion)
        if (FrontPointer == RearPointer || FrontPointer.next == RearPointer) {
            return true;
        } else {
            return (FrontPointer.data == RearPointer.data) 
                    && (Ispalindrome(FrontPointer.next, RearPointer.prev));
        }

    }

    public String display() {
        node pointer = head;
        String display = "";
        while (pointer != null) {
            display += pointer.data + " <-> ";
            pointer = pointer.next;
        }
        return display;
    }

    public node getHead() {
        return head;
    }

    public node getTail() {
        return tail;
    }

    @Override
    public String toString() {
        node pointer = head;
        String display = "";
        while (pointer != null) {
            display += pointer.data;
            pointer = pointer.next;
        }
        return display;
    }
    
    
}
