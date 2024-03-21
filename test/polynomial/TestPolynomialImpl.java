package polynomial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test class for the PolynomialImpl class.
 */
public class TestPolynomialImpl {

    /**
     * Tests the addTerm method of the PolynomialImpl class.
     * Verifies that terms are added correctly to the polynomial.
     */
    @Test
    public void testAddTerm() {
        Polynomial polynomial = new PolynomialImpl();
        polynomial.addTerm(3, 2);
        polynomial.addTerm(-5, 3);
        polynomial.addTerm(2, 1);
        polynomial.addTerm(-4, 0);

        assertEquals("-5x^3 +3x^2 +2x^1 -4", polynomial.toString());
    }


    /**
     * Tests the evaluate method of the PolynomialImpl class with two constructors.
     * Verifies that the polynomial evaluates correctly for different values of x.
     */
    @Test
    public void testEvaluate() {
        // Create a polynomial: 5x^2 + 4x - 2
        Polynomial polynomial = new PolynomialImpl();
        polynomial.addTerm(5, 2);
        polynomial.addTerm(4, 1);
        polynomial.addTerm(-2, 0);

        // Evaluate the polynomial for x = 2.0
        double result1 = polynomial.evaluate(2.0);
        assertEquals(26.0, result1);

        // Evaluate the polynomial for x = -2.0
        double result2 = polynomial.evaluate(-2.0);
        assertEquals(10.0, result2);

        // Evaluate the polynomial for x = 0.0
        double result3 = polynomial.evaluate(0.0);
        assertEquals(-2.0, result3);

        String s = "-4x^3 +3x^1 -5";
        PolynomialImpl polynomial2 =new PolynomialImpl(s);
        double result4 = polynomial2.evaluate(2.0);
        assertEquals(-31.0, result4);
    }

    /**
     * Tests the add method of the PolynomialImpl class.
     * Verifies that two polynomials are correctly added together.
     */
    @Test
    public void testAdd() {
        Polynomial polynomial1 = new PolynomialImpl();
        polynomial1.addTerm(3, 2);
        polynomial1.addTerm(-2, 1);
        polynomial1.addTerm(-4, 0);

        Polynomial polynomial2 = new PolynomialImpl();
        polynomial2.addTerm(2, 2);
        polynomial2.addTerm(2, 1);
        polynomial2.addTerm(4, 0);

        Polynomial result = polynomial1.add(polynomial2);

        assertEquals("5x^2", result.toString());
    }

    /**
     * Tests the getDegree method of the PolynomialImpl class.
     * Verifies that the degree of the polynomial is obtained correctly.
     */
    @Test
    public void testGetDegree() {
        Polynomial polynomial1 = new PolynomialImpl();
        polynomial1.addTerm(3, 2);
        polynomial1.addTerm(-2, 1);
        polynomial1.addTerm(-4, 0);

        assertEquals(2, polynomial1.getDegree());
    }

    /**
     * Tests the getCoefficient method of the PolynomialImpl class.
     * Verifies that the coefficient of a term is obtained correctly.
     */
    @Test
    public void testGetCoefficient() {
        Polynomial polynomial1 = new PolynomialImpl();
        polynomial1.addTerm(3, 2);
        polynomial1.addTerm(-2, 1);
        polynomial1.addTerm(-4, 0);

        assertEquals(3, polynomial1.getCoefficient(2));
        assertEquals(-2, polynomial1.getCoefficient(1)); // Coefficient of x^4 is 0
    }

    /**
     * Tests the isSame method of the PolynomialImpl class.
     * Verifies that two polynomials are correctly identified as the same or different.
     */
    @Test
    public void testIsSame() {
        PolynomialImpl polynomial1 = new PolynomialImpl();
        polynomial1.addTerm(3, 2);
        polynomial1.addTerm(-2, 1);
        polynomial1.addTerm(-4, 0);

        PolynomialImpl polynomial2 = new PolynomialImpl();
        polynomial2.addTerm(3, 2);
        polynomial2.addTerm(-2, 1);
        polynomial2.addTerm(-4, 0);

        assertEquals(true, polynomial1.isSame(polynomial2));

        PolynomialImpl polynomial3 = new PolynomialImpl();
        polynomial1.addTerm(2, 2);
        polynomial1.addTerm(2, 1);
        polynomial1.addTerm(4, 0);

        assertEquals(false, polynomial1.isSame(polynomial3));
    }

    /**
     * Tests the toString method of the PolynomialImpl class with two constructors.
     * Verifies that the string representation of the polynomial is correct.
     */
    @Test
    public void testToString() {
        PolynomialImpl polynomial1 = new PolynomialImpl();
        polynomial1.addTerm(3, 2);
        polynomial1.addTerm(-2, 1);
        polynomial1.addTerm(-4, 0);

        assertEquals("3x^2 -2x^1 -4", polynomial1.toString());

        String s = "-4x^3 +3x^1 -5";
        PolynomialImpl polynomial2 =new PolynomialImpl(s);
        assertEquals("-4x^3 +3x^1 -5", polynomial2.toString());
    }
}
