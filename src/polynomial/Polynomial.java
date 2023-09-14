package polynomial;

/**
 *
 * @author Ahmed
 */

/**
 * Represents a polynomial as a linked list of PolynomialNodes.
 */
public class Polynomial {

    private PolynomialNode head;

    /**
     * Initializes an empty polynomial.
     */
    public Polynomial() {
        head = null;
    }

    /**
     * Adds a term to the polynomial with the given coefficient and power.
     * If the coefficient is zero, the term is not added.
     *
     * @param coefficient The coefficient of the term.
     * @param power       The power of the term.
     */
    
    public void addTerm(double coefficient, int power) {
        if (coefficient == 0) {
            return; // Don't add a term with zero coefficient
        }
        PolynomialNode newNode = new PolynomialNode(coefficient, power);
        if (head == null || power > head.power) {
            newNode.next = head;
            head = newNode;
        } else {
            PolynomialNode Pointer = head;
            while (Pointer.next != null && power < Pointer.next.power) {
                Pointer = Pointer.next;
            }
            if (power == Pointer.power) {
                Pointer.coefficient += coefficient;
                if (Pointer.coefficient == 0) {
                    delete(Pointer);
                }
            } else {
                newNode.next = Pointer.next;
                Pointer.next = newNode;
            }
        }
    }
    
    /**
     * Deletes the specified node from the polynomial.
     *
     * @param node The node to be deleted.
     */
    private void delete(PolynomialNode node) {
        if (node == null || head == null) {
            return;
        }
        if (head == node) {
            head = head.next;
            return;
        }
        PolynomialNode Pointer = head;
        while (Pointer.next != null && Pointer.next != node) {
            Pointer = Pointer.next;
        }
        if (Pointer.next == node) {
            Pointer.next = node.next;
        }
    }

    /**
     * Evaluates the polynomial for a given value of 'x'.
     *
     * @param x The value of 'x' for evaluation.
     * @return The result of the polynomial evaluation.
     */
    public double evaluate(double x) {
        double result = 0;
        PolynomialNode Pointer = head;
        while (Pointer != null) {
            result += Pointer.coefficient * Math.pow(x, Pointer.power);
            Pointer = Pointer.next;
        }
        return result;
    }
    
    /**
     * Sorts the terms of the polynomial in decreasing order of power.
     */
    private void sort() {
        if (head == null || head.next == null) {
            // List is empty or has only one element, no need to sort
            return;
        }
        PolynomialNode Pointer = head;
        while (Pointer != null) {
            PolynomialNode maxNode = Pointer;
            PolynomialNode runner = Pointer.next;
            while (runner != null) {
                if (runner.power > maxNode.power) {
                    maxNode = runner;
                }
                runner = runner.next;
            }
            if (maxNode != Pointer) {
                double tempCoefficient = Pointer.coefficient;
                int temppower = Pointer.power;
                Pointer.coefficient = maxNode.coefficient;
                Pointer.power = maxNode.power;
                maxNode.coefficient = tempCoefficient;
                maxNode.power = temppower;
            }
            Pointer = Pointer.next;
        }
    }

    /**
     * Adds the input polynomial to this polynomial and returns the result.
     *
     * @param other The polynomial to be added.
     * @return The result of the addition.
     */
    public Polynomial add(Polynomial other) {
        Polynomial result = new Polynomial();
        PolynomialNode Pointer1 = head;
        PolynomialNode Pointer2 = other.head;
        while (Pointer1 != null && Pointer2 != null) {
            if (Pointer1.power == Pointer2.power) {
                double sum = Pointer1.coefficient + Pointer2.coefficient;
                if (sum != 0) {
                    result.addTerm(sum, Pointer1.power);
                }
                Pointer1 = Pointer1.next;
                Pointer2 = Pointer2.next;
            } else if (Pointer1.power > Pointer2.power) {
                result.addTerm(Pointer1.coefficient, Pointer1.power);
                Pointer1 = Pointer1.next;
            } else {
                result.addTerm(Pointer2.coefficient, Pointer2.power);
                Pointer2 = Pointer2.next;
            }
        }
        while (Pointer1 != null) {
            result.addTerm(Pointer1.coefficient, Pointer1.power);
            Pointer1 = Pointer1.next;
        }
        while (Pointer2 != null) {
            result.addTerm(Pointer2.coefficient, Pointer2.power);
            Pointer2 = Pointer2.next;
        }
        return result;
    }
    
