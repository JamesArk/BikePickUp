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
     * For each 30 minutes the pickup's cost incresases
     */
    int MINS_LATE = 30;

    /**
     * Return bike's id
     * @return bike's id
     */
	String getBikeID();

    /**
     * Return user's id
     * @return user's id
     */
	String getUserID();

    /**
     * Return's duration (in minutes)
     * @return duration
     */
	int getMinutes();

    /**
     * Return final park identification
     * @return final park identification
     */
	String getFinalParkID();

    /**
     * Return the minutes late
     * @return the minutes late
     */
	int minutesLate();

    /**
     * Return the final park identification
     * @return the initial park identification
     */
	String getInitialIDPark();

    /**
     * Return pickup's cost
     * @return pickup's cost
     */
	int getCost();
}
