package bikePickUp.Bike;

public class PickUpClass implements PickUp{

    private String idUser,initialIDPark,finalIDPark;
    private int duration;
    private int cost;

    public PickUpClass(String idUser){
        this.idUser = idUser;
    }
}
