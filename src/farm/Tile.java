package farm;

public class Tile {

    private Seeds seeds = new Seeds();
    private Farmer farmer = new Farmer();

    private String crop;
    private boolean isSelected;
    private boolean isPlowed;
    private boolean isWithered;
    private boolean hasRock;
    private boolean hasSeed;
    private boolean isHarvestable;
    private int timesWatered;
    private int timesFertilized;
    private int daysPassed;

    /**
     * Class constructor for creating new tile
     * It sets all attributes to default values
     *
     */
    public Tile() {
        this.isPlowed = false;
        this.isWithered = false;
        this.hasRock = false;
        this.hasSeed = false;
        this.isHarvestable = false;
        this.crop = "";
        this.timesWatered = 0;
        this.timesFertilized = 0;
        this.daysPassed = 0;
    }

    /**
     *
     * Checks the number of tiles watered
     *
     * @return if the tile's watering limit is exceeded
     */
    public boolean canWater() {
        if (farmer.getWaterBonusLimits() > 0) {
            return this.timesWatered < this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getWaterCapBonus() ||
                    this.timesWatered < this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getWaterNeeded();
        } else {
            return this.timesWatered < this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getWaterNeeded();
        }
    }

    /**
     *
     * Checks the number of tiles fertilized
     *
     * @return if the tile's fertilizing limit is exceeded
     */
    public boolean canFertilize() {
        if (this.farmer.getFertBonusLimits() > 0) {
            return this.timesFertilized < this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getFertCapBonus() ||
                    this.timesFertilized < this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getFertilizerNeeded();
        } else {
            return this.timesFertilized < this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getFertilizerNeeded();
        }
    }

    /**
     * Checks the tile if it can be planted
     * @return if the tile is plowed, does not have a rock, no plant, and is not withered
     */
    public boolean canPlant() {
        return this.isPlowed && !this.hasRock && this.crop.isEmpty() && !this.isWithered;
    }

    /**
     * checks if the crop on the tile passes as a withered crop
     * and sets withered tile
     */
    public void checkWithered() {
        //if days passed is more than the harvest time OR
        //if days passed is equal to the harvest time AND
        //if it does not meet the water or fertilize limit (hasn't been taken care of)
        if (!this.crop.isEmpty()) {
            if ((this.daysPassed > this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getHarvestTime()) ||
                    (this.daysPassed == this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getHarvestTime() && (canWater() || canFertilize()))) {
                this.isPlowed = false;
                this.isWithered = true;
                this.hasSeed = false;
                this.crop = "";
                this.timesWatered = 0;
                this.timesFertilized = 0;
                this.daysPassed = 0;
            }
        }
    }

    /**
     checks if the crop on the tile passes as a harvestable crop
     * and sets harvestable tile
     */
    public void checkHarvestable() {

        if (!this.crop.isEmpty()) {
            if (this.daysPassed == this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getHarvestTime()
                    && (this.timesWatered == this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getWaterNeeded() || this.timesWatered == this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getWaterCapBonus())
                    && (this.timesFertilized == this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getFertilizerNeeded() || this.timesFertilized == this.seeds.getPlants().get(this.seeds.getCropIndex(this.crop)).getFertCapBonus())) {
                this.isHarvestable = true;
            }
        }

    }

