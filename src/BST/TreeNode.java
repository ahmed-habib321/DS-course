/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BST;

/**
 *
 * @author Ahmed
 */
/**
 * A class representing a node in a Binary Search Tree (BST).
 */
public class TreeNode {

    private int data;
    private TreeNode left;
    private TreeNode right;

    /**
     * Constructs a new TreeNode with the given value.
     *
     * @param value The value to be stored in the node.
     */
    public TreeNode(int value) {
        this.data = value;
        this.left = null;
        this.right = null;
    }

    /**
     * Checks if the node has the same value as the given value.
     *
     * @param value The value to compare with.
     * @return true if the values are equal, false otherwise.
     */
    public boolean hasSameValue(int value) {
        return this.data == value;
    }

    /**
     * Gets the value stored in the node.
     *
     * @return The value stored in the node.
     */
    public int getValue() {
        return data;
    }

    /**
     * Sets the value of the node.
     *
     * @param value The new value to set.
     */
    public void setValue(int value) {
        this.data = value;
    }

    /**
     * Gets the left child of the node.
     *
     * @return The left child node.
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * Sets the left child of the node.
     *
     * @param left The new left child node.
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * Gets the right child of the node.
     *
     * @return The right child node.
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * Sets the right child of the node.
     *
     * @param right The new right child node.
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }
}
