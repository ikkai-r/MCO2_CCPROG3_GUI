package farm;

import farmerprogress.FarmerLevel;

import java.util.ArrayList;

public class Farmer implements GeneralMethods {

    private Inventory farmerInventory = new Inventory();
    private static String farmerCharacter;
    private static String farmerName;
    private int farmerLevel;
    private static double experience;
    private String farmerStatus;
    private int dayCount;
    private static int seedCostReduction;
    private static int bonusEarningsPerProduce;
    private static int waterBonusLimits;
    private static int fertBonusLimits;

    /**
     * Sets the farmer variables to the default values
     */
    public Farmer() {
        getFarmerInventory().resetInventory();
        setFarmerCharacter(null);
        setFarmerStatus("FARMER");
        setFarmerName(null);
        setSeedCostReduction(0);
        setBonusEarningsPerProduce(0);
        setWaterBonusLimits(0);
        setFertBonusLimits(0);
        setDayCount(1);
        setExperience(0);
        setFarmerLevel(0);
    }

    /**
     *
     * The method serves as the initializer when the farmer decides to earn their prestige.
     *
     * @param  farmerLevel holds the stats of the farmer.
     */
    public void changeRegistration(FarmerLevel farmerLevel) {
        setFarmerStatus(farmerLevel.name());
        setBonusEarningsPerProduce(farmerLevel.getBonusEarnings());
        setSeedCostReduction(farmerLevel.getSeedCostReduction());
        setWaterBonusLimits(farmerLevel.getWaterBonus());
        setFertBonusLimits(farmerLevel.getFertBonus());
        farmerInventory.setObjectCoins(farmerInventory.getObjectCoins()-farmerLevel.getRegistrationFee());
    }

    /**
     *
     * The method increases the seeds that the player has by how much they bought
     * and adds the seed name to their owned seeds.
     *
     * @param seeds  holds the info of the plants.
     * @param plantSeeds the number of seeds the farmer has bought.
     * @param plantChoice the user's seed choice to plant
     */
    public void addItemToInventory(Seeds seeds, int plantSeeds, int plantChoice) {
        String plantName = seeds.getPlants().get(plantChoice).getPlantName();

        if (farmerInventory.getSeedsOwned().containsKey(plantName)) {
            int currentNoOfSeeds = farmerInventory.getSeedsOwned().get(plantName);
            farmerInventory.getSeedsOwned().replace(plantName, currentNoOfSeeds+plantSeeds);
        } else {
            farmerInventory.getSeedsOwned().put(plantName, plantSeeds);
        }
    }

    /**
     *
     * Lets the user interact with the tools provided to a tile.
     *
     * @param crop holds the info of the tile picked.
     * @param toolChoice holds the index of the tool the user picked.
     *
     * @return arraylist of Strings that are feedback from the tool action
     */
    public ArrayList<String> useTools(Tile crop, int toolChoice) {
        ArrayList<String> feedback = new ArrayList<>();
        String toolName = farmerInventory.getTools().get(toolChoice).getToolName();

        if (farmerInventory.checkValidTool(toolName, crop)) {
            //use tool if valid
            if (farmerInventory.getObjectCoins() >= farmerInventory.getTools().get(toolChoice).getCostOfUsage()) {
                ArrayList<Object> returnTool = farmerInventory.useTool(toolName, crop);
                setExperience(getExperience()+ (Double) returnTool.get(2));
                feedback.add((String)returnTool.get(0));
                feedback.add((String)returnTool.get(1));
            } else {
                feedback.add("Insufficient funds to use.");
            }
        } else {
            feedback.add("Invalid use of tool.");
        }

        return feedback;

    }

