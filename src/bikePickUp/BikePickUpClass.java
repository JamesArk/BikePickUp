package bikePickUp;


import bikePickUp.Exceptions.UserAlreadyExistsException;
import bikePickUp.Exceptions.UserNotFoundException;
import bikePickUp.User.User;
import bikePickUp.User.UserClass;


public class BikePickUpClass implements BikePickUp {

    private User user;

    public BikePickUpClass(){
        user = null;
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
}
