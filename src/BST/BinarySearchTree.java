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
 * A class representing a Binary Search Tree (BST).
 */
public class BinarySearchTree {

    private TreeNode root;

    /**
     * Inserts a new value into the BST.
     *
     * @param value The value to be inserted.
     */
    public void insert(int value) {
        TreeNode newNode = new TreeNode(value);
        if (root == null) {
            root = newNode;
        } else {
            TreeNode current = root;
            while (true) {
                if (current.hasSameValue(value)) {
                    return;
                }
                if (current.getValue() < value) {
                    if (current.getRight() == null) {
                        current.setRight(newNode);
                        break;
                    } else {
                        current = current.getRight();
                    }
                } else {
                    if (current.getLeft() == null) {
                        current.setLeft(newNode);
                        break;
                    } else {
                        current = current.getLeft();
                    }
                }
            }
        }
    }

    /**
     * Performs an in-order traversal of the BST and prints the elements. This
     * method will print the elements in ascending order.
     */
    public void traverseInOrder() {
        if (root != null) {
            recursiveInOrderTraversal(root);
        } else {
            System.out.println("Empty tree");
        }
    }

    private void recursiveInOrderTraversal(TreeNode node) {
        if (node.getLeft() != null) {
            recursiveInOrderTraversal(node.getLeft());
        }
        System.out.print(node.getValue() + " , ");
        if (node.getRight() != null) {
            recursiveInOrderTraversal(node.getRight());
        }
    }

    /**
     * Performs a pre-order traversal of the BST and prints the elements.
     */
    public void traversePreOrder() {
        if (root != null) {
            recursivePreOrderTraversal(root);
        } else {
            System.out.println("Empty tree");
        }
    }

    private void recursivePreOrderTraversal(TreeNode node) {
        System.out.print(node.getValue() + " , ");
        if (node.getLeft() != null) {
            recursivePreOrderTraversal(node.getLeft());
        }
        if (node.getRight() != null) {
            recursivePreOrderTraversal(node.getRight());
        }
    }

    /**
     * Performs a post-order traversal of the BST and prints the elements.
     */
    public void traversePostOrder() {
        if (root != null) {
            recursivePostOrderTraversal(root);
        } else {
            System.out.println("Empty tree");
        }
    }

    private void recursivePostOrderTraversal(TreeNode node) {
        if (node.getLeft() != null) {
            recursivePostOrderTraversal(node.getLeft());
        }
        if (node.getRight() != null) {
            recursivePostOrderTraversal(node.getRight());
        }
        System.out.print(node.getValue() + " , ");
    }

    /**
     * Retrieves the node with the given value from the BST.
     *
     * @param value The value to search for.
     * @return The node with the specified value, or null if not found.
     */
    public TreeNode get(int value) {
        if (root == null) {
            return null;
        } else {
            TreeNode current = root;
            while (true) {
                if (current.getValue() == value) {
                    return current;
                } else if (value > current.getValue()) {
                    if (current.getRight() == null) {
                        return null;
                    } else {
                        current = current.getRight();
                    }
                } else if (value < current.getValue()) {
                    if (current.getLeft() == null) {
                        return null;
                    } else {
                        current = current.getLeft();
                    }
                }
            }
        }
    }

    /**
     * Retrieves the maximum value in the BST.
     *
     * @return The maximum value in the tree, or 0 if the tree is empty.
     */
    public int getMax() {
        if (root != null) {
            return findMax(root);
        }
        return 0;
    }

    private int findMax(TreeNode node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getValue();
    }

    /**
     * Retrieves the minimum value in the BST.
     *
     * @return The minimum value in the tree, or 0 if the tree is empty.
     */
    public int getMin() {
        if (root != null) {
            return findMin(root);
        }
        return 0;
    }

    private int findMin(TreeNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getValue();
    }

    /**
     * Calculates the depth of a node with the specified value in the BST.
     *
     * @param value The value of the node whose depth is to be calculated.
     * @return The depth of the node with the specified value, or -1 if the
     * value is not found in the tree.
     */
    public int depth(int value) {
        if (root != null) {
            return calculateDepth(root, value, 0);
        }
        return -1;
    }

    private int calculateDepth(TreeNode node, int value, int depth) {
        if (node == null) {
            return -1;
        }
        if (node.getValue() == value) {
            return depth;
        }
        int leftDepth = calculateDepth(node.getLeft(), value, depth + 1);
        int rightDepth = calculateDepth(node.getRight(), value, depth + 1);
        return Math.max(leftDepth, rightDepth);
    }

    /**
     * Deletes a node with the specified value from the BST.
     *
     * @param value The value of the node to be deleted.
     */
    public void delete(int value) {
        root = deleteNode(root, value);
    }

    private TreeNode deleteNode(TreeNode node, int value) {
        if (node == null) {
            return null;
        }
        if (value < node.getValue()) {
            node.setLeft(deleteNode(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(deleteNode(node.getRight(), value));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                node.setValue(findMin(node.getRight()));
                node.setRight(deleteNode(node.getRight(), node.getValue()));
            }
        }
        return node;
    }

    /**
     * Calculates the height of the BST.
     *
     * @return The height of the tree, or -1 if the tree is empty.
     */
    public int height() {
        return calculateHeight(root);
    }

    private int calculateHeight(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = calculateHeight(node.getLeft());
        int rightHeight = calculateHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Counts the number of leaf nodes in the BST.
     *
     * @return The number of leaf nodes in the tree.
     */
    public int leaves() {
        return countLeaves(root);
    }

    private int countLeaves(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }
        return countLeaves(node.getLeft()) + countLeaves(node.getRight());
    }

    /**
     * Checks if the BST is a complete binary tree.
     *
     * @return true if the tree is complete, false otherwise.
     */
    public boolean isComplete() {
        return isComplete(root, 0, size(root));
    }

    private boolean isComplete(TreeNode node, int index, int totalNodes) {
        if (node == null) {
            return true;
        }
        if (index >= totalNodes) {
            return false;
        }
        return (isComplete(node.getLeft(), (2 * index) + 1, totalNodes)
                && isComplete(node.getRight(), (2 * index) + 2, totalNodes));
    }

    private int size(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.getLeft()) + size(node.getRight());
    }

    /**
     * Checks if the BST is a full binary tree.
     *
     * @return true if the tree is full, false otherwise.
     */
    public boolean isFull() {
        return isFull(root);
    }

    private boolean isFull(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return true;
        }
        if (node.getLeft() != null && node.getRight() != null) {
            return isFull(node.getLeft()) && isFull(node.getRight());
        }
        return false;
    }

    /**
     * Retrieves the path to a node with the specified value in the BST.
     *
     * @param value The value of the node to search for.
     * @return A string representing the path to the node, or "not found" if the
     * value is not present in the tree.
     */
    public String getpath(int value) {
        String path = "";
        if (root != null) {
            TreeNode pointer = root;
            while (true) {
                if (pointer.getValue() == value) {
                    path += " found";
                    break;
                } else if (value > pointer.getValue()) {
                    if (pointer.getRight() == null) {
                        path = "not found";
                        break;
                    } else {
                        path += " Right ";
                        pointer = pointer.getRight();
                    }
                } else if (value < pointer.getValue()) {
                    if (pointer.getLeft() == null) {
                        path = "not found";
                        break;
                    } else {
                        path += " Left ";
                        pointer = pointer.getLeft();
                    }
                }
                path += ",";
            }
        }
        return path;
    }

}
