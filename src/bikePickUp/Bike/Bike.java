package bikePickUp.Bike;

public interface Bike {

	String getID();

	void pickUp(PickUp pickUp);

	boolean hasBeenUsed();

	String getParkID();

	void pickDown(String finalParkID, int minutes);
}
