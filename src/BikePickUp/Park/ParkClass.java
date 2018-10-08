package BikePickUp.Park;

import BikePickUp.Bike.Bike;
import dataStructures.*;

public class ParkClass implements Park {

    /**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	private String parkID,name,address;
    private Bike bike;
    private int nBikes;
    private int nPickUps;

    public ParkClass(String parkID, String name, String address) {
        this.parkID = parkID;
        this.name = name;
        this.address = address;
        bike = null;
        nBikes = 0;
        nPickUps = 0;
    }

	@Override
	public String getID() {
		return parkID;
	}

	@Override
	public Iterator<String> getParkInfo() {
		return getInfo(nBikes);
	}

	private Iterator<String> getInfo(int i) {
        List<String> list = new DoublyLinkedList<>();
        list.addLast(name);
        list.addLast(address);
        list.addLast(Integer.toString(i));
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
		nBikes--;
		nPickUps++;
	}

	@Override
	public void pickDown(Bike bike) {
		this.bike = bike;
		nBikes++;
	}

	@Override
	public boolean isBikeInPark() {
		return bike != null;
	}

    @Override
    public Iterator<String> getFavouriteParkInfo() {
		return getInfo(nPickUps);
    }

    @Override
    public void removeBike() {
        nBikes--;
        bike = null;
    }
}
