
//        This is to certify that this project is my own work, based on my personal efforts in studying and applying
//        the concepts learned. I have constructed the functions and their respective algorithms and corresponding
//        code by myself. The program was run, tested, and debugged by my own efforts. I further certify that
//        I have not copied in part or whole or otherwise plagiarized the work of other students and/or persons.
//
//        Rica Mae Sales
//        12197378
//


package farm;

import farmerprogress.ProgressChecker;

public class FarmingGame {

    private Farmer farmer;
    private Store store;
    private Seeds seeds;
    private static Board board;
    private ProgressChecker progressChecker;

    /**
     *
     * Initializes the items and necessary objects
     */
    public FarmingGame() {
            initializeItems();
    }

    /**
     *
     * serves as an initializer for every start of the game.
     */
    public void initializeItems() {
        seeds = new Seeds();
        store = new Store();
        progressChecker = new ProgressChecker();
        board = new Board();
        farmer = new Farmer();
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
     *         and your Objectcoins is less than the cheapest seed or if you do not have any
     *         money to remove withered plants with all your tiles withered.
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