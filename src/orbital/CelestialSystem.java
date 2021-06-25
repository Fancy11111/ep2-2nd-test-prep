package orbital;

import java.util.Arrays;
import java.util.NoSuchElementException;

// A celestial system implements 'Orbitable' and has at least one object in orbit around its
// central celestial body. Further objects can be added.
//
public class CelestialSystem implements Orbitable {

    private final CelestialBody centralBody;
    private Node inOrbit;

    // Initialises this system with its central body.
    public CelestialSystem(CelestialBody centralBody, Orbitable inOrbit) {
        this.centralBody = centralBody;
        this.inOrbit = new Node(inOrbit);
    }


    @Override
    public String getName() {
        return centralBody.getName();
    }

    @Override
    public Orbitable add(Orbitable inOrbit) {
        this.inOrbit.add(inOrbit);
        return this;
    }

    @Override
    public String toString() {
        String s = centralBody.toString();
        for(Orbitable o : this) {
//            s += Arrays.stream(o.toString().split("\n"))
//                    .reduce("", (cs, ts) -> cs + "\n\t"  + ts);
            String[] lines = o.toString().split("\n");
            for (String line : lines) {
                s += "\n\t" + line;
            }
        }
        return s;
    }

    @Override
    public OrbitIterator iterator() {
        return new SystemIter(inOrbit);
    }

    private static class SystemIter implements OrbitIterator {

        private Node curr;

        SystemIter(Node curr) {
            this.curr = curr;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Orbitable next() {
            if(!hasNext()) {
                throw new NoSuchElementException("no more elements for you");
            }
            Orbitable next = curr.getVal();
            curr = curr.getNext();
            return next;
        }
    }

    private static class Node {
        private Orbitable val;
        private Node next;
        public Node(Orbitable val) {
            this.val = val;
        }

        public void add(Orbitable val) {
            if(next != null) {
                next.add(val);
            }
            else {
                next = new Node(val);
            }
        }

        public Node getNext() {
            return next;
        }

        public Orbitable getVal() {
            return val;
        }
    }
}

