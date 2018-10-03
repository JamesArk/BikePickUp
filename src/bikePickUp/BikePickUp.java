package bikePickUp;

import bikePickUp.Exceptions.*;
import bikePickUp.dataStructures.Iterator;


public interface BikePickUp {

	int MIN_PICKUP_BALANCE = 5;
    void addUser(String userID, String nif, String email,String phone, String name, String address);

    void removeUser(String userID) throws UserNotFoundException, UserUsedSystemException;
    
    Iterator<String> getUserInfo(String userID) throws UserNotFoundException;

	void addPark(String idPark, String name, String address) throws ParkAlreadyExistsException;

	void addBike(String idBike, String idPark, String bikeLicense) throws BikeAlreadyExistsException,ParkNotFoundException;

	void removeBike(String bikeID) throws BikeNotFoundException, UsedBikeException ;

	Iterator<String> getParkInfo(String parkID) throws ParkNotFoundException;

	void pickUp(String idBike, String idUser) throws BikeNotFoundException,BikeOnTheMoveException,UserNotFoundException,UserOnTheMoveException,InsufficientBalanceException;

	void pickDown(String idBike,String idPark, int minutes) throws BikeNotFoundException, BikeStoppedException,ParkNotFoundException,InvalidDataException;

    void chargeUser(String idUser, int value) throws  UserNotFoundException,InvalidDataException;
}
