package BikePickUp.Park;

import BikePickUp.Bike.Bike;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 */
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
