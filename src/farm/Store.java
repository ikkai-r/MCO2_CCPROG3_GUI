package farm;

public class Store {

    private Seeds seeds = new Seeds();

    /**
     *
     * This method creates the transaction that the user and the store will create in selling the seeds.
     * @param farmer the farmer's objectcoins will be used to know if they can afford to buy what they inputted.
     * @param plantChoice the farmer's pick in buying the seeds.
     * @param plantSeeds the amount of seeds that the farmer wants to buy.
     */
    public void sellItem(Farmer farmer, int plantChoice, int plantSeeds) {

        double price = (double)plantSeeds*((seeds.getPlants().get(plantChoice-1).getSeedCost())-farmer.getSeedCostReduction());

        if (price <= farmer.getFarmerInventory().getObjectCoins()) {
            farmer.getFarmerInventory().setObjectCoins(farmer.getFarmerInventory().getObjectCoins()-price);
            farmer.addItemToInventory(seeds, plantSeeds, plantChoice);
            System.out.println("Successfully purchased!");
        } else {
            System.out.println("Insufficient funds.");
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