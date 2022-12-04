package farm;

public class Tools {

    private String toolName;
    private String toolFunction;
    private int costOfUsage;
    private double experienceGainedOnUse;

    /**
     * class constructor for the tools available in the game
     *
     * @param toolName name of the tool.
     * @param toolFunction function of the tool.
     * @param costOfUsage the amount that the user must pay in order to use the tool.
     * @param experienceGainedOnUse the xp that the user will gain once it is used.
     */
    public Tools(String toolName, String toolFunction, int costOfUsage, double experienceGainedOnUse) {
        this.toolName = toolName;
        this.toolFunction = toolFunction;
        this.costOfUsage = costOfUsage;
        this.experienceGainedOnUse = experienceGainedOnUse;
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */

    public String getToolName() {
        return toolName;
    }

    public String getToolFunction() {
        return toolFunction;
    }

    public int getCostOfUsage() {
        return costOfUsage;
    }

    public double getExperienceGainedOnUse() {
        return experienceGainedOnUse;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public void setToolFunction(String toolFunction) {
        this.toolFunction = toolFunction;
    }

    public void setCostOfUsage(int costOfUsage) {
        this.costOfUsage = costOfUsage;
    }

    public void setExperienceGainedOnUse(double experienceGainedOnUse) {
        this.experienceGainedOnUse = experienceGainedOnUse;
    }
}
