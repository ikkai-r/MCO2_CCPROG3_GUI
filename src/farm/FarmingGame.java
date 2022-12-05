
//        This is to certify that this project is our own work, based on our personal efforts in studying and applying
//        the concepts learned. We have constructed the functions and their respective algorithms and corresponding
//        code by ourselves. The program was run, tested, and debugged by our own efforts. We further certify that
//        we have not copied in part or whole or otherwise plagiarized the work of other students and/or persons.
//
//        Rica Mae Sales
//        12197378
//


package farm;

import farmerprogress.ProgressChecker;

import java.util.Scanner;

public class FarmingGame {

    private Farmer farmer;
    private Store store;
    private Seeds seeds;
    private Scanner scanner = new Scanner(System.in);
    private Board board;
    private ProgressChecker progressChecker;


    public FarmingGame() {
        initializeItems();
        farmer = new Farmer();
    }

    /**
     *
     * The method starts the game by initializing the necessities and continues the day-to-day process of the user.
     *
     */
    public void startSimulator() {

        //intro
        initializeItems();
        inputFarmerDetails();
        playIntroSequence();

        //gameplay (advances day by day)
        do {
            displayDetails();
            showActions();
        } while (!isGameOver());

        //outro
        playOutroSequence();
    }

    /**
     *
     * The method lets the player choose if they want to start another game or exit the program.
     *
     */
    public void playOutroSequence() {
        int playerChoice = -1;

        System.out.println("\nGame is over!");

        while (playerChoice < 0 || playerChoice > 2) {

            System.out.println("Input the number of your desired action: ");
            System.out.println("1: Play again");
            System.out.println("2: Exit game");

            try {
                playerChoice = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Only input valid integers. Please try again.");
                playOutroSequence();
            }

            switch (playerChoice) {
                case 1 -> startSimulator();
                case 2 -> System.exit(0);
                default -> {
                    System.out.println("Invalid input. Try again.");
                }
            }
        }
    }

    /**
     *
     * Displays the farm
     */
    public void displayDetails() {
        displayDay();
        displayLand();
    }

    /**
     *
     * Displays the day
     */
    public void displayDay() {
        System.out.println();
        System.out.println("Day " + farmer.getDayCount());
    }

    /**
     *
     * Displays the farm of the user
     */
    public void displayLand() {
        System.out.println();
        System.out.println(farmer.getFarmerName() + "'s Field");
        board.displayLand();
    }

    /**
     *
     * Displays the actions that the user can do and executes a specific action that the player wants.
     */
    public void showActions() {

        int playerChoice = -1;

        while (playerChoice < 0 || playerChoice > 4) {

            System.out.println("\nInput desired number to enable action:");
            System.out.println("1: Open Inventory");
            System.out.println("2: Open Shop");
            System.out.println("3: Check Crops");
            System.out.println("4: Go to Sleep");

            try {
                playerChoice = scanner.nextInt();
            }
            catch (Exception e) {
                scanner.nextLine();
                System.out.println("Only input valid integers. Please try again.");
                showActions();
            }

            switch (playerChoice) {
                case 1 -> openInventory();
                case 2 -> openStore();
                case 3 -> { displayLand();
                    promptCropChecking(); }
                case 4 -> sleep();
                default -> System.out.println("Invalid input. Try again.");
            }
        }

    }

    /**
     *
     * Displays the products available for the user to buy.
     */
    public void openStore() {
        int counter = 1;

        //displays the products
        System.out.printf("%18s\n", "== STORE ==");

        System.out.printf("%-13s %11s\n", "Seed", "Price");
        for (Plants plant: seeds.getPlants()) {
            System.out.printf("%d: %-10s %11.2f\n", counter, plant.getPlantName(), plant.getSeedCost()-farmer.getSeedCostReduction());
            counter++;
        }
        //adds a cancel option in the store in case the user doesn't want to buy
        System.out.printf("%d: %-10s\n", counter, "Cancel");

        System.out.println();
        System.out.printf("Objectcoins: %.2f\n\n", farmer.getFarmerInventory().getObjectCoins());
        storeAction();
    }

    /**
     *
     * Displays the actions that the user can do in the store.
     */
    public void storeAction() {

        int storeChoice = -1;

        while (storeChoice < 0 || storeChoice > 2) {

            System.out.printf("%19s\n", "== ACTIONS ==");
            System.out.println("1: Buy seeds");
            System.out.println("2: Back");

            try {
                storeChoice = scanner.nextInt();
            }
            catch (Exception e) {
                scanner.nextLine();
                System.out.println("Only input valid integers. Please try again.");
                storeAction();
            }

            switch (storeChoice) {
                case 1 -> promptStoreAction();
                case 2 -> showActions();
                default -> System.out.println("Invalid input. Try again.");
            }

        }
    }