    /**
     * turns the tile into a tile with default values.
     * @param tile the tile that will be reset.
     */
    public void resetTile(Tile tile) {
        tile.setPlowed(false);
        tile.setCrop("");
        tile.setHasSeed(false);
        tile.setDaysPassed(0);
        tile.setHarvestable(false);
        tile.setTimesFertilized(0);
        tile.setTimesWatered(0);
        tile.setWithered(false);
        tile.setRock(false);
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */

    /**
     * Acts as the getter for the boolean if the tile is plowed
     * @return true if it is plowed, otherwise, false
     */
    public boolean isPlowed() {
        return isPlowed;
    }

    /**
     * Acts as the getter for the boolean if the tile is withered
     * @return true if it is withered, otherwise, false
     */
    public boolean isWithered() {
        return isWithered;
    }

    /**
     * Acts as the getter for the boolean if the tile has seed
     * @return true if it has seed, otherwise, false
     */
    public boolean hasSeed() {
        return hasSeed;
    }

    /**
     * Acts as the getter for the boolean if the tile has rock
     * @return true if it has rock, otherwise, false
     */
    public boolean hasRock() {
        return hasRock;
    }

    /**
     * Acts as the getter for the boolean if the tile is harvestable
     * @return true if it is harvestable, otherwise, false
     */
    public boolean isHarvestable() {
        return isHarvestable;
    }

    /**
     * Acts as the getter for the number of times the tile is watered
     * @return the number of times the tile is watered
     */
    public int getTimesWatered() {
        return timesWatered;
    }

    /**
     * Acts as the getter for the number of times the tile is fertilized
     * @return the number of times the tile is fertilized
     */
    public int getTimesFertilized() {
        return timesFertilized;
    }

    /**
     * Acts as the getter for the boolean if the tile is selected
     * @return true if it is selected, otherwise, false
     */
    public boolean isSelected(){return isSelected;}

    /**
     * Acts as the getter for the name of the crop planted on the tile if it exists
     * @return the crop's name
     */
    public String getCrop() {
        return crop;
    }

    /**
     * Acts as the getter for the number of times the player has slept
     * @return the number of days passed
     */
    public int getDaysPassed() {
        return daysPassed;
    }

    /**
     * Acts as the getter for the current farmer object
     * @return the current farmer object
     */
    public Farmer getFarmer() {
        return farmer;
    }

    /**
     * Acts as the getter for the current seeds object
     * @return the current seeds object
     */
    public Seeds getSeeds() {
        return seeds;
    }

    /**
     * Acts as the setter for the amount of times the tile is watered
     * @param timesWatered number of times the tile is watered
     */
    public void setTimesWatered(int timesWatered) {
        this.timesWatered = timesWatered;
    }

    /**
     * Acts as the setter for indicating if the tile is harvestable or not
     * @param isHarvestable true if it is harvestable, otherwise, false
     */
    public void setHarvestable(boolean isHarvestable) {
        this.isHarvestable = isHarvestable;
    }

    /**
     * Acts as the setter for the amount of times the tile is fertilized
     * @param timesFertilized number of times the tile is fertilized
     */
    public void setTimesFertilized(int timesFertilized) {
        this.timesFertilized = timesFertilized;
    }

    /**
     * Acts as the setter for indicating if the tile has a seed or not
     * @param hasSeed true if it has seed, otherwise, false
     */
    public void setHasSeed(boolean hasSeed) {
        this.hasSeed = hasSeed;
    }

    /**
     * Acts as the setter for the amount of days that has passed
     * @param daysPassed number of times days has passed in the game
     */
    public void setDaysPassed(int daysPassed) {
        this.daysPassed = daysPassed;
    }

    /**
     * Acts as the setter for indicating if the tile is plowed or not
     * @param plowed true if it is plowed, otherwise, false
     */
    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
    }

    /**
     * Acts as the setter for indicating if the tile has a rock or not
     * @param hasRock true if it has a rock, otherwise, false
     */
    public void setRock(boolean hasRock) {
        this.hasRock = hasRock;
    }

    /**
     * Acts as the setter for indicating if the tile is withered or not
     * @param withered true if it is withered, otherwise, false
     */
    public void setWithered(boolean withered) {
        isWithered = withered;
    }

    /**
     * Acts as the setter for indicating if the tile is selected or not
     * @param selected true if it is selected, otherwise, false
     */
    public void setSelected(boolean selected) { isSelected = selected; }

    /**
     * Acts as the setter for the planted crop's name
     * @param crop the String name of the crop planted
     */
    public void setCrop(String crop) {
        this.crop = crop;
    }

    /**
     * Acts as the setter for the seed object
     * @param seeds the value that will be set as the new value of the seed object
     */
    public void setSeeds(Seeds seeds) {
        this.seeds = seeds;
    }

    /**
     * Acts as the setter for the farmer object
     * @param farmer the value that will be set as the new value of the farmer object
     */
    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
