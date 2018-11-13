package BikePickUp.Bike;

import BikePickUp.PickUp.PickUp;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 *
 * An interface for getters only
 */
public interface Bike extends Serializable {

	/**
	 * Return bike's identification
	 * @return bike's identification
	 */
	String getID();

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
     * Return true if the first pick up is incomplete
     * @return true if the bike is on its first pick up
     */
	boolean isBikeOnFirstPickUp();

    /**
     * Return an iterator with all the pickups
     * @return an iterator with all the pickups
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

	/**
	 * Pre : currentPickUp != null;
     * Returns the user Of the bike's current pickup.
	 * @return user's ID.
	 */
    String getUserID();
}
