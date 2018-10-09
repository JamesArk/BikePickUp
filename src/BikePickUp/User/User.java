package BikePickUp.User;


import BikePickUp.PickUp.PickUp;
import dataStructures.Iterator;
import java.io.Serializable;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 */
public interface User  extends Serializable {

    /**
     * Returns user's identification
     * @return user's identification
     */
	String getID();

    /**
     * Returns the user's details.
     * @return iterator with the user's details.
     */
    Iterator<String> getUserInfo();

    /**
     * Returns the user's funds
     * @return user's balance
     */
    int getBalance();

    /**
     * Adds a new incomplete pickup
     * @param pickUp the pickup to be added
     */
    void pickUp(PickUp pickUp);

    /**
     * Returns true if the user used the system.
     * @return true if the user used the system.
     */
    boolean hasUsedSystem();

    /**
     * Completes the current pickup by adding the final park identification and its duration
     * @param finalParkID - final park identification
     * @param minutes - pickup's duration
     */
    void pickDown(String finalParkID, int minutes);

    /**
     * Adds funds to the user
     * @param value amount to be added.
     */
    void charge(int value);

    /**
     * Returns true if the first pick up is incomplete
     * @return true if the bike is on its first pick up
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
     * Returns true if the user has more than zero points (has a pick up which exceeded 60 minutes of duration)
     * @return true if the user has more than zero points
     */
    boolean isThereTardiness();

    /**
     * Returns true if the user has an incomplete pickup
     * @return true if the user has an unfinished pickup
     */
    boolean isOnTheMove();
}
