package BikePickUp.Bike;

import BikePickUp.PickUp.PickUp;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 */
public interface Bike extends Serializable {

	/**
	 * 
	 * @return bike's identification
	 */
	String getID();

    /**
     * Creates a new incomplete pickup
     * @param pickUp - the pick to be added
     */
	void pickUp(PickUp pickUp);

    /**
     * Returns true, if the bike has been used
     * @return true, if the bike has been used
     */
	boolean hasBeenUsed();

    /**
     * Return park's identification
     * @return park's identification
     */
	String getParkID();

    /**
     * Completes the current pickup by adding the final park identification and its duration
     * @param finalParkID - final park identification
     * @param minutes - pickup's duration
     */
	void pickDown(String finalParkID, int minutes);

    /**
     * Return true if the first pick up is incomplete
     * @return true if the bike is on its first pick up
     */
	boolean isBikeOnFirstPickUp();

    /**
     * Return an iterator with all the pick ups
     * @return an iterator with all the pick ups
     */
	Iterator<PickUp> getBikePickUps();

    /**
     * Return bike's license
     * @return bike's license
     */
	String getBikeLicense();

    /**
     * Return true, if the bike has been picked up but not droppedOff (pickeddown)
     * @return true, if the bike has a incomplete pick up
     */
	boolean isOnTheMove();
}
