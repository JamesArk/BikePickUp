package BikePickUp.Bike;

import BikePickUp.PickUp.PickUpSet;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 *
 * An interface for setters and methods that affect the object
 */
public interface BikeSet extends Bike{

    /**
     * Adds a new incomplete pickup
     * @param pickUp - the pickup to be added
     */
    void pickUp(PickUpSet pickUp);

    /**
     * Completes the current pickup by adding the final park identification and its duration
     * @param finalParkID - final park identification
     * @param minutes - pickup's duration
     */
    void pickDown(String finalParkID, int minutes);
}
