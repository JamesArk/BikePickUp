package bikePickUp.Bike;

public class BikeClass implements Bike {

    private String id, parkID,bikeLicense;

    public BikeClass(String id, String parkID, String bikeLicense) {
        this.id = id;
        this.parkID = parkID;
        this.bikeLicense = bikeLicense;
    }

	@Override
	public String getID() {
		return id;
	}
}
