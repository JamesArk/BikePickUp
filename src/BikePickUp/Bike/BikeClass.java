package BikePickUp.Bike;

import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;

public class BikeClass implements Bike {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id, parkID,bikeLicense;
    private List<PickUp> pickUps;
    private boolean isOnTheMove;

    public BikeClass(String id, String parkID, String bikeLicense) {
        this.id = id;
        this.parkID = parkID;
        this.bikeLicense = bikeLicense;
        this.pickUps = new DoublyLinkedList<>();
        isOnTheMove = false;
    }

	@Override
	public String getID() {
		return id;
	}

    @Override
    public void pickUp(PickUp pickUp) {
        pickUps.addLast(pickUp);
        isOnTheMove = true;
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
        pickUp.setCost();
        isOnTheMove = false;
    }

	@Override
	public boolean isOnTheMove() {
		return isOnTheMove;
	}

	@Override
	public Iterator<PickUp> getBikePickUps() {
		return pickUps.iterator();
	}

	@Override
	public String getBikeLicense() {
		return bikeLicense;
	}

}
