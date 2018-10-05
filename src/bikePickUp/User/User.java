package bikePickUp.User;


import bikePickUp.Bike.PickUp;
import bikePickUp.dataStructures.Iterator;
import java.io.Serializable;


public interface User  extends Serializable {
    
	String getID();
	
    Iterator<String> getUserInfo();

    int getBalance();

    void pickUp(PickUp pickUp);

    boolean hasUsedSystem();

    void pickDown(String finalParkID, int minutes);

    void charge(int value);
    
    boolean isOnTheMove();
    
	Iterator<PickUp> getUserPickUps();
	
	int getPoints();

    boolean isThereTardiness();
}
