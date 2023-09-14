/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Probing_HashTable;

import java.util.Objects;

/**
 *
 * @author Ahmed
 */
/**
 * A basic hash table implementation using open addressing and linear probing to
 * handle collisions.
 */
public class HashTable {

    private Node[] table;

    /**
     * Creates a new HashTable with an initial size of 10.
     */
    public HashTable() {
        table = new Node[10];
    }

    /**
     * Checks if a slot in the table is occupied.
     *
     * @param index The index to check.
     * @return true if the slot is occupied, false otherwise.
     */
    private boolean isOccupied(int index) {
        return table[index] != null;
    }

    /**
     * Computes the hash key for a given key.
     *
     * @param key The key to hash.
     * @return The computed hash key.
     */
    private int hashKey(String key) {
        return Math.abs(Objects.requireNonNull(key).hashCode()) % table.length;
    }

    /**
     * Adds a key-value pair to the hash table.
     *
     * @param key The key to add.
     * @param value The corresponding value.
     */
    public void put(String key, Object value) {
        int hashedKey = hashKey(key);

        if (isOccupied(hashedKey)) {
            int stopIndex = hashedKey;

            // Linear probing
            do {
                hashedKey = (hashedKey + 1) % table.length;
            } while (isOccupied(hashedKey) && hashedKey != stopIndex);
        }

        if (isOccupied(hashedKey)) {
            System.out.println("Unable to add at index " + hashedKey + ", position already occupied.");
        } else {
            Node newNode = new Node(key, value);
            table[hashedKey] = newNode;
        }
    }

    /**
     * Retrieves the value associated with a key.
     *
     * @param key The key to search for.
     * @return The value associated with the key, or null if the key is not
     * found.
     */
    public Object get(String key) {
        int hashedKey = findKey(key);

        return hashedKey == -1 ? null : table[hashedKey].value;
    }

    /**
     * Removes a key-value pair from the hash table.
     *
     * @param key The key to remove.
     * @return The value associated with the removed key, or null if the key is
     * not found.
     */
    public Object remove(String key) {
        int hashedKey = findKey(key);

        if (hashedKey == -1) {
            return null;
        } else {
            Node removedNode = table[hashedKey];
            table[hashedKey] = null;

            // Rehashing
            Node[] oldTable = table;
            table = new Node[oldTable.length];

            for (Node node : oldTable) {
                if (node != null) {
                    put(node.key, node.value);
                }
            }

            return removedNode.value;
        }
    }

    /**
     * Finds the index of a key in the hash table.
     *
     * @param key The key to search for.
     * @return The index of the key if found, or -1 if not found.
     */
    private int findKey(String key) {
        int hashedKey = hashKey(key);
        boolean sameKeyExists = isOccupied(hashedKey) && table[hashedKey].key.equals(key);

        if (sameKeyExists) {
            return hashedKey;
        }

        // Linear probing
        int stopIndex = hashedKey;

        do {
            hashedKey = (hashedKey + 1) % table.length;
        } while (isOccupied(hashedKey) && hashedKey != stopIndex && !table[hashedKey].key.equals(key));

        return isOccupied(hashedKey) && table[hashedKey].key.equals(key) ? hashedKey : -1;
    }

    /**
     * Prints the contents of the hash table.
     */
    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                System.out.println("Empty");
            } else {
                System.out.println(table[i]);
            }
        }
    }

}
