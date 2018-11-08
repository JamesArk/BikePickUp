package BikePickUp.PickUp;

import java.io.Serializable;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 *
 * An interface for getters only
 */
public interface PickUp extends Serializable {

    /**
     * The max duration for a free pickup
     */
    int MAX_MINS = 60;

    /**
     * For each 30 minutes the pickup's cost increases
     */
    int MINS_LATE = 30;

    /**
     * Returns bike's id of this pickup
     * @return bike's id
     */
	String getBikeID();

    /**
     * Returns user's id of this pickup
     * @return user's id
     */
	String getUserID();

    /**
     * Returns duration (in minutes)
     * @return duration
     */
	int getMinutes();

    /**
     * Returns final park identification
     * @return final park identification
     */
	String getFinalParkID();

    /**
     * Returns the minutes late
     * @return the minutes late
     */
	int minutesLate();

    /**
     * Returns the final park identification
     * @return the initial park identification
     */
	String getInitialParkID();

    /**
     * Returns pickup's cost
     * @return pickup's cost
     */
	int getCost();
}
