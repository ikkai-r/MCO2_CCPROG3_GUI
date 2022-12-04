package farm;

import java.util.ArrayList;
import java.util.HashMap;

public class Plants {

    private String plantName;
    private String plantType;
    private int harvestTime;
    private int waterNeeded;
    private int fertilizerNeeded;
    private int maxProductsProduced;
    private int minProductsProduced;
    private int waterCapBonus;
    private int fertCapBonus;
    private double seedCost;
    private double baseCost;
    private double experienceYield;

    /**
     * serves as a class constructor for the different kinds of plants available in the game
     *
     * @param plantName
     * @param plantType
     * @param harvestTime
     * @param waterNeeded
     * @param waterCapBonus
     * @param fertilizerNeeded
     * @param fertCapBonus
     * @param maxProductsProduced
     * @param minProductsProduced
     * @param seedCost
     * @param baseCost
     * @param experienceYield
     */
    public Plants(String plantName, String plantType, int harvestTime, int waterNeeded, int waterCapBonus, int fertilizerNeeded, int fertCapBonus,
                   int maxProductsProduced, int minProductsProduced, double seedCost, double baseCost, double experienceYield) {
        this.plantName = plantName;
        this.plantType = plantType;
        this.harvestTime = harvestTime;
        this.waterNeeded = waterNeeded;
        this.maxProductsProduced = maxProductsProduced;
        this.minProductsProduced = minProductsProduced;
        this.waterCapBonus = waterCapBonus;
        this.fertCapBonus = fertCapBonus;
        this.fertilizerNeeded = fertilizerNeeded;
        this.seedCost = seedCost;
        this.baseCost = baseCost;
        this.experienceYield = experienceYield;
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */
    public String getPlantName() {
        return plantName;
    }

    public String getPlantType() {
        return plantType;
    }

    public double getHarvestTime() {
        return harvestTime;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public int getFertilizerNeeded() {
        return fertilizerNeeded;
    }

    public int getMaxProductsProduced() {
        return maxProductsProduced;
    }

    public int getMinProductsProduced() {
        return minProductsProduced;
    }

    public double getSeedCost() {
        return seedCost;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public double getExperienceYield() {
        return experienceYield;
    }

    public int getWaterCapBonus() {
        return waterCapBonus;
    }

    public int getFertCapBonus() {
        return fertCapBonus;
    }
}