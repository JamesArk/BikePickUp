package bikePickUp.Park;

import bikePickUp.Bike.Bike;
import bikePickUp.dataStructures.Iterator;

public interface Park {

	String getID();
	
	Iterator<String> getParkInfo();
	
	void addBike(Bike b);

	void pickUp();

	void pickDown(Bike bike);

	boolean isBikeInPark();
}
