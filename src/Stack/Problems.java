package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problems {

    //problem 1
    public static class Balanced_Parentheses {
        public boolean Evaluate(String Expression) {
            StackList stack = new StackList();
            for (char ch : Expression.toCharArray()) {
                if (ch == '(' || ch == '{' || ch == '[') {
                    stack.push(ch);
                } else if (ch == ')') {
                    if (stack.peek().equals('(')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (ch == '}') {
                    if (stack.peek().equals('{')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (ch == ']') {
                    if (stack.peek().equals('[')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }
    }


    //problem 2
    /**
     * Utility class used to evaluate simple arithmetic expressions written
     * in infix notation (e.g. "3+(2*4)").
     *
     * <p>
     * Supported operators:
     * <ul>
     *   <li>Addition (+)</li>
     *   <li>Subtraction (-)</li>
     *   <li>Multiplication (*)</li>
     *   <li>Division (/)</li>
     * </ul>
     *
     * <p>
     * The evaluation uses two stacks:
     * <ul>
     *   <li>A stack of integer values</li>
     *   <li>A stack of operators</li>
     * </ul>
     *
     * <p>
     * Limitations:
     * <ul>
     *   <li>Only single-digit integers are supported</li>
     *   <li>No support for negative numbers</li>
     *   <li>No floating-point calculations</li>
     * </ul>
     */
    public static class Equation_Evaluation {
        /**
         * Returns the precedence of an arithmetic operator.
         *
         * <p>
         * Higher value means higher precedence.
         *
         * @param operator the operator character ('+', '-', '*', '/')
         * @return precedence value (1 for (+,-), 2 for (*,/), 0 otherwise)
         */
        private int precedence(char operator) {
            return switch (operator) {
                case '+', '-' -> 1;
                case '*', '/' -> 2;
                default -> 0;
            };
        }

        /**
         * Evaluates an arithmetic expression written in infix notation.
         *
         * <p>
         * Example:
         * <pre>
         * Evaluate("3+(2*4)") → 11
         * </pre>
         *
         * @param expression the expression string to evaluate
         * @return the evaluated integer result
         * @throws ArithmeticException if division by zero occurs
         */
        public int Evaluate(String expression) {
            Stack<Integer> values = new Stack<>();
            Stack<Character> operators = new Stack<>();

            for (char c : expression.toCharArray()) {

                if (Character.isDigit(c)) {
                    values.push(c - '0');
                } else if (c == '(') {
                    operators.push(c);
                } else if (c == ')') {
                    evaluateUntilOpeningParenthesis(values, operators);
                } else {
                    evaluateHigherPrecedenceOperators(values, operators, c);
                    operators.push(c);
                }
            }

            while (!operators.isEmpty()) {
                applyTopOperator(values, operators);
            }

            return values.pop();
        }

        /**
         * Applies operators until '(' is found.
         */
        private void evaluateUntilOpeningParenthesis(Stack<Integer> values,
                                                     Stack<Character> operators) {
            while (operators.peek() != '(') {
                applyTopOperator(values, operators);
            }
            operators.pop(); // remove '('
        }

        /**
         * Applies operators with higher or equal precedence.
         */
        private void evaluateHigherPrecedenceOperators(Stack<Integer> values,
                                                       Stack<Character> operators,
                                                       char currentOperator) {
            while (!operators.isEmpty()
                    && precedence(operators.peek()) >= precedence(currentOperator)) {
                applyTopOperator(values, operators);
            }
        }

        /**
         * Applies the operator on top of the operator stack.
         */
        private void applyTopOperator(Stack<Integer> values,Stack<Character> operators) {
            char operator = operators.pop();
            int right = values.pop();
            int left = values.pop();
            values.push(applyOperator(operator, left, right));
        }

        /**
         * Applies an arithmetic operation to two operands.
         *
         * @param operator the operator ('+', '-', '*', '/')
         * @param left the first operand
         * @param right the second operand
         * @return the result of the operation
         * @throws ArithmeticException if division by zero occurs
         */
        public int applyOperator(char operator, int left, int right) {
            return switch (operator) {
                case '+' -> left + right;
                case '-' -> left - right;
                case '*' -> left * right;
                case '/' -> left / right;
                default -> throw new IllegalArgumentException("Invalid operator: " + operator);
            };
        }

    }
}
