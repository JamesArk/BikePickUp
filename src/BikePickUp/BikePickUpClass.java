package BikePickUp;

import BikePickUp.Bike.*;
import BikePickUp.Exceptions.*;
import BikePickUp.PickUp.PickUp;
import BikePickUp.PickUp.PickUpClass;
import BikePickUp.Park.*;
import BikePickUp.PickUp.PickUpSet;
import BikePickUp.User.*;
import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;
import dataStructures.Iterator;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 */
public class BikePickUpClass implements BikePickUp {

	/**
	 * Constant for serialization
	 */
	static final long serialVersionUID = 0L;

	/**
	 * All of the users in the system.
	 */
	private Dictionary<String,UserSet> users;

    /**
     * All of the bikes in the system.
     */
	private Dictionary<String,BikeSet> bikes;

    /**
     * Park of the system.
     */
	private ParkSet park;

    /**
     * True if there is at least one user that exceeded 60 in duration of a pickup.
     */
	private boolean isThereTardyUser;

    /**
     * The most popular park (Most used) among all users.
     */
	private Park favouritePark;


	/**
	 * System's constructor
	 */
    public BikePickUpClass(){
        park = null;
        isThereTardyUser = false;
        users = new ChainedHashTable<>();
        bikes = new ChainedHashTable<>();
    }

    @Override
    public void addUser(String userID, String nif, String email,String phone, String name, String address) throws UserAlreadyExistsException {
        if(!userNotFound(userID))
            throw new UserAlreadyExistsException();
        users.insert(userID,new UserClass(nif,email,phone,name,address));
    }

    @Override
    public void removeUser(String idUser) throws UserNotFoundException, UserUsedSystemException {
        if(userNotFound(idUser))
            throw new UserNotFoundException();
        if(hasUserUsedSystem(idUser))
        	throw new UserUsedSystemException();
        users.remove(idUser.toLowerCase());
    }

	@Override
	public User getUserInfo(String idUser) throws UserNotFoundException {
		if(userNotFound(idUser))
			throw new UserNotFoundException();
		return users.find(idUser.toLowerCase());
	}

	@Override
	public void addPark(String idPark, String name, String address) throws ParkAlreadyExistsException {
		if(!parkNotFound(idPark))
			throw new ParkAlreadyExistsException();
		park = new ParkClass(idPark,name,address);
		
	}

	@Override
	public void addBike(String idBike, String idPark, String bikeLicense)
			throws BikeAlreadyExistsException, ParkNotFoundException {
		if(!bikeNotFound(idBike))
			throw new BikeAlreadyExistsException();
		if(parkNotFound(idPark))
			throw new ParkNotFoundException();
		BikeSet bike = new BikeClass(idBike,idPark,bikeLicense);
		bikes.insert(idBike,bike);
		park.addBike(bike);
		
	}

    @Override
	public void removeBike(String bikeID) throws BikeNotFoundException,UsedBikeException {
		if(bikeNotFound(bikeID))
			throw new BikeNotFoundException();
		if(hasBikeBeenUsed(bikeID))
			throw new UsedBikeException();
		bikes.remove(bikeID.toLowerCase());
		park.removeBike(bikeID.toLowerCase());
	}

    @Override
	public Park getParkInfo(String parkID) throws ParkNotFoundException {
		if(parkNotFound(parkID))
			throw new ParkNotFoundException();
		return park;
	}

	@Override
	public void pickUp(String idBike, String idUser) throws BikeNotFoundException,BikeOnTheMoveException,UserNotFoundException,UserOnTheMoveException,InsufficientBalanceException {
    	if(bikeNotFound(idBike))
    		throw new BikeNotFoundException();
    	if(bikeIsOnTheMove(idBike))
    		throw new BikeOnTheMoveException();
    	if(userNotFound(idUser))
    		throw new UserNotFoundException();
    	if(userOnTheMove(idUser))
    		throw new UserOnTheMoveException();
    	if(insufficientBalance(idUser))
    		throw new InsufficientBalanceException();
		PickUpSet pickUp = new PickUpClass(idBike.toLowerCase(),idUser.toLowerCase(),bikes.find(idBike).getParkID());
		favouritePark = park;
		users.find(idUser.toLowerCase()).pickUp(pickUp);
        bikes.find(idBike.toLowerCase()).pickUp(pickUp);
		park.pickUp(idBike);
	}

    @Override
	public User pickDown(String idBike, String finalParkID, int minutes) throws BikeNotFoundException, BikeStoppedException, ParkNotFoundException, InvalidDataException {
    	if(bikeNotFound(idBike))
    		throw new BikeNotFoundException();
    	if(!bikeIsOnTheMove(idBike))
    		throw new BikeStoppedException();
    	if(parkNotFound(finalParkID))
    		throw new ParkNotFoundException();
    	if(invalidData(minutes))
    		throw new InvalidDataException();
    	String userID = bikes.find(idBike.toLowerCase()).getUserID();
    	users.find(userID).pickDown(finalParkID,minutes);
    	bikes.find(idBike.toLowerCase()).pickDown(finalParkID,minutes);
    	park.pickDown(bikes.find(idBike));
    	return users.find(userID);
	}

