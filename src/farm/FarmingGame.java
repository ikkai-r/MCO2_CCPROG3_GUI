
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
    private static Board board;
    private ProgressChecker progressChecker;


    public FarmingGame() {
            initializeItems();
            board = new Board();
            farmer = new Farmer();
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