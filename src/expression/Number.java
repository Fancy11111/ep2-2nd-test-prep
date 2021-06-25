package expression;

import java.util.NoSuchElementException;

// Diese Klasse repräsentiert eine ganze Zahl. Ihr Iterator liefert nur eine Zahl und ist danach verbraucht.
class Number implements Expression {

    private int num;
    public Number(int num) {
        this.num = num;
    }

    public int eval() { return num; }

    public Sum add(Expression exp) { return new Sum(this, exp); }

    @Override
    public String toString() {
        return "" + num;
    }

    public Iterator iterator() { return new Iterator() {

        private boolean used = false;

        @Override
        public boolean hasNext() {
            return !used;
        }

        @Override
        public Integer next() {
            if(!hasNext()) {
                throw new NoSuchElementException("no more element");
            }
            used = true;
            return num;
        }
    }; }
}
