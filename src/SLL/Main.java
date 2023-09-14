/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package SLL;

/**
 *
 * @author Ahmed
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SingleLinkedList sll = new SingleLinkedList();
        sll.addNode("hgj");
        sll.addNode(90);
        sll.addNode(900);
        sll.addNode(9000);
        
        System.out.println(sll.display());
        
        sll.insertNode(50, 1);
        System.out.println(sll.display());
        
        sll.deleteNode(4);
        System.out.println(sll.display());
        
        System.out.println(sll.size());
        System.out.println(sll.isExist("hg"));
        System.out.println(sll.findMiddle());
        
        
    }
    
}
