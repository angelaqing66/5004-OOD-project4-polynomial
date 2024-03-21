package polynomial;

import java.util.*;

/**
 * Represents a term in a polynomial.
 */
class Term {
    int coefficient;
    int power;
    Term next;

    /**
     * the constructer of the Term class
     * @param coefficient
     * @param power
     */
    public Term(int coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
        this.next = null;
    }

}


/**
 * Implementation of the Polynomial interface representing a polynomial function.
 */
public class PolynomialImpl implements Polynomial{
    private Term head;

    /**
     * Default constructor initializing the polynomial with an empty set of terms.
     */
    public PolynomialImpl() {
        this.head = null;
    }

    /**
     * Constructor initializing the polynomial by parsing the given polynomial string.
     * Each term in the string should be separated by a space.
     *
     * @param polynomialStr the string representation of the polynomial
     */
    public PolynomialImpl(String polynomialStr) {
        this();
        parsePolynomial(polynomialStr);
    }

    /**
     * Parses the given polynomial string and adds terms to the polynomial.
     *
     * @param polynomialStr the string representation of the polynomial
     */
    private void parsePolynomial(String polynomialStr) {
        String[] terms = polynomialStr.split(" ");
        for (String term : terms) {
            String[] parts = term.split("x\\^");
            int coefficient, power;
            if (parts.length == 1) {
                coefficient = Integer.parseInt(parts[0]);
                power = 0;
            } else {
                coefficient = Integer.parseInt(parts[0]);
                power = Integer.parseInt(parts[1]);
            }
            addTerm(coefficient, power);
        }
    }


    /**
     * Add this polynomial to another and return the result as another polynomial.
     *
     * @param other the other polynomial to be added
     * @return the resulting polynomial
     * @throws IllegalArgumentException if parameter is not the same concrete type
     *                                  as the current object.
     */
    @Override
    public Polynomial add(Polynomial other) throws IllegalArgumentException {
        if (other == null) {
            throw new IllegalArgumentException("The polynomial to add cannot be null.");
        }

        if (getClass() != other.getClass()) {
            throw new IllegalArgumentException("Polynomials must be of the same concrete type.");
        }

        PolynomialImpl otherPoly = (PolynomialImpl) other;
        PolynomialImpl result = new PolynomialImpl();

        // Add terms from the current polynomial
        Term current = head;
        while (current != null) {
            result.addTerm(current.coefficient, current.power);
            current = current.next;
        }

        // Add terms from the other polynomial
        current = otherPoly.head;
        while (current != null) {
            result.addTerm(current.coefficient, current.power);
            current = current.next;
        }

        return result;
    }

    /**
     * Add a term to this polynomial with the specified coefficient and power.
     *
     * @param coefficient the coefficient of the term to be added
     * @param power       the power of the term to be added
     * @throws IllegalArgumentException if the power is negative
     */
    @Override
    public void addTerm(int coefficient, int power) throws IllegalArgumentException {
        if (power < 0) {
            throw new IllegalArgumentException("Power cannot be negative.");
        }
        if (coefficient == 0) {
            return;
        }
        if (head == null || head.power < power) {
            Term newTerm = new Term(coefficient, power);
            newTerm.next = head;
            head = newTerm;
        } else {
            Term current = head;
            Term prev = null;
            while (current != null && current.power > power) {
                prev = current;
                current = current.next;
            }
            if (current != null && current.power == power) {
                current.coefficient += coefficient;
                if (current.coefficient == 0) {
                    if (prev != null) {
                        prev.next = current.next;
                    } else {
                        head = current.next;
                    }
                }
            } else {
                Term newTerm = new Term(coefficient, power);
                if (prev != null) {
                    prev.next = newTerm;
                } else {
                    head = newTerm;
                }
                newTerm.next = current;
            }
        }
    }

    /**
     * Determines if this polynomial is the same as the parameter polynomial.
     *
     * @param poly the polynomial to use
     * @return true if this polynomial is of the same concrete type and has the same
     * terms as the parameter, false otherwise
     */
    @Override
    public boolean isSame(Polynomial poly) {
        if (!(poly instanceof PolynomialImpl)) {
            return false;
        }
        PolynomialImpl otherPoly = (PolynomialImpl) poly;
        Term current1 = this.head;
        Term current2 = otherPoly.head;
        while (current1 != null && current2 != null) {
            if (current1.coefficient != current2.coefficient || current1.power != current2.power) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }
        return current1 == null && current2 == null;
    }

    /**
     * Evaluate the value of this polynomial at the given value of the variable.
     *
     * @param x the value at which the polynomial is to be evaluated.
     * @return the value of the polynomial at x
     */
    @Override
    public double evaluate(double x) {
        double result = 0;
        Term current = head;
        while(current != null) {
            result = result + current.coefficient * Math.pow(x, current.power);
            current = current.next;
        }
        return result;
    }

    /**
     * Return the coefficient of the term with the given power.
     *
     * @param power the power whose coefficient is sought
     * @return the coefficient at the given power
     */
    @Override
    public int getCoefficient(int power) {
        Term current = head;
        while (current != null && current.power != power) {
            current = current.next;
        }
        return current != null ? current.coefficient : 0;
    }

    /**
     * Get the degree of this polynomial.
     *
     * @return the degree of this polynomial as a whole number
     */
    @Override
    public int getDegree() {
        return head != null ? head.power : 0;
    }


    /**
     * Returns a string representation of the polynomial.
     *
     * @return the string representation of the polynomial
     */
    @Override
    public String toString() {
        if (head == null) {
            return "0";
        }

        StringBuilder builder = new StringBuilder();
        Term current = head;
        boolean firstTerm = true;

        while (current != null) {
            int coefficient = current.coefficient;
            int power = current.power;

            // Append the coefficient if it's not zero
            if (coefficient != 0) {
                // Append the sign if it's not the first term and the coefficient is positive
                if (!firstTerm && coefficient > 0) {
                    builder.append(" +");
                } else if (coefficient < 0) { // Append a minus sign if the coefficient is negative
                    builder.append(" -");
                    coefficient = -coefficient; // Make the coefficient positive for display
                }

                // Append the coefficient if it's not 1 or the power is 0
                if (coefficient != 1 || power == 0) {
                    builder.append(coefficient);
                }

                // Append 'x' if the power is not zero
                if (power > 0) {
                    builder.append("x");

                    // Append '^' followed by the power if the power is greater than 1
                    if (power >= 1) {
                        builder.append("^").append(power);
                    }
                }

                firstTerm = false; // Update firstTerm after processing the first term
            }

            current = current.next; // Move to the next term
        }

        return builder.toString().trim();
    }
}
