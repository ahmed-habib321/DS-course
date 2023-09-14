/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import BST.BinarySearchTree;


/**
 *
 * @author Ahmed
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(25);
        bst.insert(30);
        bst.insert(18);
        bst.insert(40);
        bst.insert(28);
        bst.insert(20);
        bst.insert(13);
        bst.insert(50);
        bst.insert(38);
        System.out.println(bst.height());
        System.out.println(bst.isComplete());
    }

}
