package farm;

import farm.seeds.*;

import java.util.ArrayList;

public class Seeds {

    private ArrayList<Plants> plants = new ArrayList<>();

    /**
     * Creates the list of available plants in the game
     */
    public Seeds() {
        plants.add(new Turnip());
        plants.add(new Carrot());
        plants.add(new Potato());
        plants.add(new Rose());
        plants.add(new Tulips());
        plants.add(new Sunflower());
        plants.add(new Mango());
        plants.add(new Apple());
    }

    /**
     * Checks the plants if there is an equal crop to the parameter
     *
     * @param crop name of the crop
     *
     * @return index of the crop that matches with the crop name
     */
    public int getCropIndex(String crop) {
        int counter = -1;

        for (Plants plants : plants) {
            counter++;
            if (plants.getPlantName().contains(crop)) {
                return counter;
            }
        }

        return counter;
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */
    public void setPlants(ArrayList<Plants> plants) {
        this.plants = plants;
    }

    public ArrayList<Plants> getPlants() {
        return plants;
    }
}
