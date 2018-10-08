package BikePickUp.Bike;

import dataStructures.Iterator;

import java.io.Serializable;

public interface Bike extends Serializable {

	/**
	 * 
	 * @return bike's identification
	 */
	String getID();
	
	void pickUp(PickUp pickUp);

	boolean hasBeenUsed();

	String getParkID();

	void pickDown(String finalParkID, int minutes);
	
	boolean isBikeOnFirstPickUp();

	Iterator<PickUp> getBikePickUps();
	
	String getBikeLicense();

	boolean isOnTheMove();
}
