package BikePickUp.Park;

import BikePickUp.Bike.Bike;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 *
 * An interface for setters and methods that affect the object
 */
public interface ParkSet extends Park {

    /**
     * Adds a bike to the park
     * @param b - the bike to be added
     */
    void addBike(Bike b);

    /**
     * Removes the bike from the park
     */
    void pickUp();

    /**
     * Returns the bike to the park
     * @param bike - the bike to be delivered
     */
    void pickDown(Bike bike);

    /**
     * Removes the bike from the park
     */
    void removeBike();
}
