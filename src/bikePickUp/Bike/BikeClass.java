package bikePickUp.Bike;

public class BikeClass implements Bike {

    private String ID, parkID,bikeLicense;

    public BikeClass(String ID, String parkID, String bikeLicense) {
        this.ID = ID;
        this.parkID = parkID;
        this.bikeLicense = bikeLicense;
    }
}
