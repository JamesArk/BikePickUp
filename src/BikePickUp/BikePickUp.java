package BikePickUp;

import BikePickUp.Bike.PickUp;
import BikePickUp.Exceptions.*;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * An application with one user, one bike and one park
 *
 */
public interface BikePickUp extends Serializable {

	/**
	 * Minimum balance to be allowed to pickup a bike
	 */
	int MIN_PICKUP_BALANCE = 5;
	
	/**
	 * Adds a new user to the system
	 * @param userID - a string that the system uses to identify the user
	 * @param nif - user´s NIF
	 * @param email - user's email
	 * @param phone - user's phone
	 * @param name - user's name
	 * @param address - user's address
	 */
    void addUser(String userID, String nif, String email,String phone, String name, String address) throws UserAlreadyExistsException;

    /**
     * Removes the user from the system
     * @param userID - the user with the specified identification
     * @throws UserNotFoundException
     * @throws UserUsedSystemException
     */
    void removeUser(String userID) throws UserNotFoundException, UserUsedSystemException;
    
    /**
     * Obtains the information about the user
     * @param userID - the user with the specified identification
     * @return an iterator of strings containing details about the specified user
     * @throws UserNotFoundException
     */
    Iterator<String> getUserInfo(String userID) throws UserNotFoundException;

    /**
     * Adds a park to the system
     * @param idPark - a string that the system uses to identify the park
     * @param name - park's name
     * @param address - park's address
     * @throws ParkAlreadyExistsException
     */
	void addPark(String idPark, String name, String address) throws ParkAlreadyExistsException;

	/**
	 * Adds a bike to the system and to the specified park
	 * @param idBike - a string that the system uses to identify the bike
	 * @param idPark - a string that the system uses to identify the park
	 * @param bikeLicense - bike's license
	 * @throws BikeAlreadyExistsException
	 * @throws ParkNotFoundException
	 */
	void addBike(String idBike, String idPark, String bikeLicense) throws BikeAlreadyExistsException,ParkNotFoundException;

	/**
	 * Removes the bike from the system
	 * @param bikeID - the bike with the specified identification
	 * @throws BikeNotFoundException
	 * @throws UsedBikeException
	 */
	void removeBike(String bikeID) throws BikeNotFoundException, UsedBikeException ;

	/**
	 * Obtains the info about the park
	 * @param parkID - the park with the specified identification
	 * @return an iterator of string s containing the information about the specified park 
	 * @throws ParkNotFoundException
	 */
	Iterator<String> getParkInfo(String parkID) throws ParkNotFoundException;

	/**
	 * The user pick a bike of the system
	 * @param idBike - the bike the user will pick
	 * @param idUser - the user with the specified identification
	 * @throws BikeNotFoundException
	 * @throws BikeOnTheMoveException
	 * @throws UserNotFoundException
	 * @throws UserOnTheMoveException
	 * @throws InsufficientBalanceException
	 */
	void pickUp(String idBike, String idUser) throws BikeNotFoundException,BikeOnTheMoveException,UserNotFoundException,UserOnTheMoveException,InsufficientBalanceException;

	/**
	 * The user returns the bike he picked from the system
	 * @param idBike - the bike with the specified identification
	 * @param idPark - the park with the specified identification
	 * @param minutes - the amount of time the user used the bike
	 * @throws BikeNotFoundException
	 * @throws BikeStoppedException
	 * @throws ParkNotFoundException
	 * @throws InvalidDataException
	 */
	void pickDown(String idBike,String idPark, int minutes) throws BikeNotFoundException, BikeStoppedException,ParkNotFoundException,InvalidDataException;

	/**
	 * Charges an amount to the user
	 * @param idUser - the user with the specified identification
	 * @param value - the value to be charged
	 * @throws UserNotFoundException
	 * @throws InvalidDataException
	 */
    void chargeUser(String idUser, int value) throws  UserNotFoundException,InvalidDataException;

    /**
     * 
     * @param idBike - the bike with the specified identification
     * @return an iterator containing information about the bike's activity
     * @throws BikeNotFoundException
     * @throws BikeNotUsedException
     * @throws BikeOnFirstPickUpException
     */
	Iterator<PickUp> getBikePickUps(String idBike) throws BikeNotFoundException, BikeNotUsedException, BikeOnFirstPickUpException;

	/**
	 * 
	 * @param idUser - the user with the specified identification
	 * @return an iterator containing information about the user's activity 
	 * @throws UserNotFoundException
	 * @throws UserNotUsedSystemException
	 * @throws UserOnFirstPickUpException
	 */
	Iterator<PickUp> getUserPickUps(String idUser) throws UserNotFoundException,UserNotUsedSystemException,UserOnFirstPickUpException;

	/**
	 * 
	 * @return the user's balance
	 */
	int getUserBalance();

	/**
	 * 
	 * @return the user's points
	 */
	int getUserPoints();

	/**
	 * Verifies if the bike is parked in the park
	 * @param idBike - the bike with the specified identification
	 * @param idPark - the bike with the specified identification
	 * @throws BikeNotFoundException
	 * @throws ParkNotFoundException
	 * @throws BikeNotInParkException
	 */
	void isBikeParked(String idBike, String idPark) throws BikeNotFoundException, ParkNotFoundException, BikeNotInParkException;

	/**
	 * 
	 * @return an iterator of strings containing the info about the users who delayed bike's delivery (we are assuming the is only one user)
	 * @throws NoTardinessException
	 */
	Iterator<String> listDelayed() throws NoTardinessException;

	/**
	 * 
	 * @return an iterator of strings containing the information about the favorite parks (we are assuming there is only one park)
	 * @throws NoPickUpsMadeException
	 */
	Iterator<String> favouriteParks() throws NoPickUpsMadeException;
}
