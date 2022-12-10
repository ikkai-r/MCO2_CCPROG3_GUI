package farm;

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

    /**
     * Acts as the getter for the plant's name
     * @return the name of the plant
     */
    public String getPlantName() {
        return plantName;
    }

    /**
     * Acts as the getter for the plant's type
     * @return the type of the plant
     */
    public String getPlantType() {
        return plantType;
    }

    /**
     * Acts as the getter for the plant's harvest time
     * @return the harvest time of the plant
     */
    public double getHarvestTime() {
        return harvestTime;
    }

    /**
     * Acts as the getter for the plant's water needed
     * @return the amount of water needed of the plant
     */
    public int getWaterNeeded() {
        return waterNeeded;
    }

    /**
     * Acts as the getter for the plant's fertilizer needed
     * @return the amount of fertilizer needed of the plant
     */
    public int getFertilizerNeeded() {
        return fertilizerNeeded;
    }

    /**
     * Acts as the getter for the plant's maximum products produced
     * @return the number of maximum products produced of the plant
     */
    public int getMaxProductsProduced() {
        return maxProductsProduced;
    }

    /**
     * Acts as the getter for the plant's minimum products produced
     * @return the number of minimum products produced of the plant
     */
    public int getMinProductsProduced() {
        return minProductsProduced;
    }

    /**
     * Acts as the getter for the plant's seed cost
     * @return the seed cost of the plant
     */
    public double getSeedCost() {
        return seedCost;
    }

    /**
     * Acts as the getter for the plant's base cost
     * @return the base cost of the plant
     */
    public double getBaseCost() {
        return baseCost;
    }

    /**
     * Acts as the getter for the plant's experience yield
     * @return the experience yield of the plant
     */
    public double getExperienceYield() {
        return experienceYield;
    }

    /**
     * Acts as the getter for the plant's water cap
     * @return the water cap of the plant
     */
    public int getWaterCapBonus() {
        return waterCapBonus;
    }

    /**
     * Acts as the getter for the plant's fertilizer cap
     * @return the fertilizer cap of the plant
     */
    public int getFertCapBonus() {
        return fertCapBonus;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public void setHarvestTime(int harvestTime) {
        this.harvestTime = harvestTime;
    }

    public void setWaterNeeded(int waterNeeded) {
        this.waterNeeded = waterNeeded;
    }

    public void setFertilizerNeeded(int fertilizerNeeded) {
        this.fertilizerNeeded = fertilizerNeeded;
    }

    public void setMaxProductsProduced(int maxProductsProduced) {
        this.maxProductsProduced = maxProductsProduced;
    }

    public void setMinProductsProduced(int minProductsProduced) {
        this.minProductsProduced = minProductsProduced;
    }

    public void setWaterCapBonus(int waterCapBonus) {
        this.waterCapBonus = waterCapBonus;
    }

    public void setFertCapBonus(int fertCapBonus) {
        this.fertCapBonus = fertCapBonus;
    }

    public void setSeedCost(double seedCost) {
        this.seedCost = seedCost;
    }

    public void setBaseCost(double baseCost) {
        this.baseCost = baseCost;
    }

    public void setExperienceYield(double experienceYield) {
        this.experienceYield = experienceYield;
    }
}