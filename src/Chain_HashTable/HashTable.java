/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chain_HashTable;

/**
 *
 * @author Ahmed
 */
/**
 * A hash table implementation using chaining to handle collisions.
 */
public class HashTable {

    private ChainNode[] table;

    /**
     * Constructs a new hash table with a specified initial size.
     *
     * @param initialSize The initial size of the hash table.
     */
    public HashTable(int initialSize) {
        table = new ChainNode[initialSize];
    }

    /**
     * Computes the hash code for a given key.
     *
     * @param key The key for which to compute the hash code.
     * @return The computed hash code.
     */
    private int hashKey(String key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    /**
     * Adds a key-value pair to the hash table.
     *
     * @param key The key to add.
     * @param value The value associated with the key.
     */
    public void put(String key, Object value) {
        int hashedKey = hashKey(key);
        ChainNode newNode = new ChainNode(key, value);
        ChainNode pointer = table[hashedKey];

        if (pointer == null) {
            table[hashedKey] = newNode;
        } else {
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = newNode;
        }
    }

    /**
     * Retrieves the value associated with a given key from the hash table.
     *
     * @param key The key to retrieve.
     * @return The value associated with the key, or null if the key is not
     * found.
     */
    public Object get(String key) {
        int hashedKey = hashKey(key);
        ChainNode pointer = table[hashedKey];

        while (pointer != null && !pointer.key.equals(key)) {
            pointer = pointer.next;
        }

        return (pointer != null) ? pointer.value : null;
    }

    /**
     * Removes a key-value pair from the hash table.
     *
     * @param key The key to remove.
     * @return The value associated with the removed key, or null if the key is
     * not found.
     */
    public Object remove(String key) {
        int hashedKey = hashKey(key);
        ChainNode pointer = table[hashedKey];
        ChainNode prev = null;
        Object val = null;

        while (pointer != null && !pointer.key.equals(key)) {
            prev = pointer;
            pointer = pointer.next;
        }

        if (pointer != null) {
            val = pointer.value;

            if (prev == null) {
                table[hashedKey] = pointer.next;
            } else {
                prev.next = pointer.next;
            }
        }

        return val;
    }

    /**
     * Prints the contents of the hash table.
     */
    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                System.out.println("Empty");
            } else {
                ChainNode pointer = table[i];
                StringBuilder display = new StringBuilder();
                while (pointer != null) {
                    display.append("[Key: ").append(pointer.key).append(", Value: ").append(pointer.value).append("] -> ");
                    pointer = pointer.next;
                }
                System.out.println(display);
            }
        }
    }
}
