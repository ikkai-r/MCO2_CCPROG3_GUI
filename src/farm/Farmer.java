package farm;

import farmerprogress.FarmerLevel;

import java.util.Scanner;

public class Farmer implements GeneralMethods {

    private Inventory farmerInventory;
    private Scanner scanner = new Scanner(System.in);
    private String farmerName = null;
    private int farmerAge;
    private int farmerLevel = 0;
    private static double experience = 0D;
    private String farmerStatus = "FARMER";
    private int dayCount = 1;
    private static int seedCostReduction = 0;
    private static int bonusEarningsPerProduce = 0;
    private static int waterBonusLimits = 0;
    private static int fertBonusLimits = 0;


    public Farmer(){}

    /**
     *
     * The method serves as a constructor for the name and age of the farmer.
     *
     * @param  farmerName  The provided username of the user.
     * @param  farmerAge   The provided age of the user.
     */
    public Farmer(String farmerName, int farmerAge){
        this.farmerName = farmerName;
        this.farmerAge = farmerAge;
        this.farmerInventory = new Inventory();
        farmerInventory.setObjectCoins(100);
    }

    public int returnRandom(int max, int min) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */
    public String getFarmerName() {
        return farmerName;
    }

    public int getFarmerLevel() {
        return farmerLevel;
    }

    public double getExperience() {
        return experience;
    }

    public int getDayCount() {
        return dayCount;
    }

