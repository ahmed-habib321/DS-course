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
        Singlelinkedlist sll = new Singlelinkedlist();
        sll.AddNode("hgj");
        sll.AddNode(90);
        sll.AddNode(900);
        sll.AddNode(9000);
        
        System.out.println(sll.display());
        
        sll.InsertNode(50, 1);
        System.out.println(sll.display());
        
        sll.DeleteNode(4);
        System.out.println(sll.display());
        
        System.out.println(sll.size());
        System.out.println(sll.IsExist("hg"));
        System.out.println(sll.FindMiddle());
        
        
    }
    
}
