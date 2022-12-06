package farm.tools;

import farm.Tools;

public class WateringCan extends Tools {
    public WateringCan() {
        super("Watering Can", "Adds to the total number of tiles a tile/crop has been watered. " +
                        "Can only be performed on a plowed tile with a crop.", 0, 0.5);
    }
}
