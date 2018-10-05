package bikePickUp.Bike;

public interface PickUp {

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
}
