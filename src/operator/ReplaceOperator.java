package operator;

import java.util.*;

// This class implements 'StringOperator' and represents a simple replacement operation on a string.
// It replaces the all occurrences of the specified 'oldChar' with 'newChar'.
// The iterator of this class iterates over only one element and therefore behaves as follows:
// The 'next' method returns 'this', if it is called for the first time.
// 'hasNext' is 'true' only if 'next' has not been called.
//
public class ReplaceOperator implements StringOperator {
    private final char oldChar, newChar;

    // Constructor of the 'ReplaceOperator'.
    public ReplaceOperator(char oldChar, char newChar) {
        this.oldChar = oldChar;
        this.newChar = newChar;
    }

    @Override
    public String apply(String operand) {
        return operand.replace(oldChar,newChar);
    }

    @Override
    public StringOperator andThen(StringOperator after) {
        return new ComposedOperator(this, after);
    }

    @Override
    public String toString() {
        return "replace '" +  oldChar + "' with '" + newChar + "'";
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != getClass()) return false;
        ReplaceOperator other = (ReplaceOperator)o;
        return this.oldChar == other.oldChar && this.newChar == other.newChar;
    }

    @Override
    public int hashCode() {
        return oldChar * newChar;
    }

    @Override
    public Iterator<StringOperator> iterator() {
        return new ReplaceIter(this);
    }

    public static class ReplaceIter implements Iterator<StringOperator> {

        private ReplaceOperator value;

        public ReplaceIter(ReplaceOperator value) {
            this.value = value;
        }

        @Override
        public boolean hasNext() {
            return value != null;
        }

        @Override
        public StringOperator next() {
            if(!hasNext()) {
                throw new NoSuchElementException("Jetzt gibts Nasensalad");
            }
            ReplaceOperator notTemp = value;
            value = null;
            return notTemp;
        }
    }
}