    /**
     *
     * The method lets the player consume the seeds from their inventory by planting it in a tile.
     *
     * @param tile holds the info of the tile picked.
     * @param seeds serves as the reference of data for each of the plant's statistics.
     * @param board holds the info of the tiles
     * @param plantName holds the name of the seed to be planted
     *
     * @return feedback of the user's action of planting a seed
     *
     */
    public String plantSeeds(Tile tile, Seeds seeds, Board board, String plantName) {

        String feedback;

        if (!farmerInventory.getSeedsOwned().isEmpty()) {

            //check if you can plant
            if (tile.canPlant()) {

                //plant seed
                if (!((seeds.getPlants().get(seeds.getCropIndex(plantName)).getPlantType()).equals("Fruit Tree"))) {

                    tile.setCrop(plantName);
                    tile.setHasSeed(true);
                    feedback = "Successfully planted " + plantName + "!";

                    if (farmerInventory.getSeedsOwned().get(plantName)-1 == 0) {
                        farmerInventory.getSeedsOwned().remove(plantName);
                    } else {
                        farmerInventory.getSeedsOwned().replace(plantName, (farmerInventory.getSeedsOwned().get(plantName)-1));
                    }

                } else {
                    //check if tile around is not occupied
                    if(!board.checkSurroundingTilesIfOcc()) {
                        //plant fruit tree
                        tile.setCrop(plantName);
                        tile.setHasSeed(true);
                        feedback = "Successfully planted " + plantName + "!";

                        if (farmerInventory.getSeedsOwned().get(plantName)-1 == 0) {
                            farmerInventory.getSeedsOwned().remove(plantName);
                        } else {
                            farmerInventory.getSeedsOwned().replace(plantName, (farmerInventory.getSeedsOwned().get(plantName)-1));
                        }

                    } else {
                        feedback = "Surrounding tiles is occupied.";
                    }
                }

            } else {
                feedback = "You cannot plant in this tile.";
            }
        } else {
            feedback = "No owned seeds to plant.";
        }

        return feedback;
    }

    /**
     *
     * The method lets the player harvest a seed from a specific tile.
     *
     * @param tile holds the info of the tile picked.
     * @param seeds serves as the reference of data for each of the plant's statistics.
     *
     * @return array list of Strings that are feedback from the harvest action
     */
    public ArrayList<String> harvestCrop(Tile tile, Seeds seeds) {

        ArrayList<String> cropFeedback = new ArrayList<>();

        if (tile.isHarvestable()) {
            int cropIndex = seeds.getCropIndex(tile.getCrop());
            Plants crop = seeds.getPlants().get(cropIndex);

            //products produced
            int productsProduced = returnRandom(crop.getMaxProductsProduced(), crop.getMinProductsProduced());

            //compute
            double harvestTotal = productsProduced*(crop.getBaseCost() + getBonusEarningsPerProduce());
            double waterBonus = harvestTotal*0.2*(tile.getTimesWatered()-1);
            double fertilizerBonus = harvestTotal*0.5*(tile.getTimesFertilized());

            double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

            if (crop.getPlantType().equals("Flower")) {
                finalHarvestPrice *= 1.1;
            }

            finalHarvestPrice = Math.round(finalHarvestPrice*100.0)/100.0;

            //set object coins
            farmerInventory.setObjectCoins(finalHarvestPrice+farmerInventory.getObjectCoins());

            //give experience
            setExperience(getExperience()+(crop.getExperienceYield()*productsProduced));

            cropFeedback.add("Successfully harvested " + productsProduced + " " + crop.getPlantName() + "!");
            cropFeedback.add("Successfully earned " + finalHarvestPrice + " object coins!");
            cropFeedback.add("Successfully gained " + (crop.getExperienceYield()*productsProduced) + " XP!");

            tile.resetTile(tile);

        } else {
            cropFeedback.add("Tile is not for harvesting.");
        }

        return cropFeedback;

    }

    /**
     *
     * The method generates an integer between the minimum and the maximum range.
     *
     * @param max the maximum range for the random value
     * @param min the minimum range for the random value
     *
     * @return a random integer.
     */
    @Override
    public int returnRandom(int max, int min) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */

    /**
     * Acts as the getter for the current farmer's name
     * @return the name of the farmer
     */
    public String getFarmerName() {
        return farmerName;
    }

    /**
     * Acts as the getter for the current farmer's level
     * @return the level of the farmer
     */
    public int getFarmerLevel() {
        return farmerLevel;
    }

    /**
     * Acts as the getter for the current farmer's experience
     * @return the numerical experience of the farmer
     */
    public static double getExperience() {
        return experience;
    }

