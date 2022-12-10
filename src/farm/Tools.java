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


    /**
     * Acts as the getter for the game tool's name
     * @return the name of the current tool
     */
    public String getToolName() {
        return toolName;
    }

    /**
     * Acts as the getter for the game tool's function
     * @return the function of the current tool
     */
    public String getToolFunction() {
        return toolFunction;
    }

    /**
     * Acts as the getter for the game tool's cost of usage
     * @return the cost of usage of the current tool
     */
    public int getCostOfUsage() {
        return costOfUsage;
    }

    /**
     * Acts as the getter for the game tool's experience gain
     * @return the experience gain of the current tool
     */
    public double getExperienceGainedOnUse() {
        return experienceGainedOnUse;
    }

    /**
     * Acts as the setter for the game tool's name
     * @param toolName the name of the current tool
     */
    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    /**
     * Acts as the setter for the game tool's function
     * @param toolFunction the function of the current tool
     */
    public void setToolFunction(String toolFunction) {
        this.toolFunction = toolFunction;
    }

    /**
     * Acts as the setter for the game tool's cost of usage
     * @param costOfUsage the cost of usage of the current tool
     */
    public void setCostOfUsage(int costOfUsage) {
        this.costOfUsage = costOfUsage;
    }

    /**
     * Acts as the setter for the game tool's experience gain
     * @param experienceGainedOnUse the experience gain of the current tool
     */
    public void setExperienceGainedOnUse(double experienceGainedOnUse) {
        this.experienceGainedOnUse = experienceGainedOnUse;
    }
}
