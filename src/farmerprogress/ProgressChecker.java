package farmerprogress;

import farm.Farmer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgressChecker {

    /**
     * checks the farmer's current experience if it already passed the threshold for the current level.
     *
     * @param farmer gets the current xp of the farmer
     * @return
     */
    public ArrayList<Object> checkExperience(Farmer farmer) {

        ArrayList<Object> checkingFarm = new ArrayList<>();

        int farmerXPLevel = ((int)farmer.getExperience()/100);
        System.out.println("xp: " + farmerXPLevel + " " + farmer.getFarmerLevel());
        if (farmerXPLevel > farmer.getFarmerLevel()) {
            farmer.setFarmerLevel(farmerXPLevel);
            checkingFarm = checkFarmerLevel(farmer, true);
        }

        return checkingFarm;
    }

    /**
     *
     * checks the farmer's current level if it already passed the threshold for the current registration.
     *
     * @param farmer gets the current level of the farmer
     */
    public ArrayList<Object> checkFarmerLevel(Farmer farmer, boolean hasLeveledUp) {

        ArrayList<Object> farmerLvl = new ArrayList<>();

        StringBuilder farmerLvlAlert = new StringBuilder();

        FarmerLevel farmerLevel = checkLevel(farmer);

        if (hasLeveledUp) {

            farmerLvl.add(farmerLvlAlert.append("You have leveled up! \nYou are now level ").append(farmer.getFarmerLevel()).append("!"));

            if (farmerLevel != null) {

                farmerLvl.add(promptFarmerRegistration(farmerLevel));
                farmerLvl.add(farmerLevel);

            }

        }

        return farmerLvl;

    }

    public String checkRegister(Farmer farmer, FarmerLevel farmerLevel) {
        String feedback = null;
        if (farmer.getFarmerInventory().getObjectCoins() < farmerLevel.getRegistrationFee()) {
            feedback = "You have insufficient funds to register.";
        } else {
            farmer.changeRegistration(farmerLevel);
            feedback = "Successfully changed farmer registration to " + farmerLevel.name() + " FARMER!";
        }

        return feedback;
    }

    /**
     *
     * checks the level of the farmer if equal to the level of the required level
     *
     * @param farmer gets the current level of the farmer
     * @return the new level of farmer status
     */
    public FarmerLevel checkLevel(Farmer farmer) {

        FarmerLevel newLevel = null;

        for(FarmerLevel farmerLvl : FarmerLevel.values()) {
            if (farmer.getFarmerLevel() == farmerLvl.getRequiredLevel()) {
                newLevel = farmerLvl;
            }
        }

        return newLevel;
    }

    /**
     *
     * gives info for the next farmer registration level to the user to give them a choice of increasing their registration or not.
     *
     * @param farmerLvl checks the level of the farmer.
     * @return if the user has the sufficient funds to register to the next level.
     */
    public String promptFarmerRegistration(FarmerLevel farmerLvl) {

        StringBuilder dialogue = new StringBuilder();

        dialogue.append("\nYou can now register as " + farmerLvl.name() + " FARMER!");
        dialogue.append("\nPay " + farmerLvl.getRegistrationFee() + " Object coins to receive the following benefits: ");
        dialogue.append("\nBonus Earnings per Produce: " + farmerLvl.getBonusEarnings());
        dialogue.append("\nSeed Cost Reduction: " + farmerLvl.getSeedCostReduction());
        dialogue.append("\nWater Bonus Limit Increase: " + farmerLvl.getWaterBonus());
        dialogue.append("\nFertilizer Bonus Limit Increase: " + farmerLvl.getFertBonus());

        return dialogue.toString();
    }


}
