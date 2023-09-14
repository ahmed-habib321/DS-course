package polynomial;

/**
 *
 * @author Ahmed
 */
/**
 * Represents a node in a polynomial linked list.
 */
public class PolynomialNode {

    public double coefficient;
    public int power;
    public PolynomialNode next;

    /**
     * Constructs a new PolynomialNode with the given coefficient and power.
     *
     * @param coefficient The coefficient of the term.
     * @param power The power of the term.
     */
    public PolynomialNode(double coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
        this.next = null;
    }
}
