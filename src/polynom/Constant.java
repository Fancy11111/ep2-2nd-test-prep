package polynom;

import java.util.*;

// 'polynomial.Constant' implements 'polynomial.Polynomial' and is used by class 'polynomial.HornerScheme' to represent a polynomial
// with degree 0. Its value is a*x⁰ = a (regardless of the 'x' used for evaluation).
// The iterator of this class iterates over only one value and therefore behaves as follows:
// The 'next' method returns the value of 'this' (specified by 'a'), if it is called for the first
// time. 'hasNext' is 'true' only if 'next' has not been called.
//
public class Constant implements Polynomial {

    //TODO: define missing parts of this class.
    private final int a;


    // Initializes this object.
    // Precondition: a != 0.
    public Constant(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "" + a;
    }

    @Override
    public int degree() {
        return 0;
    }

    @Override
    public List<Integer> coefficients() {
        List<Integer> coeffs = new ArrayList<>();
        coeffs.add(a);
        return coeffs;
    }

    @Override
    public double eval(double x) {
        return a;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ConstantIter(a);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || o.getClass() != getClass()) return false;
        Constant other = (Constant)o;
        return a == other.a;
    }

    @Override
    public int hashCode() {
        return a;
    }

    public static class ConstantIter implements Iterator<Integer> {
        private Integer a;

        public ConstantIter(int a) {
            this.a = a;
        }

        @Override
        public boolean hasNext() {
            return a != null;
        }

        @Override
        public Integer next() {
            if(a == null) {
                throw new NoSuchElementException("28 Grad im Zimmer, cool cool cool cool cool cool");
            }
            int notTemp = a;
            a = null;
            return notTemp;
        }
    }
}


