/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DLL;

/**
 *
 * @author Ahmed
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DoubleLinkedList dll = new DoubleLinkedList();

        dll.addnode(1);
        dll.addnode(2);
        dll.addnode(3);
        dll.addnode("a");
        dll.addnode("b");
        /*
        System.out.println(dll.display());
        dll.deletenode(1);  // location not index (start from 1)
        System.out.println(dll.display());
        System.out.println(dll.IsExist("1"));
        dll.insertnode("new node", 2);
        System.out.println(dll.display());
        System.out.println(dll.count());
         
        dll.addnode(1);
        dll.addnode(2);
        dll.addnode(34);
        dll.addnode("a");
        dll.addnode("b");
        dll.addnode("b");
        dll.addnode("a");
        dll.addnode(34);
        dll.addnode(2);
        dll.addnode(1);
        System.out.println(dll.Ispalindrome());
        System.out.println(dll.Ispalindrome(dll.getHead(), dll.getTail()));
        System.out.println(dll.IsExist(dll.getHead(), 34));

        System.out.println(dll.IsInverse(dll.getHead(), dll.getTail()));
        System.out.println(dll.IsInverse(dll));
         */
        dll.Reverse(dll.getHead(), dll.getTail());
        System.out.println(dll.display());
        dll.Reverse();
        System.out.println(dll.display());
    }

}
