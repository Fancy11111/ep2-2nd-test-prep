package interval;

import java.util.NoSuchElementException;

// 'NonEmpty' repräsentiert eine aufsteigend iterierbare Menge von aufeinander folgenden ganzen Zahlen,
// die mindestens eine Zahl enthält. 'NonEmpty' wird durch Untergrenze 'lower' und Obergenze 'upper'
// des Intervalls bestimmt.
class NonEmpty implements Interval {

    private final int lower, upper;

    public NonEmpty(int lower, int upper) {
        if(lower > upper) {
            throw new IllegalArgumentException("lower " + lower + " must not be greater than upper (" + upper + ")!");
        }
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public boolean contains(Integer n) {
        if(n == null) return false;
        return n >= lower && n <= upper;
    }

    @Override
    // liefert 'true' genau dann, wenn alle Zahlen aus 'iv' enthalten sind.
    public boolean containsAll(Interval iv) {
        for(Integer i : iv) {
            if(!contains(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Interval intersect(Interval iv) {
        if(iv.isEmpty()) {
            return Empty.EMPTY;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int val : iv) {
            min = Math.min(min, val);
            max = Math.max(max, val);
        }
        min = Math.max(min,lower);
        max = Math.min(max,upper);
        if(min > max) {
            return Empty.EMPTY;
        }
        return new NonEmpty(min, max);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator iterator() {
        return new IntervalIter(lower, upper);
    }

    @Override
    public String toString() {
        return "[" + lower + ", " + upper + "]";
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        NonEmpty other = (NonEmpty)o;
        return other.lower == lower && other.upper == upper;
    }

    @Override
    public int hashCode() {
        return Math.abs(lower) * Math.abs(upper) + Math.abs(lower) + Math.abs(upper);
    }

    public static class IntervalIter implements Iterator {

        private int current;
        private final int max;

        public IntervalIter(int current, int max) {
            this.current = current;
            this.max = max;
        }


        @Override
        public boolean hasNext() {
            return current <= max;
        }

        @Override
        public Integer next() {
            if(!hasNext()) {
                throw new NoSuchElementException("no more elements");
            }
            return current++;
        }
    }
}