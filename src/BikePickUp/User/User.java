package BikePickUp.User;


import BikePickUp.PickUp.PickUp;
import dataStructures.Iterator;
import java.io.Serializable;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 */
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
