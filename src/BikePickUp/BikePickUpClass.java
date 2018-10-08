package BikePickUp;

import BikePickUp.Bike.*;
import BikePickUp.Exceptions.*;
import BikePickUp.Park.*;
import BikePickUp.User.*;
import dataStructures.Iterator;



public class BikePickUpClass implements BikePickUp {

	static final long serialVersionUID = 0L;
	
	private User user;
    private Park park;
    private Bike bike;
    private User tardyUser;
    private Park favouritePark;

    public BikePickUpClass(){
        user = null;
        park = null;
        bike = null;
        tardyUser = null;
    }

    @Override
    public void addUser(String userID, String nif, String email,String phone, String name, String address) throws UserAlreadyExistsException {
        if(!userNotFound(userID))
            throw new UserAlreadyExistsException();
        user = new UserClass(userID,nif,email,phone,name,address);
    }

    @Override
    public void removeUser(String idUser) throws UserNotFoundException, UserUsedSystemException {
        if(userNotFound(idUser))
            throw new UserNotFoundException();
        if(hasUserUsedSystem())
        	throw new UserUsedSystemException();
        user = null;
    }

	@Override
	public Iterator<String> getUserInfo(String idUser) throws UserNotFoundException {
		if(userNotFound(idUser))
			throw new UserNotFoundException();
		return user.getUserInfo();
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
		bike = new BikeClass(idBike,idPark,bikeLicense);
		park.addBike(bike);
		
	}

    @Override
	public void removeBike(String bikeID) throws BikeNotFoundException,UsedBikeException {
		if(bikeNotFound(bikeID))
			throw new BikeNotFoundException();
		if(hasBikeBeenUsed())
			throw new UsedBikeException();
		bike = null;
		park.removeBike();
	}

    @Override
	public Iterator<String> getParkInfo(String parkID) throws ParkNotFoundException {
		if(parkNotFound(parkID))
			throw new ParkNotFoundException();
		return park.getParkInfo();
		
	}

	@Override
	public void pickUp(String idBike, String idUser) throws BikeNotFoundException,BikeOnTheMoveException,UserNotFoundException,UserOnTheMoveException,InsufficientBalanceException {
    	if(bikeNotFound(idBike))
    		throw new BikeNotFoundException();
    	if(bike.isOnTheMove())
    		throw new BikeOnTheMoveException();
    	if(userNotFound(idUser))
    		throw new UserNotFoundException();
    	if(user.isOnTheMove())
    		throw new UserOnTheMoveException();
    	if(user.getBalance() < MIN_PICKUP_BALANCE)
    		throw new InsufficientBalanceException();
		PickUp pickUp = new PickUpClass(idBike,idUser,bike.getParkID());
		favouritePark = park;
		user.pickUp(pickUp);
		bike.pickUp(pickUp);
		park.pickUp();
	}

	@Override
	public void pickDown(String idBike, String finalParkID, int minutes) throws BikeNotFoundException, BikeStoppedException, ParkNotFoundException, InvalidDataException {
    	if(bikeNotFound(idBike))
    		throw new BikeNotFoundException();
    	if(!bike.isOnTheMove())
    		throw new BikeStoppedException();
    	if(parkNotFound(finalParkID))
    		throw new ParkNotFoundException();
    	if(minutes <= 0)
    		throw new InvalidDataException();
    	user.pickDown(finalParkID,minutes);
    	if(user.isThereTardiness())
    	    tardyUser = user;
    	bike.pickDown(finalParkID,minutes);
    	park.pickDown(bike);
    	
	}

	@Override
	public void chargeUser(String idUser, int value) throws UserNotFoundException, InvalidDataException {
		if(userNotFound(idUser))
			throw new UserNotFoundException();
		if(invalidData(value))
			throw new InvalidDataException();
		user.charge(value);
	}

    @Override
	public Iterator<PickUp> getBikePickUps(String idBike) throws BikeNotFoundException, BikeNotUsedException, BikeOnFirstPickUpException {
		if(bikeNotFound(idBike))
			throw new BikeNotFoundException();
		if(!bike.hasBeenUsed())
			throw new BikeNotUsedException();
		if(bike.isBikeOnFirstPickUp())
			throw new BikeOnFirstPickUpException();
		return bike.getBikePickUps();
	}

	@Override
	public Iterator<PickUp> getUserPickUps(String idUser)
			throws UserNotFoundException, UserNotUsedSystemException, UserOnFirstPickUpException {
		
		if(userNotFound(idUser))
			throw new UserNotFoundException();
		if(!user.hasUsedSystem())
			throw new UserNotUsedSystemException();
		if(user.isUserIsOnFirstPickUp())
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
		if(bikeNotFound(idBike))
			throw new BikeNotFoundException();
		if(parkNotFound(idPark))
			throw new ParkNotFoundException();
		if(!park.isBikeInPark())
			throw new BikeNotInParkException();
	}

    @Override
    public Iterator<String> listDelayed() throws NoTardinessException{
        if(tardyUser == null)
            throw new NoTardinessException();
        return tardyUser.getUserInfo();
    }

    @Override
    public Iterator<String> favouriteParks() throws NoPickUpsMadeException {
        if(favouritePark == null)
            throw new NoPickUpsMadeException();
        return favouritePark.getFavouriteParkInfo();
    }

	private boolean hasBikeBeenUsed() {
		return bike.hasBeenUsed();
	}


    private boolean userNotFound(String idUser) {
        return user == null || !user.getID().equalsIgnoreCase(idUser);
    }

    private boolean hasUserUsedSystem() {
        return user.hasUsedSystem();
    }

    private boolean parkNotFound(String idPark) {
        return park == null || !park.getID().equalsIgnoreCase(idPark);
    }

    private boolean bikeNotFound(String bikeID) {
        return bike == null || !bike.getID().equalsIgnoreCase(bikeID);
    }

    private boolean invalidData(int value) {
        return value <= 0;
    }
}