    /**
     *
     * The method updates the inventory of the farmer upon buying the seed
     *
     */
    public void promptStoreAction() {
        int[] plantChoice = farmer.buySeeds(store);
        //if the player didn't cancel its transaction (last option)
        if (plantChoice[0] != store.getProducts().getPlants().size() + 1)
            store.sellItem(farmer, plantChoice[0], plantChoice[1]);
        storeAction();
    }

    /**
     *
     * serves as an initializer for every start of the game.
     */
    public void initializeItems() {
        seeds = new Seeds();
        store = new Store();
        board = new Board();
        progressChecker = new ProgressChecker();
    }

    /**
     *
     * The method checks the
     */
    public void promptCropChecking() {
        int[] cropTileSpecs = farmer.chooseCropToCheck();
        int choice = -1;

        do {
            choice = farmer.tendToCrop(board.getFarmTile(cropTileSpecs[0], cropTileSpecs[1]), board, seeds);
            progressChecker.checkExperience(farmer);
        } while (choice >= 1 && choice <= 3);

        board.getFarmTile(cropTileSpecs[0], cropTileSpecs[1]).setSelected(false);
    }

    /**
     *
     * Displays the farmer's inventory to the user
     * includes: farmer's details
     *           , seeds owned
     *           , tools owned
     */
    public void openInventory() {

        int playerChoice = -1;

        //displays the farmer's details
        farmer.displayFarmerDetails();

        System.out.println();

        //displays the seeds owned
        System.out.printf("%-10s %17s\n", "Seed", "Number of Seeds");
        for (String seed : farmer.getFarmerInventory().getSeedsOwned().keySet()) {
            System.out.printf("%-10s %9d\n", seed, farmer.getFarmerInventory().getSeedsOwned().get(seed));
        }

        System.out.println();
        System.out.println("Total Number of Seeds: " + farmer.getFarmerInventory().getNumberOfSeedsOwned());

        //displays the tools owned
        System.out.println();
        System.out.printf("%-15s %9s\n", "Tool", "Function");
        for (Tools tool : farmer.getFarmerInventory().getTools()) {
            System.out.printf("%-15s %10s\n", tool.getToolName(),  tool.getToolFunction());
        }

        System.out.println();

        while(playerChoice != 1) {

            System.out.println("1: Back to Actions");

            try {
                playerChoice = scanner.nextInt();
            }
            catch (Exception e) {
                scanner.nextLine();
                System.out.println("Only input valid integers. Please try again.");
            }

            if (playerChoice == 1) {
                showActions();
            } else {
                System.out.println("Invalid number.");
            }

        }

    }

    /**
     *
     * Introductory dialogues for the gameplay
     */
    public void playIntroSequence() {
        System.out.println();
        System.out.println("Farmer Tranz: Hello " + farmer.getFarmerName() + "!");
        System.out.println("Farmer Tranz: Since you're new around here, you deserve these items, good luck!");
        System.out.println("You received new items! Check your inventory!");
    }

    /**
     *
     * The method advances the farmer to the next day.
     */
    public void sleep() {

        farmer.setDayCount(farmer.getDayCount()+1);
        System.out.println("Farmer " + farmer.getFarmerName() + " slept for this day. Sweet dreams!");
        System.out.println("Good morning, Farmer " + farmer.getFarmerName() + "!");
        System.out.println();
        progressDay();

    }

    /**
     *
     * The method advances the crops to the next day.
     */
    public void progressDay() {
        board.checkTiles();
    }

    /**
     *
     * Gets user input for the farmer
     */
    public void inputFarmerDetails() {
        Scanner scanner = new Scanner(System.in);
        int farmerAge = -1;

        System.out.println("Welcome to Farming Sim!");
        System.out.println("What's your name?");
        String farmerName = scanner.nextLine();
        String farmerCharacter = scanner.nextLine();

        farmer = new Farmer(farmerName, farmerCharacter);
    }

    /**
     *
     * The method checks if the game has met its lose conditions.
     *
     * @return if you do not have seeds or if you have no seeds other than fruit trees
     *         and your Objectcoins is less than the cheapest seed.
     */
    public boolean isGameOver() {

        //1: if the seeds owned is 0 AND no seeds on field AND no funds to buy the smallest amount of seed
        //OR
        //2: if the inventory object coins is less than the amount to remove withered AND if all tiles are withered

        return ((farmer.getFarmerInventory().getNumberOfSeedsOwned() == 0) && !(board.checkTilesSeeds())
                && (farmer.getFarmerInventory().getObjectCoins() < 5))
                || (farmer.getFarmerInventory().getObjectCoins() < 7 && board.checkTilesWithered());
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */
    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Seeds getSeeds() {
        return seeds;
    }

    public void setSeeds(Seeds seeds) {
        this.seeds = seeds;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ProgressChecker getProgressChecker() {
        return progressChecker;
    }

    public void setProgressChecker(ProgressChecker progressChecker) {
        this.progressChecker = progressChecker;
    }
}