    /**
     * Subtracts the input polynomial from this polynomial and returns the result.
     *
     * @param other The polynomial to be subtracted.
     * @return The result of the subtraction.
     */
    public Polynomial subtract(Polynomial other) {
        // Subtracts the input polynomial from this polynomial and returns the result
        Polynomial result = new Polynomial();
        PolynomialNode Pointer1 = head;
        PolynomialNode Pointer2 = other.head;
        while (Pointer1 != null && Pointer2 != null) {
            if (Pointer1.power == Pointer2.power) {
                double difference = Pointer1.coefficient - Pointer2.coefficient;
                if (difference != 0) {
                    result.addTerm(difference, Pointer1.power);
                }
                Pointer1 = Pointer1.next;
                Pointer2 = Pointer2.next;
            } else if (Pointer1.power > Pointer2.power) {
                result.addTerm(Pointer1.coefficient, Pointer1.power);
                Pointer1 = Pointer1.next;
            } else {
                result.addTerm(-Pointer2.coefficient, Pointer2.power);
                Pointer2 = Pointer2.next;
            }
        }
        while (Pointer1 != null) {
            result.addTerm(Pointer1.coefficient, Pointer1.power);
            Pointer1 = Pointer1.next;
        }
        while (Pointer2 != null) {
            result.addTerm(-Pointer2.coefficient, Pointer2.power);
            Pointer2 = Pointer2.next;
        }
        return result;
    }
    
    /**
     * Multiplies this polynomial by another polynomial and returns the result.
     *
     * @param other The polynomial to be multiplied.
     * @return The result of the multiplication.
     */
    public Polynomial multiply(Polynomial other) {
        Polynomial result = new Polynomial();
        PolynomialNode Pointer1 = head;
        while (Pointer1 != null) {
            PolynomialNode Pointer2 = other.head;
            while (Pointer2 != null) {
                double product = Pointer1.coefficient * Pointer2.coefficient;
                int power = Pointer1.power + Pointer2.power;
                PolynomialNode node = result.findTerm(power);
                if (node != null) {
                    node.coefficient += product;
                    if (node.coefficient == 0) {
                        result.delete(node);
                    }
                } else {
                    result.addTerm(product, power);
                }
                Pointer2 = Pointer2.next;
            }
            Pointer1 = Pointer1.next;
        }
        return result;
    }

    /**
     * Finds the node with the given power in the polynomial.
     *
     * @param power The power to search for.
     * @return The node with the specified power or null if not found.
     */
    private PolynomialNode findTerm(int power) {
        //find the node that contains the x of certain degree
        PolynomialNode Pointer = head;
        while (Pointer != null && Pointer.power > power) {
            Pointer = Pointer.next;
        }
        if (Pointer != null && Pointer.power == power) {
            return Pointer;
        }
        return null;
    }
    
    /**
     * Returns a string representation of the polynomial.
     *
     * @return The string representation of the polynomial.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        PolynomialNode Pointer = head;
        boolean firstTerm = true;
        while (Pointer != null) {
            if (Pointer.coefficient != 0) {
                if (!firstTerm) {
                    sb.append(" + ");
                }
                sb.append(Pointer.coefficient);

                if (Pointer.power > 1) {
                    sb.append("x^").append(Pointer.power);
                } else if (Pointer.power == 1) {
                    sb.append("x");
                } else if (Pointer.power == 0) {

                }
                firstTerm = false;
            }
            Pointer = Pointer.next;
        }
        if (firstTerm) {
            sb.append("0");
        }
        return sb.toString();
    }
}
