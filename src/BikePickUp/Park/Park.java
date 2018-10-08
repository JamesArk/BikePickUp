package BikePickUp.Park;

import BikePickUp.Bike.Bike;
import dataStructures.Iterator;

import java.io.Serializable;

public interface Park  extends Serializable {

	String getID();
	
	Iterator<String> getParkInfo();
	
	void addBike(Bike b);

	void pickUp();

	void pickDown(Bike bike);

	boolean isBikeInPark();

    Iterator<String> getFavouriteParkInfo();

	void removeBike();
}
