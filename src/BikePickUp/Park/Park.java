package BikePickUp.Park;

import BikePickUp.Bike.Bike;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 * P
 */
public interface Park  extends Serializable {

    /**
     * Return park's id
     * @return park's id
     */
	String getID();

    /**
     * Return an iterator with the details of the park
     * @return an iterator with the details of the park
     */
	Iterator<String> getParkInfo();

    /**
     * Adds a bike to the park
     * @param b - the bike to be added
     */
	void addBike(Bike b);

    /**
     * Removes the bike from the park
     */
	void pickUp();

    /**
     * Returns the bike to the park
     * @param bike - the bike to be delivered
     */
	void pickDown(Bike bike);

    /**
     * Returns true if the bike is in the park
     * @return true if the bike is in the park
     */
	boolean isBikeInPark();

    /**
     * Return an iterator with the details of the park
     * @return an iterator with the details of the park
     */
    Iterator<String> getFavouriteParkInfo();

    /**
     * Removes the bike from the park
     */
	void removeBike();
}
