package expression;

import java.util.NoSuchElementException;

// Eine Summe mit linkem und rechtem Summanden. Der Iterator iteriert über alle Zahlen des Ausdrucks von links nach rechts.
class Sum implements Expression {

    private final Expression left;
    private Expression right;
    // Konstruktor
    public Sum(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Sum add(Expression exp) {
        return  new Sum(this, exp);
    }

    public int eval() {
        return this.left.eval() + this.right.eval();
    }

    @Override
    public String toString() {
        return left + " + " + right;
    }

    public Iterator iterator() {
        return new SumIter(left.iterator(), right.iterator());
    }

    public static class SumIter implements Iterator {
        private final Iterator leftIter, rightIter;

        public SumIter(Iterator leftIter, Iterator rightIter) {
            this.leftIter = leftIter;
            this.rightIter = rightIter;
        }

        @Override
        public boolean hasNext() {
            return leftIter.hasNext() || rightIter.hasNext();
        }

        @Override
        public Integer next() {
            if(!hasNext()) {
                throw new NoSuchElementException("no more elements");
            }
            if(leftIter.hasNext()){
                return leftIter.next();
            }
            return rightIter.next();
        }
    }

}
