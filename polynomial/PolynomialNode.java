/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polynomial;

/**
 *
 * @author Ahmed
 */
public class PolynomialNode {
    public double coefficient;
    public int power;
    public PolynomialNode next;

    public PolynomialNode(double coefficient, int power, PolynomialNode next) {
        this.coefficient = coefficient;
        this.power = power;
        this.next = next;
    }
}
