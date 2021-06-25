package orbital;

import java.util.NoSuchElementException;

// This class represents a single celestial body with no other objects in orbit.
// A celestial body has a name and a mass in kilograms.
//
// The iterator's 'hasNext' method always returns 'false'. The iteration has no elements.
//
// Adding another object into its orbit (method 'add') results in a 'CelestialSystem' with 'this'
// as central body.
//
public class CelestialBody implements Orbitable {

    private final String name;
    private final double mass;
    public CelestialBody(String name, double mass) {
        this.name = name;
        this.mass = mass;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Orbitable add(Orbitable inOrbit) {
        return new CelestialSystem(this, inOrbit);
    }

    public String toString() {
        return this.name + ": " + this.mass + "kg";
    }

    @Override
    public OrbitIterator iterator() {
        return new OrbitIterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Orbitable next() {
                throw new NoSuchElementException("no element for you");
            }
        };
    }
}

