package bikePickUp.User;

import bikePickUp.Exceptions.UserNotFoundException;
import bikePickUp.dataStructures.Queue;

public interface User {
    String getID();
    Queue<String> getUserInfo();
}
