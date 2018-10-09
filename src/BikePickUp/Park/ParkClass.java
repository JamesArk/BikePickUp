package BikePickUp.Park;

import BikePickUp.Bike.Bike;
import dataStructures.*;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 */
public class ParkClass implements Park {

	/**
	 * Constant for serialization
	 */
	private static final long serialVersionUID = 0L;

    /**
     * Park identification, Park name, Park address.
     */
	private String parkID,name,address;

    /**
     * Bike parked at the park
     */
	private Bike bike;

    /**
     * Total number of bikes parked in this park.
     */
	private int nBikes;

    /**
     * Total number of pickups were made from this park.
     */
	private int nPickUps;

    /**
     *
     * @param parkID Park identification
     * @param name Park name
     * @param address Park address.
     */
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

    /**
     *
     * @param nBikes Total number of bikes parked in this park.
     * @return all of the parks info in a order.
     */
	private Iterator<String> getInfo(int nBikes) {
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
