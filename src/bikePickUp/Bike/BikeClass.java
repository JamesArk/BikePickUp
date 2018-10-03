package bikePickUp.Bike;

import bikePickUp.dataStructures.DoublyLinkedList;
import bikePickUp.dataStructures.List;

public class BikeClass implements Bike {

    private String id, parkID,bikeLicense;
    private List<PickUp> pickUps;

    public BikeClass(String id, String parkID, String bikeLicense) {
        this.id = id;
        this.parkID = parkID;
        this.bikeLicense = bikeLicense;
        this.pickUps = new DoublyLinkedList<>();
    }

	@Override
	public String getID() {
		return id;
	}

    @Override
    public void pickUp(PickUp pickUp) {
        pickUps.addLast(pickUp);
    }

    @Override
    public boolean hasBeenUsed() {
        return !pickUps.isEmpty();
    }

    @Override
    public String getParkID() {
        return parkID;
    }

    @Override
    public void pickDown(String finalParkID, int minutes) {
        PickUp pickUp = pickUps.getLast();
        pickUp.setFinalParkID(finalParkID);
        pickUp.setMinutes(minutes);
    }
}
