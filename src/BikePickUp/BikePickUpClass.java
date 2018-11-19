package BikePickUp;

import BikePickUp.Bike.*;
import BikePickUp.Exceptions.*;
import BikePickUp.PickUp.PickUp;
import BikePickUp.PickUp.PickUpClass;
import BikePickUp.Park.*;
import BikePickUp.PickUp.PickUpSet;
import BikePickUp.User.*;
import dataStructures.*;

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
	 * All the parks in the system.
	 */
	private Dictionary<String,ParkSet> parks;

    /**
     * True if there is at least one user that exceeded 60 in duration of a pickup.
     */
	private boolean isThereTardyUser;

    /**
     * All of the parks with the most number of pickups.
     */
	private OrderedDictionary<String,Park> topParks;

    /**
     * The current maximum number of pickups made in at least one park.
     */
	private int currentMaxPickUps;

	private OrderedDictionary<Integer,List<User>> delayedUsers;

	/**
	 * System's constructor
	 */
    public BikePickUpClass(){
        isThereTardyUser = false;
        users = new ChainedHashTable<>();
        bikes = new ChainedHashTable<>();
        parks = new ChainedHashTable<>();
        topParks = new BinarySearchTree<>();
        currentMaxPickUps = 0;
        delayedUsers = new BinarySearchTree<>();
    }

    @Override
    public void addUser(String userID, String nif, String email,String phone, String name, String address) throws UserAlreadyExistsException {
        if(!userNotFound(userID))
            throw new UserAlreadyExistsException();
        users.insert(userID,new UserClass(nif,email,phone,name,address));
    }

    @Override
    public void removeUser(String userID) throws UserNotFoundException, UserUsedSystemException {
        if(userNotFound(userID))
            throw new UserNotFoundException();
        if(hasUserUsedSystem(userID))
        	throw new UserUsedSystemException();
        users.remove(userID.toLowerCase());
    }

	@Override
	public User getUserInfo(String userID) throws UserNotFoundException {
		if(userNotFound(userID))
			throw new UserNotFoundException();
		return users.find(userID.toLowerCase());
	}

	@Override
	public void addPark(String parkID, String name, String address) throws ParkAlreadyExistsException {
		ParkSet park = parks.find(parkID.toLowerCase());
        if(!parkNotFound(park))
			throw new ParkAlreadyExistsException();
		park = new ParkClass(parkID,name,address);
		parks.insert(parkID, park);
	}

	@Override
	public void addBike(String bikeID, String parkID, String bikeLicense)
			throws BikeAlreadyExistsException, ParkNotFoundException {
		ParkSet park = parks.find(parkID.toLowerCase());
        if(!bikeNotFound(bikeID))
			throw new BikeAlreadyExistsException();
		if(parkNotFound(park))
			throw new ParkNotFoundException();
		BikeSet bike = new BikeClass(bikeID, parkID,bikeLicense);
		bikes.insert(bikeID,bike);
		park.addBike(bike);
	}

    @Override
	public void removeBike(String bikeID) throws BikeNotFoundException,UsedBikeException {
		if(bikeNotFound(bikeID))
			throw new BikeNotFoundException();
		if(hasBikeBeenUsed(bikeID))
			throw new UsedBikeException();
		parks.find(bikes.remove(bikeID.toLowerCase()).getParkID().toLowerCase()).removeBike(bikeID.toLowerCase());
	}

    @Override
	public Park getParkInfo(String parkID) throws ParkNotFoundException {
        ParkSet park = parks.find(parkID.toLowerCase());
        if(parkNotFound(park))
			throw new ParkNotFoundException();

		return park;
	}

	@Override
	public void pickUp(String idBike, String userID) throws BikeNotFoundException,BikeOnTheMoveException,UserNotFoundException,UserOnTheMoveException,InsufficientBalanceException {
    	if(bikeNotFound(idBike))
    		throw new BikeNotFoundException();
    	if(bikeIsOnTheMove(idBike))
    		throw new BikeOnTheMoveException();
    	if(userNotFound(userID))
    		throw new UserNotFoundException();
    	if(userOnTheMove(userID))
    		throw new UserOnTheMoveException();
    	if(insufficientBalance(userID))
    		throw new InsufficientBalanceException();
		PickUpSet pickUp = new PickUpClass(idBike.toLowerCase(),userID.toLowerCase(),bikes.find(idBike).getParkID());
		users.find(userID.toLowerCase()).pickUp(pickUp);
        bikes.find(idBike.toLowerCase()).pickUp(pickUp);
        parks.find(bikes.find(idBike.toLowerCase()).getParkID()).pickUp(idBike.toLowerCase());
	}

    @Override
	public User pickDown(String idBike, String finalParkID, int minutes) throws BikeNotFoundException, BikeStoppedException, ParkNotFoundException, InvalidDataException {
    	ParkSet park  = parks.find(finalParkID.toLowerCase());
        if(bikeNotFound(idBike))
    		throw new BikeNotFoundException();
    	if(!bikeIsOnTheMove(idBike))
    		throw new BikeStoppedException();
    	if(parkNotFound(park))
    		throw new ParkNotFoundException();
    	if(invalidData(minutes))
    		throw new InvalidDataException();
    	String userID = bikes.find(idBike.toLowerCase()).getUserID();
    	UserSet user = users.find(userID);
    	int oldPoints = user.getPoints();
    	user.pickDown(finalParkID,minutes);
    	bikes.find(idBike.toLowerCase()).pickDown(finalParkID,minutes);
    	park.pickDown(bikes.find(idBike.toLowerCase()));
    	updateTopParks(park,Integer.parseInt(park.getNPickUps()));
    	updateDelayedUsers(user,oldPoints);

    	return users.find(userID);
	}

    private void updateDelayedUsers(UserSet user, int oldPoints) {
        List<User> userList = delayedUsers.find(oldPoints);
        if(userList != null) {
            userList.remove(user);
            if (userList.isEmpty())
                delayedUsers.remove(oldPoints);
        }
            userList = delayedUsers.find(user.getPoints());
            if (userList == null)
                userList = new DoublyLinkedList<>();
        userList.addLast(user);
        delayedUsers.insert(user.getPoints(),userList);
    }

    private void updateTopParks(Park park, int nPickups) {
        if(nPickups > currentMaxPickUps) {
            topParks = new BinarySearchTree<>();
            currentMaxPickUps = nPickups;
        }
        else if(nPickups == currentMaxPickUps)
            topParks.insert(park.getName(),park);
    }

    @Override
	public User chargeUser(String userID, int value) throws UserNotFoundException, InvalidDataException {
		if(userNotFound(userID))
			throw new UserNotFoundException();
		if(invalidData(value))
			throw new InvalidDataException();
		users.find(userID.toLowerCase()).charge(value);
        return users.find(userID.toLowerCase());
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
	public Iterator<PickUp> getUserPickUps(String userID)
			throws UserNotFoundException, UserNotUsedSystemException, UserOnFirstPickUpException {
		if(userNotFound(userID))
			throw new UserNotFoundException();
		if(!hasUserUsedSystem(userID))
			throw new UserNotUsedSystemException();
		if(isUserIsOnFirstPickUp(userID))
			throw new UserOnFirstPickUpException();
        return users.find(userID.toLowerCase()).getUserPickUps();
	}

    @Override
	public void bikeParked(String idBike, String idPark) throws BikeNotFoundException, ParkNotFoundException, BikeNotInParkException {
		ParkSet park = parks.find(idPark.toLowerCase());
        if(bikeNotFound(idBike))
			throw new BikeNotFoundException();
		if(parkNotFound(park))
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
    public Iterator<Entry<String, Park>> favouriteParks() throws NoPickUpsMadeException {
        if(topParks.isEmpty())
            throw new NoPickUpsMadeException();
        return topParks.iterator();
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
     * @param userID user's identification
     * @return true if the user is not registered
     */
    private boolean userNotFound(String userID) {
        return users.find(userID.toLowerCase()) == null;
    }

    /**
     * Returns true if the park is not registered in the system.
     * @param park park's identification
     * @return true if park is not registered
     */
    private boolean parkNotFound(ParkSet park) {
    	return park == null;
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
     * @param userID user's identification
     * @return true if the user is using a bike.
     */
    private boolean userOnTheMove(String userID) {
        return users.find(userID.toLowerCase()).isOnTheMove();
    }

    /**
     * Returns true if the user has less funds than the minimum necessary to execute a PickUp.
     * @param userID user's identification
     * @return true if the user has insufficient funds.
     */
    private boolean insufficientBalance(String userID) {
        return users.find(userID.toLowerCase()).getBalance()< MIN_PICKUP_BALANCE;
    }

    /**
     * Returns true if the bike is being used for the first time.
     * @param bikeID bike's identification
     * @return true if the bike is being used for the first time.
     */
    private boolean isBikeOnFirstPickUp(String bikeID) {
        return bikes.find(bikeID.toLowerCase()).isBikeOnFirstPickUp();
    }

    /**
     * Returns true if the user is using a bike for the first time.
     * @param userID user's identification.
     * @return true if the user is using a bike for the first time.
     */
    private boolean isUserIsOnFirstPickUp(String userID) {
        return users.find(userID.toLowerCase()).isUserIsOnFirstPickUp();
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