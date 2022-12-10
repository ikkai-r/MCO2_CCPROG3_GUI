package farmerprogress;

public enum FarmerLevel {

    REGISTERED(5, 1, 1, 0, 0, 200),
    DISTINGUISHED(10, 2, 2, 1, 0, 300),
    LEGENDARY(15, 3, 4, 2, 1, 400);

    private final int REQUIRED_LEVEL;
    private final int SEED_COST_REDUCTION;
    private final int BONUS_EARNINGS;
    private final int WATER_BONUS;
    private final int FERT_BONUS;
    private final int REGIS_FEE;

    /**
     * class constructor for the farmer registrations available in the game
     *
     * @param requiredLevel the level the farmer needs to register
     * @param seedCostReduction amount reduced for the farmer's seed costs
     * @param bonusEarnings amount added for the farmer's harvest
     * @param waterBonus number of times added to the farmer's water limits
     * @param fertBonus number of times added to the farmer's fertilizer limits
     * @param registrationFee amount the farmer has to pay to register
     */
    private FarmerLevel(int requiredLevel, int seedCostReduction, int bonusEarnings,
                        int waterBonus, int fertBonus, int registrationFee) {
        this.REQUIRED_LEVEL = requiredLevel;
        this.SEED_COST_REDUCTION = seedCostReduction;
        this.BONUS_EARNINGS = bonusEarnings;
        this.WATER_BONUS = waterBonus;
        this.FERT_BONUS = fertBonus;
        this.REGIS_FEE = registrationFee;

    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */

    /**
     * Acts as a getter for the required level for the farmer registration
     * @return required level to qualify for the farmer registration
     */
    public int getRequiredLevel() {
        return REQUIRED_LEVEL;
    }

    /**
     * Acts as a getter for the seed cost reduction based on the farmer registration
     * @return amount of reduction for the seed cost
     */
    public int getSeedCostReduction() {
        return SEED_COST_REDUCTION;
    }

    /**
     * Acts as a getter for the bonus earnings based on the farmer registration
     * @return amount of bonus for the harvest earnings
     */
    public int getBonusEarnings() {
        return BONUS_EARNINGS;
    }

    /**
     * Acts as a getter for the water bonus based on the farmer registration
     * @return amount of bonus for the watering action
     */
    public int getWaterBonus() {
        return WATER_BONUS;
    }

    /**
     * Acts as a getter for the fertilizer bonus based on the farmer registration
     * @return amount of bonus for the fertilizing action
     */
    public int getFertBonus() {
        return FERT_BONUS;
    }

    /**
     * Acts as a getter for the registration fee
     * @return amount of the registration fee for the farmer registration
     */
    public int getRegistrationFee() {
        return REGIS_FEE;
    }
}
