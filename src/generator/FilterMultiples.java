package generator;

import java.util.NoSuchElementException;

// Ein 'FilterMultiples'-Objekt erzeugt aus einer Datenquelle 'source' eine gefilterte Zahlenfolge.
// Alle Zahlen, die durch 'div' teilbar sind, sind nicht Teil der gefilterten Folge.
// Alle übrigen Zahlen von 'source' sind Teil der gefilterten Folge.
// hasNext() und next() beziehen sich auf die gefilterte Folge.
class FilterMultiples implements FilteredGenerator {

    private final Generator source;
    private final int div;
    private int number;
    private boolean exists;

    public FilterMultiples(Generator source, int div) {
        this.source = source;
        this.div = div;
    }

    @Override
    public boolean pass(int i) {
        return i % div != 0;
    }

    @Override
    public FilteredGenerator copy() {
        return new FilterMultiples(source.copy(), div);
    }

    public boolean hasNext() {
        while(source.hasNext() && !exists) {
            number = source.next();
            if(pass(number)) {
                exists = true;
            }
        }
        return exists;
    }

    public Integer next() {
        if(!hasNext()) {
            throw new NoSuchElementException("Nasensalat time");
        }
        exists = false;
        return number;
    }
}
