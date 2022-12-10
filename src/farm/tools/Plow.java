package farm.tools;

import farm.Tools;

public class Plow extends Tools {

    /**
     * Class constructor for creating a Plow object
     * from the Tools constructor
     * It sets all attributes to default values
     *
     */

    public Plow() {
        super("Plow", "Converts an unplowed tile to a plowed tile.",
                0, 0.5);
    }

}
