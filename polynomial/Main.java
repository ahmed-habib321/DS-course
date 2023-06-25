/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package polynomial;

/**
 *
 * @author Ahmed
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        p1.addTerm(4, 3);
        p1.addTerm(2, 1);
        p1.addTerm(1, 0);
        Polynomial p2 = new Polynomial();
        p2.addTerm(3, 4);
        p2.addTerm(4, 2);
        p2.addTerm(-5, 1);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.multiply(p2));
        System.out.println(p1.add(p2));
        System.out.println(p1.subtract(p2));
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p1.evaluate(5));
        

    }

}
