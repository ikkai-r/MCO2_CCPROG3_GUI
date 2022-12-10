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

    public boolean isPlowed() {
        return isPlowed;
    }

    public boolean isWithered() {
        return isWithered;
    }

    public boolean hasSeed() {
        return hasSeed;
    }

    public boolean hasRock() {
        return hasRock;
    }

    public void setHarvestable(boolean isHarvestable) {
        this.isHarvestable = isHarvestable;
    }

    public boolean isHarvestable() {
        return isHarvestable;
    }

    public int getTimesWatered() {
        return timesWatered;
    }

    public void setTimesWatered(int timesWatered) {
        this.timesWatered = timesWatered;
    }

    public int getTimesFertilized() {
        return timesFertilized;
    }

    public void setTimesFertilized(int timesFertilized) {
        this.timesFertilized = timesFertilized;
    }

    public void setHasSeed(boolean hasSeed) {
        this.hasSeed = hasSeed;
    }

    public void setDaysPassed(int daysPassed) {
        this.daysPassed = daysPassed;
    }

    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
    }

    public void setRock(boolean hasRock) {
        this.hasRock = hasRock;
    }

    public void setWithered(boolean withered) {
        isWithered = withered;
    }

    public void setSelected(boolean selected) { isSelected = selected; }

    public boolean isSelected(){return isSelected;}

    public String getCrop() {
        return crop;
    }

    public int getDaysPassed() {
        return daysPassed;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public Seeds getSeeds() {
        return seeds;
    }

    public void setSeeds(Seeds seeds) {
        this.seeds = seeds;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
