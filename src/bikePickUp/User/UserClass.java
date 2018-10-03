package bikePickUp.User;


import bikePickUp.Bike.PickUp;
import bikePickUp.dataStructures.DoublyLinkedList;
import bikePickUp.dataStructures.Iterator;
import bikePickUp.dataStructures.List;

public class UserClass implements User {

    private String NIF,name,address,email,phone,IDuser;
    private int balance,points;
    private List<PickUp> pickUps;
    

    public UserClass(String IDuser, String NIF, String email, String phone, String name, String address) {
        this.NIF = NIF;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.IDuser = IDuser;
        this.balance = 0;
        this.points = 0;
        this.pickUps = new DoublyLinkedList<>();
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
        pickUps.addLast(pickUp);
    }

    @Override
    public boolean hasUsedSystem() {
        return !pickUps.isEmpty();
    }

    @Override
    public void pickDown(String finalParkID, int minutes) {
         PickUp pickUp = pickUps.getLast();
         pickUp.setFinalParkID(finalParkID);
         pickUp.setMinutes(minutes);
    }

    @Override
    public void charge(int value) {
        balance += value;
    }
}
