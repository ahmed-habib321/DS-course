/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Probing_HashTable;

/**
 *
 * @author Ahmed
 */
/**
 * Node represents a key-value pair.
 */
class Node {

    public String key;
    public Object value;

    public Node(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "key = " + key + ", value = " + value;
    }
}
