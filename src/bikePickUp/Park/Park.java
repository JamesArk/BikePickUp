package bikePickUp.Park;

import bikePickUp.Bike.Bike;
import bikePickUp.dataStructures.Queue;

public interface Park {

	String getID();
	
	Queue<String> getParkInfo();
	
	void addBike(Bike b);
}
