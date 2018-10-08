package BikePickUp.Bike;

import java.io.Serializable;

public interface PickUp extends Serializable {

    int MAX_MINS = 60;
    
    int MINS_LATE = 30;
    
	String getBikeID();
	
	String getUserID();

	void setMinutes(int minutes);

	int getMinutes();

	void setFinalParkID(String finalParkID);

	String getFinalParkID();

	int minutesLate();

	String getInitialIDPark();
	
	void setCost();

	int getCost();

	boolean isThereTardiness();
}
