package polynomial;

public class Main {
    public static void main(String[] args) {
        String s = "-4x^3 +3x^1 -5";
        PolynomialImpl a =new PolynomialImpl(s);
        System.out.println(a.toString());
//        String[] terms = s.split(" ");
//        for(String term: terms) {
//            String[] parts = term.split("x\\^");
//            System.out.println(parts[0]);
//            System.out.println(parts[1]);
//        }


        PolynomialImpl polynomial1 = new PolynomialImpl();
        polynomial1.addTerm(3, 2);
        polynomial1.addTerm(-2, 1);
        polynomial1.addTerm(-4, 0);
        polynomial1.addTerm(3, 2);
        System.out.println(polynomial1.toString());

//        PolynomialImpl polynomial2 = new PolynomialImpl();
//        polynomial2.addTerm(3, 2);
//        polynomial2.addTerm(-2, 1);
//        polynomial2.addTerm(-4, 0);
//        System.out.println(polynomial1.getClass());
//        System.out.println(polynomial2.getClass());
//        System.out.println(polynomial1.isSame(polynomial2));
//
//        PolynomialImpl polynomial3 = new PolynomialImpl();
//        PolynomialImpl polynomial4 = new PolynomialImpl();
//        System.out.println(polynomial3.isSame(polynomial4));

    }
}
