package BikePickUp;

import BikePickUp.Park.Park;
import BikePickUp.PickUp.PickUp;
import BikePickUp.Exceptions.*;
import BikePickUp.User.User;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 *
 * An application with one user, one bike and one park
 */
public interface BikePickUp extends Serializable {

	/**
	 * Minimum balance allowed to pickup a bike
	 */
	int MIN_PICKUP_BALANCE = 5;
	
	/**
	 * Adds a new user to the system
	 * @param userID - user's identification
	 * @param nif - user's NIF
	 * @param email - user's email
	 * @param phone - user's phone
	 * @param name - user's name
	 * @param address - user's address
     * @throws UserAlreadyExistsException - if there is a user in the system with the same identification
	 */
    void addUser(String userID, String nif, String email,String phone, String name, String address) throws UserAlreadyExistsException;

    /**
     * Removes the user from the system, if it hasn't picked up a bike
     * @param userID - the user with the specified identification
     * @throws UserNotFoundException - if there is no user in the system with the specified identification
     * @throws UserUsedSystemException - if user already picked up a bike
     */
    void removeUser(String userID) throws UserNotFoundException, UserUsedSystemException;
    
    /**
     * Returns the user for its details.
     * @param userID - user's ID
     * @return object user.
     * @throws UserNotFoundException - if there is no user in the system with the specified identification
     */
     User getUserInfo(String userID) throws UserNotFoundException;

    /**
     * Adds a park to the system
     * @param idPark - park's identification
     * @param name - park's name
     * @param address - park's address
     * @throws ParkAlreadyExistsException - if the specified park has already been registered.
     */
	void addPark(String idPark, String name, String address) throws ParkAlreadyExistsException;

	/**
	 * Adds a bike to the system and to the specified park
	 * @param idBike - bike's identification.
	 * @param idPark - park's identification.
	 * @param bikeLicense - bike's license
	 * @throws BikeAlreadyExistsException - if the specified bike has already been registered
	 * @throws ParkNotFoundException - if there is no park in the system with the specified identification
	 */
	void addBike(String idBike, String idPark, String bikeLicense) throws BikeAlreadyExistsException,ParkNotFoundException;

	/**
	 * Removes the bike from the system, if no user has ever used the specified bike.
	 * @param bikeID - bike's identification.
	 * @throws BikeNotFoundException - if there is no bike in the system with the specified identification
	 * @throws UsedBikeException - if the user already used the specified bike (pickup)
	 */
	void removeBike(String bikeID) throws BikeNotFoundException, UsedBikeException ;

	/**
	 * Returns the park for its details.
	 * @param parkID - park's identification
	 * @return object park.
	 * @throws ParkNotFoundException - if there is no park in the system with the specified identification
	 */
	Park getParkInfo(String parkID) throws ParkNotFoundException;

	/**
	 * The user "picks up" the bike, creating a new incomplete pickup.
	 * @param idBike - the bike's identification that the user will pickup.
	 * @param idUser - the user's identification which will execute the pickup.
	 * @throws BikeNotFoundException - if there is no bike in the system with the specified identification
	 * @throws BikeOnTheMoveException - if the bike hasn't completed its current pickup
	 * @throws UserNotFoundException - if there is no user in the system with the specified identification
	 * @throws UserOnTheMoveException - if the user hasn't completed its current pickup
	 * @throws InsufficientBalanceException - if the user doesn't have the minimum balance to pickup a bike
	 */
	void pickUp(String idBike, String idUser) throws BikeNotFoundException,BikeOnTheMoveException,UserNotFoundException,UserOnTheMoveException,InsufficientBalanceException;

	/**
	 * The user returns the bike he "picked up" to the system
	 * @param idBike - bike's identification
	 * @param finalIdPark - park's identification that the bike will be parked in.
	 * @param minutes - the duration of the pickup
	 * @throws BikeNotFoundException - if there is no bike in the system with the specified identification
	 * @throws BikeStoppedException - if the bike isn't being used
	 * @throws ParkNotFoundException - if there is no park in the system with the specified identification
	 * @throws InvalidDataException - if the value is less or equal to zero
	 */
	User pickDown(String idBike,String finalIdPark, int minutes) throws BikeNotFoundException, BikeStoppedException,ParkNotFoundException,InvalidDataException;

	/**
	 * Adds funds to the user.
	 * @param idUser - user's identification
	 * @param value - the amount to be added.
	 * @throws UserNotFoundException - if there is no user in the system with the specified identification
	 * @throws InvalidDataException - if the value is less or equal to zero
	 */
    User chargeUser(String idUser, int value) throws  UserNotFoundException,InvalidDataException;

    /**
     * Returns all of the bike's pickups
     * @param idBike - bike's identification
     * @return an iterator containing information about the bike's activity(completed pickups)
     * @throws BikeNotFoundException - if there is no bike in the system with the specified identification
     * @throws BikeNotUsedException - if the bike hasn't been used yet
     * @throws BikeOnFirstPickUpException - if the bike is on its first pickup
     */
	Iterator<PickUp> getBikePickUps(String idBike) throws BikeNotFoundException, BikeNotUsedException, BikeOnFirstPickUpException;

	/**
	 * Returns all of the user's pickups
	 * @param idUser - user's identification
	 * @return an iterator containing information about the user's activity(completed pickups)
	 * @throws UserNotFoundException - if there is no user in the system with the specified identification
	 * @throws UserNotUsedSystemException - if the user hasn't "picked up" a bike yet
	 * @throws UserOnFirstPickUpException - if the user is on its first pickup
	 */
	Iterator<PickUp> getUserPickUps(String idUser) throws UserNotFoundException,UserNotUsedSystemException,UserOnFirstPickUpException;

	/**
	 * Returns an exception if the specified bike is not parked in the specified park.
	 * @param idBike - bike's identification
	 * @param idPark - bike's identification
	 * @throws BikeNotFoundException - if there is no bike in the system with the specified identification
	 * @throws ParkNotFoundException - if there is no park in the system with the specified identification
	 * @throws BikeNotInParkException - if the bike is not parked is the specified park
	 */
	void bikeParked(String idBike, String idPark) throws BikeNotFoundException, ParkNotFoundException, BikeNotInParkException;

	/**
	 * Returns all users that have a least one pickup that exceeded 60 minutes of duration.
	 * @return an iterator of users who have at least one pickup that exceeded 60 minutes of duration.
	 * @throws NoTardinessException - if every user has zero points(meaning there were no pickups that have more than 60 minutes of duration)
	 */
	Iterator<User> listDelayed() throws NoTardinessException;

	/**
	 * Returns all of the parks ordered by popularity, excluding the ones which haven't been used.
	 * @return all of the parks that have completed PickUps.
	 * @throws NoPickUpsMadeException - if there are no PickUps made by any user.
	 */
	Park favouriteParks() throws NoPickUpsMadeException;
}
