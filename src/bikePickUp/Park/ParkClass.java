package bikePickUp.Park;

import bikePickUp.Bike.Bike;
import bikePickUp.dataStructures.*;

public class ParkClass implements Park {

    private String parkID,name,address;
    private Bike bike;
    private int nBikes;

    public ParkClass(String parkID, String name, String address) {
        this.parkID = parkID;
        this.name = name;
        this.address = address;
        bike = null;
        nBikes = 0;
    }

	@Override
	public String getID() {
		return parkID;
	}

	@Override
	public Iterator<String> getParkInfo() {
		List<String> list = new DoublyLinkedList<>();
		list.addLast(name);
		list.addLast(address);
		list.addLast(Integer.toString(nBikes));
		return list.iterator();
	}

	@Override
	public void addBike(Bike b) {
		this.bike = b;
		nBikes++;
	}

	@Override
	public void pickUp() {
		bike = null;
	}

	@Override
	public void pickDown(Bike bike) {
		this.bike = bike;
		
	}

	@Override
	public boolean isBikeInPark() {
		return bike != null;
	}
}