	@Override
	public User chargeUser(String idUser, int value) throws UserNotFoundException, InvalidDataException {
		if(userNotFound(idUser))
			throw new UserNotFoundException();
		if(invalidData(value))
			throw new InvalidDataException();
		users.find(idUser.toLowerCase()).charge(value);
        return users.find(idUser.toLowerCase());
    }

    @Override
	public Iterator<PickUp> getBikePickUps(String idBike) throws BikeNotFoundException, BikeNotUsedException, BikeOnFirstPickUpException {
		if(bikeNotFound(idBike))
			throw new BikeNotFoundException();
		if(!hasBikeBeenUsed(idBike))
			throw new BikeNotUsedException();
		if(isBikeOnFirstPickUp(idBike))
			throw new BikeOnFirstPickUpException();
        return bikes.find(idBike.toLowerCase()).getBikePickUps();
	}

    @Override
	public Iterator<PickUp> getUserPickUps(String idUser)
			throws UserNotFoundException, UserNotUsedSystemException, UserOnFirstPickUpException {
		
		if(userNotFound(idUser))
			throw new UserNotFoundException();
		if(!hasUserUsedSystem(idUser))
			throw new UserNotUsedSystemException();
		if(isUserIsOnFirstPickUp(idUser))
			throw new UserOnFirstPickUpException();
        return users.find(idUser.toLowerCase()).getUserPickUps();
	}

    @Override
	public void bikeParked(String idBike, String idPark) throws BikeNotFoundException, ParkNotFoundException, BikeNotInParkException {
		if(bikeNotFound(idBike))
			throw new BikeNotFoundException();
		if(parkNotFound(idPark))
			throw new ParkNotFoundException();
		if(!park.isBikeInPark(idBike.toLowerCase()))
			throw new BikeNotInParkException();
	}

    @Override
    public Iterator<User> listDelayed() throws NoTardinessException{
        if(!isThereTardyUser)
            throw new NoTardinessException();
        return null;
    }

    @Override
    public Park favouriteParks() throws NoPickUpsMadeException {
        if(favouritePark == null)
            throw new NoPickUpsMadeException();
        return favouritePark;
    }

    /**
     * Returns true if the bike has already been used.
     * @return true if bike was used.
     */
	private boolean hasBikeBeenUsed(String idBike) {
        return bikes.find(idBike.toLowerCase()).hasBeenUsed();
	}

	/**
	 * Returns true if the user has already used the system.
	 * @return true if the user used the system.
	 */
	private boolean hasUserUsedSystem(String userID) {
		return users.find(userID.toLowerCase()).hasUsedSystem();
	}

    /**
     * Returns true if the user is not registered in the system.
     * @param idUser user's identification
     * @return true if the user is not registered
     */
    private boolean userNotFound(String idUser) {
        return users.find(idUser.toLowerCase()) == null;
    }

    /**
     * Returns true if the park is not registered in the system.
     * @param idPark park's identification
     * @return true if park is not registered
     */
    private boolean parkNotFound(String idPark) {
        return park == null || !park.getID().equalsIgnoreCase(idPark);
    }

    /**
     * Returns true if the bike is not registered in the system.
     * @param bikeID bike's identification
     * @return true if bike is not registered
     */
    private boolean bikeNotFound(String bikeID) {
        return bikes.find(bikeID.toLowerCase()) == null;
    }

    /**
     * Returns true if the bike is being used.
     * @param idBike bike's identification
     * @return true if bike is being used.
     */
    private boolean bikeIsOnTheMove(String idBike) {
        return bikes.find(idBike.toLowerCase()).isOnTheMove();
    }

    /**
     * Returns true if the user is using a bike.
     * @param idUser user's identification
     * @return true if the user is using a bike.
     */
    private boolean userOnTheMove(String idUser) {
        return users.find(idUser.toLowerCase()).isOnTheMove();
    }

    /**
     * Returns true if the user has less funds than the minimum necessary to execute a PickUp.
     * @param idUser user's identification
     * @return true if the user has insufficient funds.
     */
    private boolean insufficientBalance(String idUser) {
        return users.find(idUser.toLowerCase()).getBalance()< MIN_PICKUP_BALANCE;
    }

    /**
     * Returns true if the bike is being used for the first time.
     * @param idBike bike's identification
     * @return true if the bike is being used for the first time.
     */
    private boolean isBikeOnFirstPickUp(String idBike) {
        return bikes.find(idBike.toLowerCase()).isBikeOnFirstPickUp();
    }

    /**
     * Returns true if the user is using a bike for the first time.
     * @param idUser user's identification.
     * @return true if the user is using a bike for the first time.
     */
    private boolean isUserIsOnFirstPickUp(String idUser) {
        return users.find(idUser.toLowerCase()).isUserIsOnFirstPickUp();
    }

    /**
     * Returns true if the the value is invalid (below or equal to zero)
     * @param value value to be evaluated
     * @return true if <= 0
     */
    private boolean invalidData(int value) {
        return value <= 0;
    }
}
