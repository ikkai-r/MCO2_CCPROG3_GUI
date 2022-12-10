package farm.seeds;

import farm.Plants;

public class Apple extends Plants {

    /**
     * Class constructor for creating an Apple object
     * from the Plant constructor
     * It sets all attributes to default values
     *
     */
    public Apple() {
        super("Apple", "Fruit Tree", 10, 7,
                7, 5, 5, 15, 10, 200, 5, 25);
    }
}
