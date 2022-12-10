package farm;

public class Board implements GeneralMethods {

    private final int BOARDROW = 10;
    private final int BOARDCOL = 5;
    private Tile[][] farmTiles = new Tile[BOARDROW][BOARDCOL];


    /**
     * Sets the board with unplowed tiles and calls setRandomRocks() to generate rock positions.
     *
     */
    public Board() {
            for(int outerCount = 0; outerCount < BOARDROW; outerCount++) {
                for (int innerCount = 0; innerCount < BOARDCOL; innerCount++) {
                    farmTiles[outerCount][innerCount] = new Tile();
                }
            }
            setRandomRocks();
    }

    /**
     *  Places a randomized set of rocks on the farm tiles.
     */
    public void setRandomRocks() {
        int numberOfRocks = returnRandom(30, 10);
        int rockRow =  returnRandom(BOARDROW-1, 0);
        int rockCol = returnRandom(BOARDCOL-1, 0);

        for (int rockIndex = 0; rockIndex < numberOfRocks; rockIndex++) {

            while(farmTiles[rockRow][rockCol].hasRock()) {
                rockRow =  returnRandom(BOARDROW-1, 0);
                rockCol = returnRandom(BOARDCOL-1, 0);
            }

            farmTiles[rockRow][rockCol].setRock(true);
            rockRow =  returnRandom(BOARDROW-1, 0);
            rockCol = returnRandom(BOARDCOL-1, 0);

        }
    }

    /**
     * Checks the passed tile's state
     *
     * @param tile - The tile currently being checked by the board
     * @return the state of the tile in a String
     */
    public String checkTileCondition(Tile tile) {
        if (tile.isWithered()) {
            return "withered";
        } else if (tile.isHarvestable()) {
            return "harvestable";
        } else if (tile.hasRock()) {
            return "rock";
        } else if (tile.hasSeed()){
            return "seed";
        } else if (tile.isPlowed()) {
            return "plowed";
        }  else {
            return "unplowed";
        }
    }

    /**
     *
     * Updates the status of the board
     */
    public void checkTiles() {

        for (int row = 0; row < BOARDROW; row++) {
            for (int col = 0; col < BOARDCOL; col++) {
                if (farmTiles[row][col].hasSeed()) {
                    farmTiles[row][col].setDaysPassed(farmTiles[row][col].getDaysPassed()+1);
                }
                farmTiles[row][col].checkWithered();
                farmTiles[row][col].checkHarvestable();
            }
        }

    }

    /**
     * Updates the tiles on the board if they have seeds.
     * @return true if at least one of the tiles has a seed
     */
    public boolean checkTilesSeeds() {
        boolean checkHasSeed = false;

        for (int row = 0; row < BOARDROW; row++) {
            for (int col = 0; col < BOARDCOL; col++) {
                if (farmTiles[row][col].hasSeed() && !(farmTiles[row][col].getCrop().isEmpty())) {
                    checkHasSeed = true;
                }
            }
        }
        return checkHasSeed;
    }


    /**
     * Updates the tiles on the board if they are withered
     * @return true if all the tiles are withered
     */
    public boolean checkTilesWithered() {
        boolean checkIsWithered = true;

        for (int row = 0; row < BOARDROW; row++) {
            for (int col = 0; col < BOARDCOL; col++) {
                if (!(farmTiles[row][col].isWithered())) {
                    checkIsWithered = false;
                }
            }
        }
        return checkIsWithered;
    }

    /**
     * Checks if it is possible for a fruit seed to be put into the tile
     * @return true if there are no occupied tiles in the area of the tile
     */
    public boolean checkSurroundingTilesIfOcc() {

        //check all sides
        int[] tileIndices = getSelectedTileIndices();

        if (tileIndices[0]-1 < 0 || tileIndices[0] + 1 > BOARDROW || tileIndices[1]-1 < 0 || tileIndices[1]+1 > BOARDCOL) {
            return true;
        }

        for (int rowCounter = tileIndices[0]-1; rowCounter <= tileIndices[0]+1; rowCounter++) {
            for (int colCounter = tileIndices[1]-1; colCounter <= tileIndices[1]+1; colCounter++) {
                if(farmTiles[rowCounter][colCounter].hasRock()
                        || farmTiles[rowCounter][colCounter].hasSeed()
                        || farmTiles[rowCounter][colCounter].isWithered()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Finds the selected tile in the farmtiles 2D array
     * @return an array of the values consisting the tile's row and tile's column
     */
    public int[] getSelectedTileIndices() {

        int[] tileIndices = new int[2];

        for (int rowIndex = 0; rowIndex < BOARDROW; rowIndex++) {
            for (int colIndex = 0; colIndex < BOARDCOL; colIndex++) {
                if (farmTiles[rowIndex][colIndex].isSelected()) {
                    tileIndices[0] = rowIndex;
                    tileIndices[1] = colIndex;
                }
            }
        }

        return tileIndices;
    }

    /**
     * Returns the selected tile using the getFarmTile() to access the tile from its indices
     * and the getSelectedTileIndices() to access the selected tile
     * @return the selected tile of the user
     */
    public Tile getSelectedTile() {
        int[] tileIndex;
        tileIndex = getSelectedTileIndices();

        return getFarmTile(tileIndex[0], tileIndex[1]);
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
     * Gets the value of the board row
     * @return the value of the board row
     */
    public int getBoardRow() {
        return BOARDROW;
    }

    /**
     * Gets the value of the board column
     * @return the value of the board column
     */
    public int getBoardCol() {
        return BOARDCOL;
    }

    /**
     * Gets the whole 2D array of farm tiles
     * @return 2D array of tiles
     */
    public Tile[][] getFarmTiles() {
        return farmTiles;
    }

    /**
     * Sets the farm tiles to the parameter given
     * @param farmTiles a 2D array that will be set to the farmTiles of the Board class
     */
    public void setFarmTiles(Tile[][] farmTiles) {
        this.farmTiles = farmTiles;
    }

    /**
     * Returns the farm tile in the 2D array with the corresponding parameters passed
     * @param tileRow the value of the tile's row to be accessed
     * @param tileCol the value of the tile's column to be accessed
     * @return the tile with the value of tileRow and tileCol
     */
    public Tile getFarmTile(int tileRow, int tileCol) {
        return farmTiles[tileRow][tileCol];
    }

}
