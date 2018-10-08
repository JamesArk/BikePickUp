package BikePickUp.User;


import BikePickUp.Bike.PickUp;
import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;

public class UserClass implements User {

    /**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	private String NIF,name,address,email,phone,IDuser;
    private int balance,points;
    private List<PickUp> pickUps;
    private PickUp currentPickUp;
    

    public UserClass(String IDuser, String NIF, String email, String phone, String name, String address) {
        this.NIF = NIF;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.IDuser = IDuser;
        this.balance = 5;
        this.points = 0;
        this.pickUps = new DoublyLinkedList<>();
        this.currentPickUp = null;
    }

    @Override
    public String getID() {
        return IDuser;
    }

	@Override
	public Iterator<String> getUserInfo() {
        List<String> list = new DoublyLinkedList<>();
        list.addLast(name);
        list.addLast(NIF);
        list.addLast(address);
        list.addLast(email);
        list.addLast(phone);
        list.addLast(Integer.toString(balance));
        list.addLast(Integer.toString(points));
		return list.iterator();
	}

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void pickUp(PickUp pickUp) {
        currentPickUp = pickUp;
    }

    @Override
    public boolean hasUsedSystem() {
        return !pickUps.isEmpty() || currentPickUp != null;
    }

    @Override
    public void pickDown(String finalParkID, int minutes) {
         currentPickUp.setFinalParkID(finalParkID);
         currentPickUp.setMinutes(minutes);
         currentPickUp.setCost();
         points += currentPickUp.getCost();
         balance-= currentPickUp.getCost();
         pickUps.addLast(currentPickUp);
         currentPickUp = null;
    }

    @Override
    public void charge(int value) {
        balance += value;
    }

	@Override
	public boolean isUserIsOnFirstPickUp() {
		return pickUps.isEmpty() &&  currentPickUp != null ;
	}

	@Override
	public Iterator<PickUp> getUserPickUps() {
		return pickUps.iterator();
	}

	@Override
	public int getPoints() {
		return points;
	}

    @Override
    public boolean isThereTardiness() {
        return points > 0;
    }

    @Override
    public boolean isOnTheMove() {
        return currentPickUp != null;
    }
}
