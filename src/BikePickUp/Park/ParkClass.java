package BikePickUp.Park;

import BikePickUp.Bike.Bike;
import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;

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
     * Park name, Park address.
     */
	private String name,address;

    /**
     * Total number of pickups were made from this park.
     */
	private int nPickUps;

	/**
	 * All of the bikes parked in this park.
	 */
	private Dictionary<String,Bike> bikes;

    /**
     *  @param name Park name
     * @param address Park address.
	 */
    public ParkClass(String name, String address) {
        this.name = name;
        this.address = address;
        nPickUps = 0;
        bikes = new ChainedHashTable<>();
    }


	@Override
	public void addBike(Bike b) {
		bikes.insert(b.getID(),b);
	}

	@Override
	public void pickUp(String bikeID) {
		removeBike(bikeID);
		nPickUps++;
	}

	@Override
	public void pickDown(Bike bike) {
        addBike(bike);
	}

	@Override
	public boolean isBikeInPark(String bikeID) {
		return bikes.find(bikeID) != null;
	}

	@Override
	public int getNBikes() {
		return bikes.size();
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
    public void removeBike(String bikeID) {
        bikes.remove(bikeID);
    }
}
