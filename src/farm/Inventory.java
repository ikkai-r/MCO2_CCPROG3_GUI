package farm;

import farm.tools.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private HashMap<String, Integer> seedsOwned = new HashMap<String, Integer>();

    private ArrayList<Tools> tools = new ArrayList<>();
    private static double objectCoins;

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

    /**
     *
     * enables user to use the selected tool
     *
     * @param toolName the tool that is used
     * @param tile     the tile that's being interacted with the tool.
     *
     * @return the experience gained by the user.
     */
    public double useTool (String toolName, Tile tile) {

        if (toolName.equals(tools.get(0).getToolName())) {

            //plow
            tile.setPlowed(true);
            System.out.println("Successfully plowed tile!");
            System.out.println("Gained " + tools.get(0).getExperienceGainedOnUse() + " XP!");

            return tools.get(0).getExperienceGainedOnUse();

        } else if (toolName.equals(tools.get(1).getToolName())) {

            //watering can
            if (tile.canWater()) {
                tile.setTimesWatered(tile.getTimesWatered()+1);
            }

            System.out.println("Successfully watered crop!");
            System.out.println("Gained " + tools.get(1).getExperienceGainedOnUse() + " XP!");

            return tools.get(1).getExperienceGainedOnUse();

        } else if (toolName.equals(tools.get(2).getToolName())) {

            //fertilizer
            if (tile.canFertilize()) {
                tile.setTimesFertilized(tile.getTimesFertilized()+1);
            }

            setObjectCoins(getObjectCoins()-tools.get(2).getCostOfUsage());

            System.out.println("Successfully fertilized crop!");
            System.out.println("Gained " + tools.get(2).getExperienceGainedOnUse() + " XP!");
            return tools.get(2).getExperienceGainedOnUse();

        } else if (toolName.equals(tools.get(3).getToolName())) {

            //pickaxe
            tile.setRock(false);

            setObjectCoins(getObjectCoins()-tools.get(3).getCostOfUsage());
            System.out.println("Successfully destroyed rock!");
            System.out.println("Gained " + tools.get(3).getExperienceGainedOnUse() + " XP!");

            return tools.get(3).getExperienceGainedOnUse();

        } else if (toolName.equals(tools.get(4).getToolName())) {

            setObjectCoins(getObjectCoins()-tools.get(4).getCostOfUsage());

            if (tile.isWithered()) {

                System.out.println("Successfully removed withered crop!");
                System.out.println("Gained " + tools.get(4).getExperienceGainedOnUse() + " XP!");
                tile.resetTile(tile);

                return tools.get(4).getExperienceGainedOnUse();

            } else {

                System.out.println("Successfully used shovel!");
                tile.resetTile(tile);

                return 0.0;
            }

        }

        return 0.0;

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
            } else {
                System.out.println("Cannot be used on a plowed/withered/rock tile.");
            }

        } else if (toolName.equals(tools.get(1).getToolName())) {

            //watering can
            if (tile.hasSeed()) {

                return true;

            } else {

                System.out.println("Cannot be used on a tile without seed.");

            }

        } else if (toolName.equals(tools.get(2).getToolName())) {

            //fertilizer
            if (tile.hasSeed()) {
                return true;
            } else {
                System.out.println("Cannot be used on a tile without seed.");
            }

        } else if (toolName.equals(tools.get(3).getToolName())) {

            //pickaxe
            if (tile.hasRock()) {
                return true;
            } else {
                System.out.println("Cannot be used on a tile without a rock.");
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