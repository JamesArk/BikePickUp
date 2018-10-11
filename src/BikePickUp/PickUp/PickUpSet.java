package BikePickUp.PickUp;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 *
 * An interface for setters and methods that affect the object
 */
public interface PickUpSet extends PickUp {

    /**
     * Set the pick's duration
     * @param minutes - pickup's duration
     */
    void setMinutes(int minutes);

    /**
     * Sets the final park identification
     * @param finalParkID - final park identification
     */
    void setFinalParkID(String finalParkID);

    /**
     * Sets the pickup's cost
     */
    void setCost();
}
