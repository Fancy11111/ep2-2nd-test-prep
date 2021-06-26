package orbital;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

// A celestial system implements 'Orbitable' and has at least one object in orbit around its
// central celestial body. Further objects can be added.
//
public class CelestialSystem implements Orbitable {

    private final CelestialBody centralBody;
    private ArrayList<Orbitable> inOrbit;

    // Initialises this system with its central body.
    public CelestialSystem(CelestialBody centralBody, Orbitable inOrbit) {
        this.centralBody = centralBody;
        this.inOrbit = new ArrayList<Orbitable>();
        this.inOrbit.add(inOrbit);
    }

    private CelestialSystem(CelestialBody centralBody, ArrayList<Orbitable> inOrbit) {
        this.centralBody = centralBody;
        this.inOrbit = inOrbit;
    }


    @Override
    public String getName() {
        return centralBody.getName();
    }

    @Override
    public Orbitable add(Orbitable inOrbit) {
        ArrayList<Orbitable> copy = (ArrayList<Orbitable>) this.inOrbit.clone();
        copy.add(inOrbit);
        return new CelestialSystem(centralBody, copy);
    }

    @Override
    public String toString() {
        String s = centralBody.toString();
        for(Orbitable o : this) {
            // stream flex
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

        return new OrbitIterator() {
            private Iterator<Orbitable> iterator = inOrbit.iterator();
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Orbitable next() {
                if(!hasNext()) {
                    throw new NoSuchElementException("no more elements for you");
                }
                return iterator.next();
            }
        };
    }
}

