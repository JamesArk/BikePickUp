package BikePickUp.PickUp;

import java.io.Serializable;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 * A pickup from the system
 *
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
     * Set the pick's duration
     * @param minutes - pickup's duration
     */
	void setMinutes(int minutes);

    /**
     * Return's duration (in minutes)
     * @return duration
     */
	int getMinutes();

    /**
     * Sets the final park identification
     * @param finalParkID - final park identification
     */
	void setFinalParkID(String finalParkID);

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
     * Sets the pickup's cost
     */
	void setCost();

    /**
     * Return pickup's cost
     * @return pickup's cost
     */
	int getCost();
}
