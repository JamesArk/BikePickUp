package bikePickUp;

public interface BikePickUp {
    void addUser(String userID, String nif, String email,String phone, String name, String address);

    void removeUser(String idUser);
}
