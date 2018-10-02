package bikePickUp;


import bikePickUp.Bike.Bike;
import bikePickUp.Bike.BikeClass;
import bikePickUp.Exceptions.BikeAlreadyExistsException;
import bikePickUp.Exceptions.BikeNotFoundException;
import bikePickUp.Exceptions.ParkAlreadyExistsException;
import bikePickUp.Exceptions.ParkNotFoundException;
import bikePickUp.Exceptions.UserAlreadyExistsException;
import bikePickUp.Exceptions.UserNotFoundException;
import bikePickUp.Park.Park;
import bikePickUp.Park.ParkClass;
import bikePickUp.User.User;
import bikePickUp.User.UserClass;
import bikePickUp.dataStructures.Queue;


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
    public void removeUser(String idUser) throws UserNotFoundException {
        if(user == null || !user.getID().equals(idUser))
            throw new UserNotFoundException();
        user = null;
    }

	@Override
	public Queue<String> getUserInfo(String userID) throws UserNotFoundException {
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
		if(park == null || park.getID().equals(idPark))
			throw new ParkNotFoundException();
		bike = new BikeClass(idBike,idPark,bikeLicense);
		park.addBike(bike);
		
	}

	@Override
	public void removeBike(String bikeID) throws BikeNotFoundException {
		if(bike == null || !bike.getID().equals(bikeID))
			throw new BikeNotFoundException();
		bike = null;
	}

	@Override
	public Queue<String> getParkInfo(String parkID) throws ParkNotFoundException {
		if(park == null || !park.getID().equals(parkID))
			throw new ParkNotFoundException();
		return park.getParkInfo();
		
	}
}
