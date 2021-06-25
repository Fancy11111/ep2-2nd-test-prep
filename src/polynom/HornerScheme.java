package polynom;

import java.util.*;

// The class implements 'polynomial.Polynomial' and represents the form (a + x*p), where 'a' is an Integer
// and 'p' is another polynomial. 'p' must not be 'null' (objects of 'polynomial.Constant' shall be used to represent constants).
// This class implements 'polynomial.Polynomial' by using Horner's scheme.
// Example:
// a₀ + a₁*x + a₂*x² + a₃*x³ + a₄*x⁴
// The polynomial above is represented in Horner's scheme as:
// a₀ + x*(a₁ + x*(a₂ + x*(a₃ + x*a₄)))
//
public class HornerScheme implements Polynomial {

    //TODO: define missing parts of this class.
    private final int a;
    private Polynomial p;

    // Initializes this object.
    // Precondition: 'p' must not be 'null'.
    public HornerScheme(int a, Polynomial p) {
        //TODO: implement this constructor.
        this.a = a;
        this.p = p;
    }

    // Creates a polynomial from the coefficients specified by the array coeffs = {a₀, a₁, a₂, ... a𝘥}.
    // Precondition: coeffs != null and coeffs has at least one element.
    public static Polynomial create(int[] coeffs) {
        if(coeffs.length == 1){
            return new Constant(coeffs[0]);
        }

        Polynomial polynomial = new HornerScheme(coeffs[coeffs.length - 2], new Constant(coeffs[coeffs.length - 1]));
        for(int i = coeffs.length-3; i >= 0; i--){
            polynomial = new HornerScheme(coeffs[i], polynomial);
        }

        return polynomial;
    }

    @Override
    public int degree() {
        return 1+p.degree();
    }

    @Override
    public List<Integer> coefficients() {
        List<Integer> list = p.coefficients();
        list.add(0, a);
        return list;
    }

    @Override
    public double eval(double x) {
        return a + x * p.eval(x);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HornerIter(p,a);
    }

    @Override
    // (3 + x*(-2 + x*(0 + x*(7 + x*5))))
    // a₀ + x*(a₁ + x*(a₂ + x*(a₃ + x*a₄)))
    public String toString() {
        return "(" + a + " + x*" + p + ")";
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || o.getClass() != getClass()) return false;
        HornerScheme other = (HornerScheme)o;
        return a == other.a && p.equals(other.p);
    }

    @Override
    public int hashCode() {
        return a * degree() + p.hashCode();
    }

    public static class HornerIter implements Iterator<Integer> {
        private Iterator<Integer> iter;
        private Integer a;

        public HornerIter(Polynomial p, int a) {
            this.a = a;
            this.iter = p.iterator();
        }

        public boolean hasNext() {
            return (a != null) || iter.hasNext();
        }

        public Integer next() {
            if(!hasNext()) {
                throw new NoSuchElementException("Nochmal so ne Aktion und es gibt Nasensalat");
            }
            if(a != null) {
                int notTemp = a;
                a = null;
                return notTemp;
            }
            return iter.next();
        }
    }
}



