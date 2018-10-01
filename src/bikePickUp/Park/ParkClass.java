package bikePickUp.Park;

public class ParkClass implements Park {

    private String IDPark,name,address;

    public ParkClass(String IDPark, String name, String address) {
        this.IDPark = IDPark;
        this.name = name;
        this.address = address;
    }
}
