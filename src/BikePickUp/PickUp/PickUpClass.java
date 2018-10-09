package BikePickUp.PickUp;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 */
public class PickUpClass implements PickUp{

	/**
	 * Constant for serialization
	 */
	private static final long serialVersionUID = 0L;

    /**
     * Bike identification, User identification, Park identification where the pickup was made, Park identification where the dropOff (pickdown) was made.
     */
	private String idBike,idUser,initialParkID,finalParkID;

    /**
     * Total minutes, total cost.
     */
	private int minutes,cost;

    /**
     *
     * @param idBike Bike identification
     * @param idUser User identification
     * @param initialParkID Park identification where the pickup was made.
     */
    public PickUpClass(String idBike,String idUser,String initialParkID){
    	this.idBike = idBike;
    	this.idUser = idUser;
    	this.initialParkID = initialParkID;
    }

	@Override
	public String getBikeID() {
		return idBike;
	}

	@Override
	public String getUserID() {
		return idUser;
	}

    @Override
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public void setFinalParkID(String finalParkID) {
        this.finalParkID = finalParkID;
    }

    @Override
    public String getFinalParkID() {
        return finalParkID;
    }

    @Override
    public int minutesLate() {
        if(minutes >= 60)
            return minutes - MAX_MINS;
        else
            return 0;
    }

	@Override
	public String getInitialIDPark() {
		return initialParkID;
	}
	
	@Override
	public void setCost() {
		int i = 1;
		if(this.minutesLate() <= 0) 
			cost = 0;
		else {
			while(this.minutesLate() > MINS_LATE * i) {
				i++;
			}
			cost = i;
		}
	}


	@Override
	public int getCost() {
		return cost;
	}


}
