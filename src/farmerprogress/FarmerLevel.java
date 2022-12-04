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

    private FarmerLevel(int requiredLevel, int seedCostReduction, int bonusEarnings,
                        int waterBonus, int fertBonus, int registrationFee) {
        this.REQUIRED_LEVEL = requiredLevel;
        this.SEED_COST_REDUCTION = seedCostReduction;
        this.BONUS_EARNINGS = bonusEarnings;
        this.WATER_BONUS = waterBonus;
        this.FERT_BONUS = fertBonus;
        this.REGIS_FEE = registrationFee;

    }

    public int getRequiredLevel() {
        return REQUIRED_LEVEL;
    }

    public int getSeedCostReduction() {
        return SEED_COST_REDUCTION;
    }

    public int getBonusEarnings() {
        return BONUS_EARNINGS;
    }

    public int getWaterBonus() {
        return WATER_BONUS;
    }

    public int getFertBonus() {
        return FERT_BONUS;
    }

    public int getRegistrationFee() {
        return REGIS_FEE;
    }
}
