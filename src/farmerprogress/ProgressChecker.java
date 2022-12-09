package farmerprogress;

import farm.Farmer;

import java.util.Scanner;

public class ProgressChecker {

    /**
     * checks the farmer's current experience if it already passed the threshold for the current level.
     *
     * @param farmer gets the current xp of the farmer
     * @return
     */
    public String checkExperience(Farmer farmer) {

        String check = null;

        int farmerXPLevel = ((int)farmer.getExperience()/100);
        System.out.println("xp: " + farmerXPLevel + " " + farmer.getFarmerLevel());
        if (farmerXPLevel > farmer.getFarmerLevel()) {
            farmer.setFarmerLevel(farmerXPLevel);
            check = checkFarmerLevel(farmer, true);
        }

        return check;
    }

    /**
     *
     * checks the farmer's current level if it already passed the threshold for the current registration.
     *
     * @param farmer gets the current level of the farmer
     */
    public String checkFarmerLevel(Farmer farmer, boolean hasLeveledUp) {

        StringBuilder farmerLvlAlert = new StringBuilder();

        FarmerLevel farmerLevel = checkLevel(farmer);

        if (hasLeveledUp) {

            farmerLvlAlert.append("You have leveled up! You are now level ").append(farmer.getFarmerLevel()).append("! Congratulations!");

            if (farmerLevel != null) {

//                playerChoice = promptFarmerRegistration(farmerLevel);

//                if (playerChoice == 1) {
//                    if (farmer.getFarmerInventory().getObjectCoins() < farmerLevel.getRegistrationFee()) {
//                        System.out.println("You have insufficient funds to register.");
//                    } else {
//                        farmer.changeRegistration(farmerLevel);
//                        System.out.println("Successfully changed farmer registration to " + farmerLevel.name() + " FARMER!");
//                    }
//                }

            }

        }

        return farmerLvlAlert.toString();

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
    public void promptFarmerRegistration(FarmerLevel farmerLvl) {

        Scanner scanner = new Scanner(System.in);

        int playerChoice = -1;

        System.out.println("\nYou can now register as " + farmerLvl.name() + " FARMER!");
        System.out.println("You will have to pay " + farmerLvl.getRegistrationFee() + " Object coins to receive the following benefits: ");
        System.out.println("Bonus Earnings per Produce: " + farmerLvl.getBonusEarnings());
        System.out.println("Seed Cost Reduction: " + farmerLvl.getSeedCostReduction());
        System.out.println("Water Bonus Limit Increase: " + farmerLvl.getWaterBonus());
        System.out.println("Fertilizer Bonus Limit Increase: " + farmerLvl.getFertBonus());

        do {
            System.out.println();
            System.out.println("Would you like to register?");

            System.out.println("1: Yes");
            System.out.println("2: No");

            try {
                playerChoice = scanner.nextInt();
            }
            catch (Exception e) {
                scanner.nextLine();
                System.out.println("Only input valid integers. Please try again.");
            }

        } while(playerChoice < 1 || playerChoice > 2);

//        return playerChoice;
    }


}
