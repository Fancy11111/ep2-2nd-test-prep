package expression;

// Iterierbare Objekte mit ganzzahligen Elementen
interface Iterable extends java.lang.Iterable<java.lang.Integer> {
    Iterator iterator();
}

// Ein Iterator über ganze Zahlen.
interface Iterator extends java.util.Iterator<java.lang.Integer> {
    boolean hasNext();
    java.lang.Integer next();
}
