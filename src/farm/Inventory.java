package farm;

import farm.tools.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory {
    private static HashMap<String, Integer> seedsOwned = new HashMap<String, Integer>();

    private ArrayList<Tools> tools = new ArrayList<>();
    private static double objectCoins = 100;

    /**
     *
     * adds the available tools that are provided to the user for every game
     */
    public Inventory() {
        tools.add(new Plow());
        tools.add(new WateringCan());
        tools.add(new Fertilizer());
        tools.add(new Pickaxe());
        tools.add(new Shovel());
    }

    public void resetInventory() {
        seedsOwned.clear();
        objectCoins = 100;
    }

    /**
     *
     * enables user to use the selected tool
     *
     * @param toolName the tool that is used
     * @param tile     the tile that's being interacted with the tool.
     *
     * @return the experience gained by the user.
     */
    public ArrayList useTool (String toolName, Tile tile) {
        ArrayList<Object> toolReturn = new ArrayList<>();

        if (toolName.equals(tools.get(0).getToolName())) {

            //plow
            tile.setPlowed(true);

            toolReturn.add("Successfully plowed tile!");
            toolReturn.add("Gained " + tools.get(0).getExperienceGainedOnUse() + " XP!");
            toolReturn.add(tools.get(0).getExperienceGainedOnUse());

        } else if (toolName.equals(tools.get(1).getToolName())) {

            //watering can
            if (tile.canWater()) {
                tile.setTimesWatered(tile.getTimesWatered()+1);
            }

            toolReturn.add("Successfully watered crop!");
            toolReturn.add("Gained " + tools.get(1).getExperienceGainedOnUse() + " XP!");
            toolReturn.add(tools.get(1).getExperienceGainedOnUse());

        } else if (toolName.equals(tools.get(2).getToolName())) {

            //fertilizer
            if (tile.canFertilize()) {
                tile.setTimesFertilized(tile.getTimesFertilized()+1);
            }

            setObjectCoins(getObjectCoins()-tools.get(2).getCostOfUsage());

            toolReturn.add("Successfully fertilized crop!");
            toolReturn.add("Gained " + tools.get(2).getExperienceGainedOnUse() + " XP!");
            toolReturn.add(tools.get(2).getExperienceGainedOnUse());

        } else if (toolName.equals(tools.get(3).getToolName())) {

            //pickaxe
            tile.setRock(false);

            setObjectCoins(getObjectCoins()-tools.get(3).getCostOfUsage());
            toolReturn.add("Successfully destroyed rock!");
            toolReturn.add("Gained " + tools.get(3).getExperienceGainedOnUse() + " XP!");
            toolReturn.add(tools.get(3).getExperienceGainedOnUse());

        } else if (toolName.equals(tools.get(4).getToolName())) {

            setObjectCoins(getObjectCoins()-tools.get(4).getCostOfUsage());

            if (tile.isWithered()) {

                toolReturn.add("Successfully removed withered!");
                toolReturn.add("Gained " + tools.get(4).getExperienceGainedOnUse() + " XP!");
                tile.resetTile(tile);

                toolReturn.add(tools.get(4).getExperienceGainedOnUse());

            } else {

                toolReturn.add("Successfully used shovel!");
                toolReturn.add("Tile reverted to unplowed state.");
                tile.resetTile(tile);
                toolReturn.add(0.0);
            }

        }

        return toolReturn;

    }

    /**
     *
     * validates if the tool can be used by the user to the given tile.
     *
     * @param toolName a reference check to the actions that the tools can do.
     * @param tile     the tile that's being interacted with the tool.
     *
     * @return true if the tool can be used on the tile, and false if otherwise.
     */
    public boolean checkValidTool(String toolName, Tile tile) {

        if (toolName.equals(tools.get(0).getToolName())) {

            //plow
            if (!tile.isPlowed() && !tile.isWithered() && !tile.hasRock()) {
                return true;
            }

        } else if (toolName.equals(tools.get(1).getToolName())) {

            //watering can
            if (tile.hasSeed()) {

                return true;

            }

        } else if (toolName.equals(tools.get(2).getToolName())) {

            //fertilizer
            if (tile.hasSeed()) {
                return true;
            }

        } else if (toolName.equals(tools.get(3).getToolName())) {

            //pickaxe
            if (tile.hasRock()) {
                return true;
            }

        } else if (toolName.equals(tools.get(4).getToolName())) {

            return true;

        }

        return false;
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */

    public double getObjectCoins() {
        return objectCoins;
    }

    public void setObjectCoins(double objectCoins) {
        this.objectCoins = objectCoins;
    }

    public int getNumberOfSeedsOwned() {
        int totalSeeds = 0;

        for (Integer seedNum : seedsOwned.values()) {
            totalSeeds+=seedNum;
        }

        return totalSeeds;
    }

    public void setSeedsOwned(HashMap<String, Integer> seedsOwned) {
        this.seedsOwned = seedsOwned;
    }

    public void setTools(ArrayList<Tools> tools) {
        this.tools = tools;
    }

    public ArrayList<Tools> getTools() {
        return tools;
    }

    public HashMap<String, Integer> getSeedsOwned() { return seedsOwned; }

}