package farm;

public class Store {

    private Seeds seeds = new Seeds();

    /**
     *
     * Creates the transaction that the user and the store will create in selling the seeds.
     *
     * @param farmer the farmer's objectcoins will be used to know if they can afford to buy what they inputted.
     * @param plantChoice the farmer's pick in buying the seeds.
     *
     * @return the feedback from the farmer's purchase
     */
    public String sellItem(Farmer farmer, int plantChoice) {

        double price = ((seeds.getPlants().get(plantChoice).getSeedCost())-farmer.getSeedCostReduction());

        if (price <= farmer.getFarmerInventory().getObjectCoins()) {
            farmer.getFarmerInventory().setObjectCoins(farmer.getFarmerInventory().getObjectCoins()-price);
            farmer.addItemToInventory(seeds, 1, plantChoice);
            return "Successfully purchased!";
        } else {
             return "Insufficient funds.";
        }

    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */

    public Seeds getProducts() {
        return seeds;
    }

    public void setSeeds(Seeds seeds) {
        this.seeds = seeds;
    }
}