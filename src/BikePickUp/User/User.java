package BikePickUp.User;


import BikePickUp.PickUp.PickUp;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 *
 * An interface for getters only
 */
public interface User  extends Serializable {


    /**
     * Returns the user's funds
     * @return user's balance
     */
    int getBalance();

    /**
     * Returns true if the user used the system.
     * @return true if the user used the system.
     */
    boolean hasUsedSystem();

    /**
     * Returns true if the first pick up is incomplete
     * @return true if the user is on its first pick up
     */
    boolean isUserIsOnFirstPickUp();

    /**
     * Returns all of the users pickups
     * @return iterator of all the user's pickups
     */
	Iterator<PickUp> getUserPickUps();

    /**
     * Returns user's points
     * @return user's points
     */
	int getPoints();

    /**
     * Returns true if the user has an incomplete pickup
     * @return true if the user has an unfinished pickup
     */
    boolean isOnTheMove();

    /**
     * Returns the user's name
     * @return user's name
     */
    String getName();

    /**
     * Returns the user's phone
     * @return user's phone
     */
    String getPhone();

    /**
     * Returns the user's email
     * @return user's email
     */
    String getEmail();

    /**
     * Returns the user's address
     * @return user's address
     */
    String getAddress();

    /**
     * Returns the user's NIF
     * @return user's NIF
     */
    String getNIF();
}
