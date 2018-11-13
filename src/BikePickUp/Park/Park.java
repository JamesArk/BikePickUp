package BikePickUp.Park;

import java.io.Serializable;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 *
 * An interface for getters only
 */
public interface Park  extends Serializable {

    /**
     * Return park's id
     * @return park's id
     */
	String getID();

    /**
     * Returns true if the bike is in the park
     * @return true if the bike is in the park
     */
	boolean isBikeInPark(String bikeID);

    /**
     * Returns the number of bikes in the park.
     * @return number of bikes.
     */
	int getNBikes();

    /**
     * Returns the park's address
     * @return park's address
     */
    String getAddress();

    /**
     * Returns the park's number of pickups
     * @return park's number of pickups
     */
    String getNPickUps();

    /**
     * Returns the park's name
     * @return park's name
     */
    String getName();
}
