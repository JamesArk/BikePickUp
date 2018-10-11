package BikePickUp.Park;

import BikePickUp.Bike.Bike;
import dataStructures.*;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 */
public class ParkClass implements ParkSet {

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
	public void addBike(Bike b) {
		this.bike = b;
		nBikes++;
	}

	@Override
	public void pickUp() {
		removeBike();
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
	public int getNBikes() {
		return nBikes;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String getNPickUps() {
		return Integer.toString(nPickUps);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
    public void removeBike() {
        nBikes--;
        bike = null;
    }
}
