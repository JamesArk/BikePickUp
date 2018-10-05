package bikePickUp;


import bikePickUp.Bike.*;
import bikePickUp.Exceptions.*;
import bikePickUp.Park.*;
import bikePickUp.User.*;
import bikePickUp.dataStructures.Iterator;



public class BikePickUpClass implements BikePickUp {

    private User user;
    private Park park;
    private Bike bike;

    

    public BikePickUpClass(){
        user = null;
        park = null;
        bike = null;
    }

    @Override
    public void addUser(String userID, String nif, String email,String phone, String name, String address) throws UserAlreadyExistsException {
        if(user != null && user.getID().equals(userID))
            throw new UserAlreadyExistsException();
        user = new UserClass(userID,nif,email,phone,name,address);
    }

    @Override
    public void removeUser(String idUser) throws UserNotFoundException, UserUsedSystemException {
        if(user == null || !user.getID().equals(idUser))
            throw new UserNotFoundException();
        if(hasUserUsedSystem())
        	throw new UserUsedSystemException();
        user = null;
    }

	private boolean hasUserUsedSystem() {
		return user.hasUsedSystem();
	}

	@Override
	public Iterator<String> getUserInfo(String userID) throws UserNotFoundException {
		if(user == null||!user.getID().equals(userID))
			throw new UserNotFoundException();
		return user.getUserInfo();
	}

	@Override
	public void addPark(String idPark, String name, String address) throws ParkAlreadyExistsException {
		if(park!=null && park.getID().equals(idPark))
			throw new ParkAlreadyExistsException();
		park = new ParkClass(idPark,name,address);
		
	}

	@Override
	public void addBike(String idBike, String idPark, String bikeLicense)
			throws BikeAlreadyExistsException, ParkNotFoundException {
		if(bike != null && bike.getID().equals(idBike))
			throw new BikeAlreadyExistsException();
		if(park == null || !park.getID().equals(idPark))
			throw new ParkNotFoundException();
		bike = new BikeClass(idBike,idPark,bikeLicense);
		park.addBike(bike);
		
	}

	@Override
	public void removeBike(String bikeID) throws BikeNotFoundException,UsedBikeException {
		if(bike == null || !bike.getID().equals(bikeID))
			throw new BikeNotFoundException();
		if(hasBikeBeenUsed())
			throw new UsedBikeException();
		bike = null;
	}

	@Override
	public Iterator<String> getParkInfo(String parkID) throws ParkNotFoundException {
		if(park == null || !park.getID().equals(parkID))
			throw new ParkNotFoundException();
		return park.getParkInfo();
		
	}

	@Override
	public void pickUp(String idBike, String idUser) throws BikeNotFoundException,BikeOnTheMoveException,UserNotFoundException,UserOnTheMoveException,InsufficientBalanceException {
    	if(bike == null  || !bike.getID().equals(idBike))
    		throw new BikeNotFoundException();
    	if(bike.getMoveSituation())
    		throw new BikeOnTheMoveException();
    	if((user == null || !user.getID().equals(idUser)))
    		throw new UserNotFoundException();
    	if(user.getMoveSituation())
    		throw new UserOnTheMoveException();
    	if(user.getBalance() < MIN_PICKUP_BALANCE)
    		throw new UserOnTheMoveException();
		PickUp pickUp = new PickUpClass(idBike,idUser,bike.getParkID());
		user.pickUp(pickUp);
		bike.pickUp(pickUp);
		park.pickUp();
	}

	@Override
	public void pickDown(String idBike, String finalParkID, int minutes) throws BikeNotFoundException, BikeStoppedException, ParkNotFoundException, InvalidDataException {
    	if(bike == null || !bike.getID().equals(idBike))
    		throw new BikeNotFoundException();
    	if(!bike.getMoveSituation())
    		throw new BikeStoppedException();
    	if(!park.getID().equals(finalParkID))
    		throw new ParkNotFoundException();
    	if(minutes <= 0)
    		throw new InvalidDataException();
    	user.pickDown(finalParkID,minutes);
    	bike.pickDown(finalParkID,minutes);
    	park.pickDown(bike);
    	
	}

	@Override
	public void chargeUser(String idUser, int value) throws UserNotFoundException, InvalidDataException {
		if(user == null || !user.getID().equals(idUser))
			throw new UserNotFoundException();
		if(value <= 0)
			throw new InvalidDataException();
		user.charge(value);
	}

	private boolean hasBikeBeenUsed() {
		return bike.hasBeenUsed();
	}

	@Override
	public Iterator<PickUp> getBikePickUps(String idBike) throws BikeNotFoundException, BikeNotUsedException, BikeOnFirstPickUpException {
		if(bike == null || !bike.getID().equals(idBike))
			throw new BikeNotFoundException();
		if(!bike.hasBeenUsed())
			throw new BikeNotUsedException();
		if(bike.getMoveSituation())
			throw new BikeOnFirstPickUpException();
		return bike.getBikePickUps();
	}

	@Override
	public Iterator<PickUp> getUserPickUps(String idUser)
			throws UserNotFoundException, UserNotUsedSystemException, UserOnFirstPickUpException {
		
		if(user == null || !user.getID().equals(idUser))
			throw new UserNotFoundException();
		if(!user.hasUsedSystem())
			throw new UserNotUsedSystemException();
		if(user.getMoveSituation())
			throw new UserOnFirstPickUpException();
		return user.getUserPickUps();
	}

	@Override
	public int getUserBalance() {
		return user.getBalance();
	}

	@Override
	public int getUserPoints() {
		return user.getPoints();
	}

	@Override
	public void isBikeParked(String idBike, String idPark) throws BikeNotFoundException, ParkNotFoundException, BikeNotInParkException {
		if(bike == null || !bike.getID().equals(idBike))
			throw new BikeNotFoundException();
		if(park == null || !park.getID().equals(idPark))
			throw new ParkNotFoundException();
		if(!park.isBikeInPark())
			throw new BikeNotInParkException();
		
	}
}
