
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

    private static Farmer farmer;
    private Store store;
    private Seeds seeds;
    private Scanner scanner = new Scanner(System.in);
    private static Board board;
    private ProgressChecker progressChecker;


    public FarmingGame() {
        initializeItems();
        farmer = new Farmer();
        board = new Board();
    }

    /**
     *
     * The method starts the game by initializing the necessities and continues the day-to-day process of the user.
     *
     */
//    public void startSimulator() {
//
//        //intro
//        initializeItems();
//        inputFarmerDetails();
//        playIntroSequence();
//
//        //gameplay (advances day by day)
//        do {
//            displayDetails();
//            showActions();
//        } while (!isGameOver());
//
//        //outro
//        playOutroSequence();
//    }

    public void startSimulator() {
        do {
            displayDetails();
            showActions();
        } while (!isGameOver());
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
                case 3 -> { displayLand();
                    promptCropChecking(); }
                case 4 -> sleep();
                default -> System.out.println("Invalid input. Try again.");
            }
        }

    }

    /**
     *
     * serves as an initializer for every start of the game.
     */
    public void initializeItems() {
        seeds = new Seeds();
        store = new Store();
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
     * The method advances the farmer to the next day.
     */
    public void sleep() {

        farmer.setDayCount(farmer.getDayCount()+1);
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