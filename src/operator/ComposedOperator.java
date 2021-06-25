package operator;

import java.util.*;

// This class implements 'StringOperator' and represents a composition of two 'StringOperator'
// objects that are applied in succession (both can be 'ComposedOperator' objects themselves).
// Calling this object's 'apply' method returns the result of applying the second 'StringOperator'
// on the result of applying the first one on the specified string.
// 'ComposedOperator' objects can be build using the 'andThen' method specified in the
// interface 'StringOperator'.
//
public class ComposedOperator implements StringOperator {

    private final StringOperator firstOper, secondOper;

    public ComposedOperator(StringOperator firstOper, StringOperator secondOper) {
        this.firstOper = firstOper;
        this.secondOper = secondOper;
    }

    @Override
    public String apply(String operand) {
        return secondOper.apply(firstOper.apply(operand));
    }

    @Override
    public StringOperator andThen(StringOperator after) {
        return new ComposedOperator(this, after);
    }

    @Override
    public Iterator<StringOperator> iterator() {
        return new CompIter(firstOper, secondOper);
    }

    @Override
    public String toString() {
        return firstOper + " and then " + secondOper;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != getClass()) return false;
        ComposedOperator other = (ComposedOperator)o;
        return this.firstOper.equals(other.firstOper) && this.secondOper.equals(other.secondOper);
    }

    @Override
    public int hashCode() {
        return firstOper.hashCode() + secondOper.hashCode();
    }

    public static class CompIter implements Iterator<StringOperator> {

        private final Iterator<StringOperator> firstIter, secondIter;

        public CompIter(StringOperator first, StringOperator second) {
            firstIter = first.iterator();
            secondIter = second.iterator();
        }

        @Override
        public boolean hasNext() {
            return firstIter.hasNext() || secondIter.hasNext();
        }

        @Override
        public StringOperator next() {
            if(!hasNext()) {
                throw new NoSuchElementException("Jetzt gibts Nasensalad");
            }
            if(firstIter.hasNext()) {
                return firstIter.next();
            }
            return secondIter.next();
        }
    }
}