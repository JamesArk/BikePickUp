package bikePickUp.User;


import bikePickUp.Bike.PickUp;
import bikePickUp.dataStructures.Iterator;


public interface User {
    
	String getID();
	
    Iterator<String> getUserInfo();

    int getBalance();

    void pickUp(PickUp pickUp);

    boolean hasUsedSystem();

    void pickDown(String finalParkID, int minutes);

    void charge(int value);
    
    boolean getMoveSituation();
    
	Iterator<PickUp> getUserPickUps();
	
	int getPoints();
}
