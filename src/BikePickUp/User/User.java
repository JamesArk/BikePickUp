package BikePickUp.User;


import BikePickUp.Bike.PickUp;
import dataStructures.Iterator;
import java.io.Serializable;


public interface User  extends Serializable {
    
	String getID();
	
    Iterator<String> getUserInfo();

    int getBalance();

    void pickUp(PickUp pickUp);

    boolean hasUsedSystem();

    void pickDown(String finalParkID, int minutes);

    void charge(int value);
    
    boolean isUserIsOnFirstPickUp();
    
	Iterator<PickUp> getUserPickUps();
	
	int getPoints();

    boolean isThereTardiness();

    boolean isOnTheMove();
}