    public Inventory getFarmerInventory() {
        return farmerInventory;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public int getSeedCostReduction() {
        return seedCostReduction;
    }

    public int getBonusEarningsPerProduce() {
        return bonusEarningsPerProduce;
    }

    public int getWaterBonusLimits() {
        return waterBonusLimits;
    }

    public int getFertBonusLimits() {
        return fertBonusLimits;
    }


    public void setFarmerInventory(Inventory farmerInventory) {
        this.farmerInventory = farmerInventory;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public int getFarmerAge() {
        return farmerAge;
    }

    public void setFarmerAge(int farmerAge) {
        this.farmerAge = farmerAge;
    }

    public String getFarmerStatus() {
        return farmerStatus;
    }

    public void setFarmerStatus(String farmerStatus) {
        this.farmerStatus = farmerStatus;
    }

    public static void setSeedCostReduction(int seedCostReduction) {
        Farmer.seedCostReduction = seedCostReduction;
    }

    public static void setBonusEarningsPerProduce(int bonusEarningsPerProduce) {
        Farmer.bonusEarningsPerProduce = bonusEarningsPerProduce;
    }

    public static void setWaterBonusLimits(int waterBonusLimits) {
        Farmer.waterBonusLimits = waterBonusLimits;
    }

    public static void setFertBonusLimits(int fertBonusLimits) {
        Farmer.fertBonusLimits = fertBonusLimits;
    }

    public void setFarmerLevel(int farmerLevel) {
        this.farmerLevel = farmerLevel;
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
        this.farmerInventory.setObjectCoins(farmerInventory.getObjectCoins()-farmerLevel.getRegistrationFee());
    }

    /**
     *
     * The method guides the user to what the different states the tile can be in.
     */
    public void displayLegend() {
        System.out.printf("%15s\n", "== LEGEND ==");
        System.out.println("X - Unplowed");
        System.out.println("_ - Plowed");
        System.out.println(". - Seed");
        System.out.println("V - Harvestable");
        System.out.println("x - Withered");
    }

    /**
     *
     * The method increases the seeds that the player has by how much they bought.
     *
     * @param seeds  holds the info of the plants.
     * @param plantChoice the user's seed choice to plant
     * @param plantSeeds the number of seeds the farmer has bought.
     */
    public void addItemToInventory(Seeds seeds, int plantSeeds, int plantChoice) {
        String plantName = seeds.getPlants().get(plantChoice-1).getPlantName();

        if (farmerInventory.getSeedsOwned().containsKey(plantName)) {
            int currentNoOfSeeds = farmerInventory.getSeedsOwned().get(plantName);
            farmerInventory.getSeedsOwned().replace(plantName, currentNoOfSeeds+plantSeeds);
        } else {
            farmerInventory.getSeedsOwned().put(plantName, plantSeeds);
        }
    }

    /**
     *
     * The method outputs the stats of the farmer.
     *
     */
    public void displayFarmerDetails() {
        System.out.println("\nFarmer Name: " + this.farmerName);
        System.out.println("Farmer Age: " + this.farmerAge);
        System.out.println("Farmer Status: " + this.farmerStatus);
        System.out.println("Farmer Level: " + this.farmerLevel);
        System.out.printf("Farmer Experience: %.2f", Farmer.experience);
        System.out.printf("\nObject Coins: %.2f\n", this.farmerInventory.getObjectCoins());
    }

    /**
     *
     * The method lets the player pick the seeds that they want to buy and by how much.
     *
     * @param store holds the seeds that the user can buy.
     *
     * @return plantChoice[] - the indexes of the plant and how much the user bought.
     */
    public int[] buySeeds(Store store) {

        int[] plantChoice = new int[2];
        //1st element is the plant to purchase
        //2nd element is the plant seeds
        plantChoice[0] = -1;
        plantChoice[1] = -1;

        //loops until the user inputs a valid integer
        while(plantChoice[0] < 0 || plantChoice[0] > store.getProducts().getPlants().size() + 1) {

            System.out.println("Pick what seed you want to buy:");

            try {
                plantChoice[0] = scanner.nextInt();
            }
            catch (Exception e) {
                //catches user input that is not an integer
                scanner.nextLine();
                System.out.println("Only input valid integers. Please try again.");
                buySeeds(store);
            }

            if (plantChoice[0] < 0 || plantChoice[0] > store.getProducts().getPlants().size() + 1) {
                System.out.println("Enter a valid seed number only.");
            }
        }

        //if user picks the cancel option from the store
        if (plantChoice[0] == store.getProducts().getPlants().size() + 1)
            return plantChoice;

        //gets the name of the seed that the user will buy
        String plantName = store.getProducts().getPlants().get(plantChoice[0]-1).getPlantName();

        while (plantChoice[1] < 0) {

            System.out.println("How many seeds of " + plantName + " do you want to purchase?");

            try {
                plantChoice[1] = scanner.nextInt();
            }
            catch (Exception e) {
                scanner.nextLine();
                System.out.println("Only input valid integers. Please try again.");
            }

            if (plantChoice[1] < 0) {
                System.out.println("Enter a valid amount of seeds.");
            }

        }

        return plantChoice;

    }

    /**
     *
     * The method lets the player pick the tile they want to perform the crop actions to.
     *
     *
     *
     * @return cropTileSpecs - the row and column of the selected tile
     */
    public int[] chooseCropToCheck() {
        int[] cropTileSpecs = new int[2];

        //cropTileRow
        cropTileSpecs[0] = -1;
        //cropTileCol
        cropTileSpecs[1] = -1;

        System.out.println("\nSelect crop tile to work on: ");

        while(cropTileSpecs[0] < 1 || cropTileSpecs[0] > 10) {
            System.out.println("\nInput crop tile ROW: ");

            try {
                cropTileSpecs[0] = scanner.nextInt();
            }
            catch (Exception e) {
                scanner.nextLine();
                System.out.println("Only input valid integers. Please try again.");
            }

            if (cropTileSpecs[0] < 1 || cropTileSpecs[0] > 10) {
                System.out.println("Enter valid crop row number.");
            }
        }


        while(cropTileSpecs[1] < 1 || cropTileSpecs[1] > 5){
            System.out.println("Input crop tile COLUMN: ");
            try {
                cropTileSpecs[1] = scanner.nextInt();
            }
            catch (Exception e) {
                scanner.nextLine();
                System.out.println("Only input valid integers. Please try again.");
            }

            if (cropTileSpecs[1] < 1 || cropTileSpecs[1] > 5) {
                System.out.println("Enter valid crop column number.");
            }
        }

        cropTileSpecs[0]--;
        cropTileSpecs[1]--;

        return cropTileSpecs;
    }

    /**
     *
     * The method lets the player pick the seeds that they want to buy and by how much.
     *
     * @param tile holds the info of the tile picked.
     * @param seeds serves as the reference of data for each of the plant's statistics.
     * @param board holds the info of the tiles
     *
     * @return playerChoice - the integer the user picked.
     */
    public int tendToCrop(Tile tile, Board board, Seeds seeds) {

        tile.setSelected(true);

        int playerChoice = -1;

        while(playerChoice < 1 || playerChoice > 4) {
            System.out.println("\nInput desired number to enable action on selected tile:");
            System.out.println("1: Use Tools");
            System.out.println("2: Plant Seeds");
            System.out.println("3: Harvest Crop");
            System.out.println("4: Back to Actions");

            try {
                playerChoice = scanner.nextInt();
            }
            catch (Exception e) {
                scanner.nextLine();
                System.out.println("Only input valid integers. Please try again.");
            }

            switch (playerChoice) {
                case 1 -> useTools(tile);
                case 2 -> plantSeeds(tile, seeds, board);
                case 3 -> harvestCrop(tile, seeds);
                case 4 -> {}
                default -> System.out.println("Please enter a number from 1 to 4 only.");
            }
        }

        return playerChoice;

    }

    /**
     *
     * Lets the user interact with the tools provided to a tile.
     *
     * @param crop holds the info of the tile picked.
     */
    public void useTools(Tile crop) {

        int counter = 1;
        int toolChoice = -1;

        System.out.printf("%-15s %10s\n", "Tool", "Cost of Usage");
        for (Tools tool : farmerInventory.getTools()) {
            System.out.printf("%d: %-15s %10d\n", counter, tool.getToolName(),  tool.getCostOfUsage());
            counter++;
        }

        while (toolChoice < 0 || toolChoice > (counter-2)) {

            System.out.println("Pick which tool you want to use:");

            try {
                toolChoice = (scanner.nextInt())-1;
            }
            catch (Exception e) {
                scanner.nextLine();
                System.out.println("Only input valid integers. Please try again.");
            }

            if (toolChoice < 0 || toolChoice > (counter-2)) {
                System.out.println("Enter a valid tool number only.");
            }

        }

        String toolName = farmerInventory.getTools().get(toolChoice).getToolName();

        if (farmerInventory.checkValidTool(toolName, crop)) {
            //use tool if valid
            if (farmerInventory.getObjectCoins() >= farmerInventory.getTools().get(toolChoice).getCostOfUsage()) {
                setExperience(getExperience()+farmerInventory.useTool(toolName, crop));
            } else {
                System.out.println("Insufficient funds to use " + toolName + ".");
            }
        }


    }

    /**
     *
     * The method lets the player consume the seeds from their inventory by planting it in a tile.
     *
     * @param tile holds the info of the tile picked.
     * @param seeds serves as the reference of data for each of the plant's statistics.
     * @param board holds the info of the tiles
     *
     */
    public void plantSeeds(Tile tile, Seeds seeds, Board board) {
        int counter = 1;
        int plantChoice = -1;

        //show seeds
        if (!farmerInventory.getSeedsOwned().isEmpty()) {

            System.out.printf("%-10s %10s\n", "Seed", "Number of Seeds");
            for (String seed : farmerInventory.getSeedsOwned().keySet()) {
                System.out.printf("%d: %-10s %5d\n", counter, seed, farmerInventory.getSeedsOwned().get(seed));
                counter++;
            }

            //check if you can plant
            if (tile.canPlant()) {

                //convert hashmap to array then get index
                String[] seedsOwnedArr = farmerInventory.getSeedsOwned().keySet().toArray(new String[0]);

                while(plantChoice < 0 || plantChoice >= seedsOwnedArr.length) {

                    System.out.println("Pick which seed you want to plant:");

                    try {
                        plantChoice = scanner.nextInt()-1;
                    }
                    catch (Exception e) {
                        scanner.nextLine();
                        System.out.println("Only input valid integers. Please try again.");
                    }

                }

                String plantName = seedsOwnedArr[plantChoice];

                //plant seed
                if (!((seeds.getPlants().get(seeds.getCropIndex(plantName)).getPlantType()).equals("Fruit Tree"))) {

                    tile.setCrop(plantName);
                    tile.setHasSeed(true);
                    System.out.println("Successfully planted " + plantName + "!");

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
                        System.out.println("Successfully planted " + plantName + "!");

                        if (farmerInventory.getSeedsOwned().get(plantName)-1 == 0) {
                            farmerInventory.getSeedsOwned().remove(plantName);
                        } else {
                            farmerInventory.getSeedsOwned().replace(plantName, (farmerInventory.getSeedsOwned().get(plantName)-1));
                        }

                    } else {
                        System.out.println("Surrounding tiles is occupied. Please find another tile to plant the " + plantName);
                        System.out.println("To plant a fruit tree crop, no crops/rocks should occupy the nearest tiles surrounding it.");
                        System.out.println("Also, it cannot be planted on the sides of the field.");
                    }
                }

            } else {
                System.out.println("You cannot plant in this tile because it is occupied/is unplowed/has a rock.");
            }
        } else {
            System.out.println("No owned seeds to plant.");
        }

    }

    /**
     *
     * The method lets the player harvest a seed from a specific tile.
     *
     * @param tile holds the info of the tile picked.
     * @param seeds serves as the reference of data for each of the plant's statistics.
     *
     */
    public void harvestCrop(Tile tile, Seeds seeds) {

        if (tile.isHarvestable()) {
            int cropIndex = seeds.getCropIndex(tile.getCrop());
            Plants crop = seeds.getPlants().get(cropIndex);

            //products produced
            int productsProduced = returnRandom(crop.getMaxProductsProduced(), crop.getMinProductsProduced());

            //compute
            double harvestTotal = productsProduced*(crop.getBaseCost() + bonusEarningsPerProduce);
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

            System.out.println("Successfully harvested " + productsProduced + " " + crop.getPlantName() + "!");
            System.out.println("Successfully earned " + finalHarvestPrice + " object coins!");
            System.out.println("Successfully gained " + (crop.getExperienceYield()*productsProduced) + " XP!");

            tile.resetTile(tile);

        } else {
            System.out.println("Tile is not for harvesting.");
        }
    }


}