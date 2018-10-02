package bikePickUp;

import bikePickUp.Exceptions.BikeAlreadyExistsException;
import bikePickUp.Exceptions.BikeNotFoundException;
import bikePickUp.Exceptions.ParkAlreadyExistsException;
import bikePickUp.Exceptions.ParkNotFoundException;
import bikePickUp.Exceptions.UserNotFoundException;
import bikePickUp.dataStructures.Queue;

public interface BikePickUp {
    void addUser(String userID, String nif, String email,String phone, String name, String address);

    void removeUser(String userID) throws UserNotFoundException;
    
    Queue<String> getUserInfo(String userID) throws UserNotFoundException;

	void addPark(String idPark, String name, String address) throws ParkAlreadyExistsException;

	void addBike(String idBike, String idPark, String bikeLicense) throws BikeAlreadyExistsException,ParkNotFoundException;

	void removeBike(String bikeID) throws BikeNotFoundException ;

	Queue<String> getParkInfo(String parkID) throws ParkNotFoundException;
}
