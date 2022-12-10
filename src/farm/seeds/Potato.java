package farm.seeds;

import farm.Plants;

public class Potato extends Plants {

    /**
     * Class constructor for creating a Potato object
     * from the Plant constructor
     * It sets all attributes to default values
     *
     */
    public Potato() {
        super("Potato", "Root Crop", 5, 3,
                4, 1, 2, 10,1, 20, 3, 12.5);
    }
}
