package farm;

public class Board implements GeneralMethods {

    private final static int BOARDROW = 10;
    private final static int BOARDCOL = 5;
    private static Tile[][] farmTiles = new Tile[BOARDROW][BOARDCOL];


    /**
     * sets the board with unplowed tiles and creates a randomized placings of rocks on the field.
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
     *
     * places a randomized number of rocks all over the land
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
     * updates the status of the board
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
     * updates the tiles on the board if they have seeds
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
     * updates the tiles on the board if they are withered
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
     * checks if it is possible for a fruit seed to be put into the tile
     * @return true if there are no occupied tiles in the area of the tile
     */
    public boolean checkSurroundingTilesIfOcc() {

        //check all sides
        int tileRowIndex = -1;
        int tileColIndex = -1;

        //find selected tile to get row and col index
        for (int rowIndex = 0; rowIndex < BOARDROW; rowIndex++) {
            for (int colIndex = 0; colIndex < BOARDCOL; colIndex++) {
                if (farmTiles[rowIndex][colIndex].isSelected()) {
                    tileRowIndex = rowIndex;
                    tileColIndex = colIndex;
                }
            }
        }

        if (tileRowIndex-1 < 0 || tileRowIndex + 1 > BOARDROW || tileColIndex-1 < 0 || tileColIndex+1 > BOARDCOL) {
            return true;
        }

        for (int rowCounter = tileRowIndex-1; rowCounter <= tileRowIndex+1; rowCounter++) {
            for (int colCounter = tileColIndex-1; colCounter <= tileColIndex+1; colCounter++) {
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
     *
     * The method generates an integer between the minimum and the maximum range.
     *
     * @return a random integer.
     */
    public int returnRandom(int max, int min) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */
    public int getBoardRow() {
        return BOARDROW;
    }

    public int getBoardCol() {
        return BOARDCOL;
    }

    public Tile[][] getFarmTiles() {
        return farmTiles;
    }

    public void setFarmTiles(Tile[][] farmTiles) {
        farmTiles = farmTiles;
    }

    public Tile getFarmTile(int tileRow, int tileCol) {
        return farmTiles[tileRow][tileCol];
    }

    public Tile getSelectedTile() {
        int tileRowIndex = -1;
        int tileColIndex = -1;

        for (int rowIndex = 0; rowIndex < BOARDROW; rowIndex++) {
            for (int colIndex = 0; colIndex < BOARDCOL; colIndex++) {
                if (farmTiles[rowIndex][colIndex].isSelected()) {
                    tileRowIndex = rowIndex;
                    tileColIndex = colIndex;
                }
            }
        }

        return getFarmTile(tileRowIndex, tileColIndex);
    }
}
