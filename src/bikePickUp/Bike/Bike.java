package bikePickUp.Bike;

import bikePickUp.dataStructures.Iterator;

public interface Bike {

	String getID();

	void pickUp(PickUp pickUp);

	boolean hasBeenUsed();

	String getParkID();

	void pickDown(String finalParkID, int minutes);
	
	boolean getMoveSituation();

	Iterator<PickUp> getBikePickUps();
	
	String getBikeLicense();
	
	
}
