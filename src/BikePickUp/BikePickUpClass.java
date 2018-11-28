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
     * All of the parks with the most number of pickups.
     */
	private OrderedDictionary<String,Park> topParks;

    /**
     * The current maximum number of pickups made in at least one park.
     */
	private int currentMaxPickUps;

	/**
	 * A BTS filled with all the users the have at least one point organized by descending order (multiplying by minus 1 to insert and to consult).
	 */
	private OrderedDictionary<Integer,List<User>> delayedUsers;

	/**
	 * System's constructor
	 */
    public BikePickUpClass(){
        users = new ChainedHashTable<>();
        bikes = new ChainedHashTable<>();
        parks = new ChainedHashTable<>();
        topParks = new BinarySearchTree<>();
        currentMaxPickUps = 0;
        delayedUsers = new BinarySearchTree<>();
    }

    @Override
    public void addUser(String userID, String nif, String email,String phone, String name, String address) throws UserAlreadyExistsException {
        UserSet user = users.find(userID.toLowerCase());
    	if(!userNotFound(user))
            throw new UserAlreadyExistsException();
        user = new UserClass(nif,email,phone,name,address);
    	users.insert(userID,user);
    }

    @Override
    public void removeUser(String userID) throws UserNotFoundException, UserUsedSystemException {
        UserSet user = users.find(userID.toLowerCase());
    	if(userNotFound(user))
            throw new UserNotFoundException();
        if(hasUserUsedSystem(user))
        	throw new UserUsedSystemException();
        users.remove(userID.toLowerCase());
    }

	@Override
	public User getUserInfo(String userID) throws UserNotFoundException {
		UserSet user = users.find(userID.toLowerCase());
    	if(userNotFound(user))
			throw new UserNotFoundException();
		return user;
	}

	@Override
	public void addPark(String parkID, String name, String address) throws ParkAlreadyExistsException {
		ParkSet park = parks.find(parkID.toLowerCase());
        if(!parkNotFound(park))
			throw new ParkAlreadyExistsException();
		park = new ParkClass(name,address);
		parks.insert(parkID.toLowerCase(), park);
	}

	@Override
	public void addBike(String bikeID, String parkID, String bikeLicense)
			throws BikeAlreadyExistsException, ParkNotFoundException {
		ParkSet park = parks.find(parkID.toLowerCase());
		BikeSet bike = bikes.find(bikeID.toLowerCase());
        if(!bikeNotFound(bike))
			throw new BikeAlreadyExistsException();
		if(parkNotFound(park))
			throw new ParkNotFoundException();
		bike = new BikeClass(bikeID, parkID,bikeLicense);
		bikes.insert(bikeID,bike);
		park.addBike(bike);
	}

    @Override
	public void removeBike(String bikeID) throws BikeNotFoundException,UsedBikeException {
		BikeSet bike = bikes.find(bikeID.toLowerCase());
    	if(bikeNotFound(bike))
			throw new BikeNotFoundException();
		if(hasBikeBeenUsed(bike))
			throw new UsedBikeException();
		bike = bikes.remove(bikeID.toLowerCase());
		parks.find(bike.getParkID().toLowerCase()).removeBike(bikeID.toLowerCase());
	}

    @Override
	public Park getParkInfo(String parkID) throws ParkNotFoundException {
        ParkSet park = parks.find(parkID.toLowerCase());
        if(parkNotFound(park))
			throw new ParkNotFoundException();
		return park;
	}

	@Override
	public void pickUp(String bikeID, String userID) throws BikeNotFoundException,BikeOnTheMoveException,UserNotFoundException,UserOnTheMoveException,InsufficientBalanceException {
    	BikeSet bike = bikes.find(bikeID.toLowerCase());
    	UserSet user = users.find(userID.toLowerCase());
    	if(bikeNotFound(bike))
    		throw new BikeNotFoundException();
    	if(bikeIsOnTheMove(bike))
    		throw new BikeOnTheMoveException();
    	if(userNotFound(user))
    		throw new UserNotFoundException();
    	if(userOnTheMove(user))
    		throw new UserOnTheMoveException();
    	if(insufficientBalance(user))
    		throw new InsufficientBalanceException();
		PickUpSet pickUp = new PickUpClass(bikeID.toLowerCase(),userID.toLowerCase(),bike.getParkID());
		user.pickUp(pickUp);
        bike.pickUp(pickUp);
        parks.find(bike.getParkID()).pickUp(bikeID.toLowerCase());
	}

    @Override
	public User pickDown(String bikeID, String finalParkID, int minutes) throws BikeNotFoundException, BikeStoppedException, ParkNotFoundException, InvalidDataException {
    	ParkSet park = parks.find(finalParkID.toLowerCase());
    	BikeSet bike = bikes.find(bikeID.toLowerCase());
        if(bikeNotFound(bike))
    		throw new BikeNotFoundException();
    	if(!bikeIsOnTheMove(bike))
    		throw new BikeStoppedException();
    	if(parkNotFound(park))
    		throw new ParkNotFoundException();
    	if(invalidData(minutes))
    		throw new InvalidDataException();
    	String userID = bike.getUserID();
    	UserSet user = users.find(userID.toLowerCase());
    	int oldPoints = user.getPoints();
    	user.pickDown(finalParkID,minutes);
    	bike.pickDown(finalParkID,minutes);
    	park.pickDown(bike);
    	updateTopParks(park,Integer.parseInt(park.getNPickUps()));
    	updateDelayedUsers(user,oldPoints);

    	return user;
	}

    private void updateDelayedUsers(UserSet user, int oldPoints) {
    	List<User> userList;
    	if(oldPoints < user.getPoints()) {
    		userList = delayedUsers.find(-oldPoints);
    		if(userList != null) {
    			userList.remove(user);
    			if (userList.isEmpty())
    				delayedUsers.remove(-oldPoints);
    		}
    	
        userList = delayedUsers.find(-user.getPoints());
        if (userList == null)
        	userList = new DoublyLinkedList<>();
        userList.addLast(user);
        delayedUsers.insert(-user.getPoints(),userList);
    	}
    }

    private void updateTopParks(Park park, int nPickups) {
        if(nPickups > currentMaxPickUps) {
            topParks = new BinarySearchTree<>();
            currentMaxPickUps = nPickups;
            topParks.insert(park.getName(), park);
        }
        else if(nPickups == currentMaxPickUps)
            topParks.insert(park.getName(),park);
    }

    @Override
	public User chargeUser(String userID, int value) throws UserNotFoundException, InvalidDataException {
		UserSet user = users.find(userID.toLowerCase());
    	if(userNotFound(user))
			throw new UserNotFoundException();
		if(invalidData(value))
			throw new InvalidDataException();
		user.charge(value);
        return user;
    }

    @Override
	public Iterator<PickUp> getBikePickUps(String bikeID) throws BikeNotFoundException, BikeNotUsedException, BikeOnFirstPickUpException {
		BikeSet bike = bikes.find(bikeID.toLowerCase());
    	if(bikeNotFound(bike))
			throw new BikeNotFoundException();
		if(!hasBikeBeenUsed(bike))
			throw new BikeNotUsedException();
		if(isBikeOnFirstPickUp(bike))
			throw new BikeOnFirstPickUpException();
        return bike.getBikePickUps();
	}

    @Override
	public Iterator<PickUp> getUserPickUps(String userID) throws UserNotFoundException, UserNotUsedSystemException, UserOnFirstPickUpException {
		UserSet user = users.find(userID.toLowerCase());
    	if(userNotFound(user))
			throw new UserNotFoundException();
		if(!hasUserUsedSystem(user))
			throw new UserNotUsedSystemException();
		if(isUserIsOnFirstPickUp(user))
			throw new UserOnFirstPickUpException();
        return user.getUserPickUps();
	}

    @Override
	public void bikeParked(String bikeID, String parkID) throws BikeNotFoundException, ParkNotFoundException, BikeNotInParkException {
		ParkSet park = parks.find(parkID.toLowerCase());
		BikeSet bike  = bikes.find(bikeID.toLowerCase());
        if(bikeNotFound(bike))
			throw new BikeNotFoundException();
		if(parkNotFound(park))
			throw new ParkNotFoundException();
		if(!park.isBikeInPark(bikeID.toLowerCase()))
			throw new BikeNotInParkException();
	}

    @Override
    public Iterator<Entry<Integer,List<User>>> listDelayed() throws NoTardinessException{
        if(delayedUsers.isEmpty())
            throw new NoTardinessException();
        return delayedUsers.iterator();
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
	 * @param bike Bike
     */
	private boolean hasBikeBeenUsed(BikeSet bike) {
        return bike.hasBeenUsed();
	}

	/**
	 * Returns true if the user has already used the system.
	 * @return true if the user used the system.
	 * @param user User
	 */
	private boolean hasUserUsedSystem(UserSet user) {
		return user.hasUsedSystem();
	}

    /**
     * Returns true if the user is not registered in the system.
     * @param user User
     * @return true if the user is not registered
     */
    private boolean userNotFound(UserSet user) {
        return user == null;
    }

    /**
     * Returns true if the park is not registered in the system.
     * @param park Park
     * @return true if park is not registered
     */
    private boolean parkNotFound(ParkSet park) {
    	return park == null;
    }

    /**
     * Returns true if the bike is not registered in the system.
     * @param bike Bike
     * @return true if bike is not registered
     */
    private boolean bikeNotFound(BikeSet bike) {
        return bike == null;
    }

    /**
     * Returns true if the bike is being used.
     * @param bike Bike
     * @return true if bike is being used.
     */
    private boolean bikeIsOnTheMove(BikeSet bike) {
        return bike.isOnTheMove();
    }

    /**
     * Returns true if the user is using a bike.
     * @param user User
     * @return true if the user is using a bike.
     */
    private boolean userOnTheMove(UserSet user) {
        return user.isOnTheMove();
    }

    /**
     * Returns true if the user has less funds than the minimum necessary to execute a PickUp.
     * @param user User
     * @return true if the user has insufficient funds.
     */
    private boolean insufficientBalance(UserSet user) {
        return user.getBalance()< MIN_PICKUP_BALANCE;
    }

    /**
     * Returns true if the bike is being used for the first time.
     * @param bike Bike
     * @return true if the bike is being used for the first time.
     */
    private boolean isBikeOnFirstPickUp(BikeSet bike) {
        return bike.isBikeOnFirstPickUp();
    }

    /**
     * Returns true if the user is using a bike for the first time.
     * @param user User.
     * @return true if the user is using a bike for the first time.
     */
    private boolean isUserIsOnFirstPickUp(UserSet user) {
        return user.isUserIsOnFirstPickUp();
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