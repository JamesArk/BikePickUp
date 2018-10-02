package bikePickUp.Park;

import bikePickUp.Bike.Bike;
import bikePickUp.dataStructures.Queue;
import bikePickUp.dataStructures.QueueInArray;

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
	public Queue<String> getParkInfo() {
		Queue<String> parkInfo = new QueueInArray<>();
		parkInfo.enqueue(name);
		parkInfo.enqueue(address);
		parkInfo.enqueue(Integer.toString(nBikes));
		return parkInfo;
	}

	@Override
	public void addBike(Bike b) {
		this.bike = b;
		nBikes++;
	}
}
