package bikePickUp.User;

public class UserClass implements User {

    private String NIF,name,address,email,phone,IDuser;
    private int balance,points;

    public UserClass(String IDuser, String NIF, String email, String phone, String name, String address) {
        this.NIF = NIF;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.IDuser = IDuser;
        this.balance = 0;
        this.points = 0;
    }

    @Override
    public String getID() {
        return IDuser;
    }
}
