package expression;

import java.util.NoSuchElementException;

public class PraxisApplication {

    /*
    Aufgabe: siehe Angabeblatt

        Ergänzen Sie bitte fehlende Teile der Klassen
        an den mit TODO gekennzeichneten Stellen.
        Auch für teilweise korrekte Lösungen werden Punkte vergeben.

    */

    // 'main' enthält vorgefertigte Testfälle jeweils mit Sollausgabe als Kommentar.
    // Ergänzen weiterer Testfälle ist hier erlaubt.
    // Das Testen wird nicht beurteilt.
    public static void main(String[] args) {

        Expression a = new Sum(new Sum(new Number(5),new Number(-3)),new Number(7));
        System.out.println(a); // 5-3+7

        a = a.add(new Number(-10));
        System.out.println(a); // 5-3+7-10

        for (Integer i: a) {
            System.out.print(i+" ");
        }
        System.out.println();
        // 5 -3 7 -10

        Expression n = new Number(13);
        Iterator iter = n.iterator();
        System.out.println(iter.hasNext()); // true
        System.out.println(iter.next()); // 13

        try {
            System.out.println(iter.next());
        } catch(NoSuchElementException e) {
            System.out.println(e.getMessage()); // No more numbers!
        }

        a = a.add(a);
        System.out.println(a); // 5-3+7-10+5-3+7-10

        System.out.println(a.eval()); // -2
    }
}
