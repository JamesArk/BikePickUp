package bikePickUp.Bike;

import bikePickUp.dataStructures.Iterator;

import java.io.Serializable;

public interface Bike extends Serializable {

	String getID();

	void pickUp(PickUp pickUp);

	boolean hasBeenUsed();

	String getParkID();

	void pickDown(String finalParkID, int minutes);
	
	boolean isOnTheMove();

	Iterator<PickUp> getBikePickUps();


}
