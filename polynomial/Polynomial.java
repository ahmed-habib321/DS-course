/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polynomial;

/**
 *
 * @author Ahmed
 */
public class Polynomial {

    private PolynomialNode head;

    public Polynomial() {
        head = null;
    }

    public void addTerm(double coefficient, int power) {
        if (coefficient == 0) {
            return; // Don't add a term with zero coefficient
        }
        PolynomialNode newNode = new PolynomialNode(coefficient, power, null);
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

    public double evaluate(double x) {
        double result = 0;
        PolynomialNode Pointer = head;
        while (Pointer != null) {
            result += Pointer.coefficient * Math.pow(x, Pointer.power);
            Pointer = Pointer.next;
        }
        return result;
    }

    private void sort() {
        // Sorts the terms of the polynomial in decreasing order of power
        if (head == null || head.next == null) {
            return; // List is empty or has only one element, no need to sort
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

    public Polynomial add(Polynomial other) {
        // Adds the input polynomial to this polynomial and returns the result
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
            } else { // Pointer1.power < Pointer2.power
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
                }else if (Pointer.power == 1){
                    sb.append("x");
                }else if(Pointer.power == 0){
                    
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
