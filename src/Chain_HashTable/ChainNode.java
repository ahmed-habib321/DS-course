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
 * A class representing a node in the chain for handling collisions in a hash table.
 */
public class ChainNode {
    public String key;
    public Object value;
    public ChainNode next;

    /**
     * Constructs a new ChainNode with the specified key and value.
     *
     * @param key   The key associated with the node.
     * @param value The value associated with the node.
     */
    public ChainNode(String key, Object value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