    /**
     * Acts as the getter for the current day count
     * @return the amount of days passed
     */
    public int getDayCount() {
        return dayCount;
    }

    /**
     * Acts as the getter for the farmer's inventory
     * @return the farmer's inventory
     */
    public Inventory getFarmerInventory() {
        return farmerInventory;
    }

    /**
     * Acts as the setter for the current day count
     * @param dayCount  value of the passed days
     */
    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    /**
     * Acts as the setter for the experience of the farmer
     * @param experience value of the updated/new experience
     */
    public static void setExperience(double experience) {
        Farmer.experience = experience;
    }

    /**
     * Acts as the getter for the reduced seed cost of the farmer
     * caused by the farmer's registration
     * @return the value of the amount a seed cost will be reduced by
     */
    public static int getSeedCostReduction() {
        return seedCostReduction;
    }

    /**
     * Acts as the getter for the bonus earnings of the farmer
     * caused by the farmer's registration
     * @return the value of the amount of earnings per produce will be added by
     */
    public static int getBonusEarningsPerProduce() {
        return bonusEarningsPerProduce;
    }

    /**
     * Acts as the getter for the bonus limits of watering
     * caused by the farmer's registration
     * @return the value of the farmer's watering limit
     */
    public static int getWaterBonusLimits() {
        return waterBonusLimits;
    }

    /**
     * Acts as the getter for the bonus limits of fertilizer
     * caused by the farmer's registration
     * @return the value of the farmer's fertilizer limit
     */
    public static int getFertBonusLimits() {
        return fertBonusLimits;
    }

    /**
     * Acts as the setter for the farmer's inventory
     * @param farmerInventory the inventory of the farmer
     */
    public void setFarmerInventory(Inventory farmerInventory) {
        this.farmerInventory = farmerInventory;
    }

    /**
     * Acts as the setter for the current farmer's name
     * @param farmerName String value of the user's desired farmer name
     */
    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    /**
     * Acts as the getter for the farmer's character
     * @return "Boy" or "Girl", the value of the farmer's character picked
     */
    public String getFarmerCharacter() {
        return farmerCharacter;
    }

    /**
     * Acts as the setter for the farmer's character
     * @param farmerCharacter "Boy" or "Girl", the value of the farmer's character picked
     */
    public void setFarmerCharacter(String farmerCharacter) {
        this.farmerCharacter = farmerCharacter;
    }

    /**
     * Acts as the getter for the current farmer's status
     * @return the String value of the farmer status
     */
    public String getFarmerStatus() {
        return farmerStatus;
    }

    /**
     * Acts as the getter for the current farmer's status
     * @return the String value of the farmer status
     */
    public void setFarmerStatus(String farmerStatus) {
        this.farmerStatus = farmerStatus;
    }

    /**
     * Acts as the setter for the seed cost reduction for the farmer
     * based on their farmer registration
     * @param seedCostReduction amount of reduced cost for the seed
     */
    public static void setSeedCostReduction(int seedCostReduction) {
        Farmer.seedCostReduction = seedCostReduction;
    }

    /**
     * Acts as the setter for the bonus earnings for the farmer
     * based on their farmer registration
     * @param bonusEarningsPerProduce amount of bonus earnings per produce
     */
    public static void setBonusEarningsPerProduce(int bonusEarningsPerProduce) {
        Farmer.bonusEarningsPerProduce = bonusEarningsPerProduce;
    }

    /**
     * Acts as the setter for the water bonus limits for the farmer
     * based on their farmer registration
     * @param waterBonusLimits amount of water bonus
     */
    public static void setWaterBonusLimits(int waterBonusLimits) {
        Farmer.waterBonusLimits = waterBonusLimits;
    }

    /**
     * Acts as the setter for the fertilizer bonus limits for the farmer
     * based on their farmer registration
     * @param fertBonusLimits amount of fertilizer bonus
     */
    public static void setFertBonusLimits(int fertBonusLimits) {
        Farmer.fertBonusLimits = fertBonusLimits;
    }

    /**
     * Acts as the setter for the level of the farmer
     * @param farmerLevel new/updated value of the farmer's level
     */
    public void setFarmerLevel(int farmerLevel) {
        this.farmerLevel = farmerLevel;
    }


}
