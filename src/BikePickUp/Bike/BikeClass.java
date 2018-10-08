package BikePickUp.Bike;

import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;

public class BikeClass implements Bike {

    /**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	private String id, parkID,bikeLicense;
    private List<PickUp> pickUps;
    private PickUp currentPickUp;

    public BikeClass(String id, String parkID, String bikeLicense) {
        this.id = id;
        this.parkID = parkID;
        this.bikeLicense = bikeLicense;
        this.pickUps = new DoublyLinkedList<>();
        this.currentPickUp = null;
    }

	@Override
	public String getID() {
		return id;
	}

    @Override
    public void pickUp(PickUp pickUp) {
        currentPickUp = pickUp;
    }

    @Override
    public boolean hasBeenUsed() {
        return !pickUps.isEmpty() || currentPickUp != null ;
    }

    @Override
    public String getParkID() {
        return parkID;
    }

    @Override
    public void pickDown(String finalParkID, int minutes) {
        currentPickUp.setFinalParkID(finalParkID);
        currentPickUp.setMinutes(minutes);
        currentPickUp.setCost();
        pickUps.addLast(currentPickUp);
        currentPickUp = null;
    }

	@Override
	public boolean isBikeOnFirstPickUp() {
		return pickUps.isEmpty() && currentPickUp != null;
	}

	@Override
	public Iterator<PickUp> getBikePickUps() {
		return pickUps.iterator();
	}

	@Override
	public String getBikeLicense() {
		return bikeLicense;
	}

    @Override
    public boolean isOnTheMove() {
        return  currentPickUp != null;
    }

}
