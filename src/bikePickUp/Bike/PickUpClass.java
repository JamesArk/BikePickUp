package bikePickUp.Bike;

public class PickUpClass implements PickUp{

    private String idBike,idUser;
    
    public PickUpClass(String idBike,String idUser){
    	this.idBike = idBike;
    	this.idUser = idUser;
    }

	@Override
	public String getBikeID() {
		return idBike;
	}

	@Override
	public String getUserID() {
		return idUser;
	}
}
