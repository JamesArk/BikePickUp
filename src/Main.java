import bikePickUp.BikePickUp;
import bikePickUp.BikePickUpClass;
import bikePickUp.Exceptions.UserAlreadyExistsException;
import bikePickUp.Exceptions.UserNotFoundException;
import bikePickUp.User.User;

import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 */
public class Main {


    private enum Message{
        ADD_USER_SUCCESS("Insercao de utilizador com sucesso."),
        USER_ALREADY_EXISTS("Utilizador existente."),
        REMOVE_USER_SUCESS("Utilizador removido com sucesso."),
        USER_NOT_FOUND("Utilizador inexistente."),
        USER_USED_SYSTEM("Utilizador ja utilizador o sistema."),
        ADD_PARK_SUCESS("Parque adicionado com sucesso."),
        PARK_ALREADY_EXISTS("Parque existente."),
        ADD_BIKE_SUCESS("Bicicleta adicionada com sucesso."),
        BIKE_ALREADY_EXISTS("Bicicleta existente."),
        PARK_NOT_FOUND("Parque inexistente."),
        REMOVE_BIKE_SUCESS("Bicicleta removida com sucesso."),
        BIKE_NOT_FOUND("Bicicleta inexistente."),
        USED_BIKE("Bicicleta ja foi utilizada."),
        PICK_UP_SUCESS("PickUp com sucesso."),
        BIKE_ON_THE_MOVE("Bicicleta em movimento."),
        USER_ON_THE_MOVE("Utilizador em movimento."),
        INSUFICIENT_BALANCE("Saldo insuficiente."),
        PICK_DOWN_SUCESS("Pickdown com sucesso: "),
        BIKE_STOPPED("Bicicleta parada."),
        INVALID_DATA("Dados invalidos."),
        CHARGE_USER_SUCESS("Saldo: "),
        BIKE_NOT_USED("Bicicleta nao foi utilizada."),
        BIKE_ON_FIRST_PICK_UP("Bicicleta em movimento em primeiro pickup."),
        USER_NOT_USED_SYSTEM("Utilizador nao utilizou o sistema."),
        USER_ON_FIRST_PICKUP("Utilizador em primeiro pickup."),
        PARKED_BIKE_SUCESS("Bicicleta estacionada no parque."),
        BIKE_NOT_IN_PARK("Bicicleta nao esta em parque."),
        NO_TARDINESS("Nao se registaram atrasos."),
        NO_PICK_UPS_MADE("Não foram efectuados pickups."),
        EXIT("Gravando e terminando.");



        private final String msg;
        Message(String msg) {
            this.msg = msg;
        }

    }

    private enum Command{
        AddUser,RemoveUser,GetUserInfo,AddPark,
        AddBike,RemoveBike,GetParkInfo,PickUp,
        PickDown,ChargeUser,BikePickUps,UserPickUps,
        ParkedBike,ListDelayed,FavoriteParks,XS,UNKNOWN;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BikePickUpClass bpu = new BikePickUpClass();
        initializeProgramme(in,bpu);

    }

    private static void initializeProgramme(Scanner in, BikePickUpClass bpu) {
        Command command = getCommand(in);
        while(!command.equals(Command.XS)) {
            switch (command) {
                case AddUser:
                    AddUserCommand(in,bpu);
                    break;
                case RemoveUser:
                    RemoveUser(in,bpu);
                    break;
                case GetUserInfo:
                    break;
                case AddPark:
                    break;
                case AddBike:
                    break;
                case RemoveBike:
                    break;
                case GetParkInfo:
                    break;
                case PickUp:
                    break;
                case PickDown:
                    break;
                case ChargeUser:
                    break;
                case BikePickUps:
                    break;
                case UserPickUps:
                    break;
                case ParkedBike:
                    break;
                case ListDelayed:
                    break;
                case FavoriteParks:
                    break;
                case XS:
                    break;
                case UNKNOWN:
                    break;

            }

            System.out.println("\n");
            command = getCommand(in);
        }
    }

    private static Command getCommand(Scanner in) {
        try {
            return Command.valueOf(in.next().trim());
        } catch(IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    private static void AddUserCommand(Scanner in, BikePickUp bpu) {
        String userID = in.next().trim();
        String NIF = in.next().trim();
        String email = in.next().trim();
        String phone = in.next().trim();
        String name = in.nextLine().trim();
        String address = in.nextLine();

        try {
            bpu.addUser(userID, NIF, email, phone, name, address);
            System.out.println(Message.ADD_USER_SUCCESS.msg);
        } catch (UserAlreadyExistsException e) {
            System.out.println(Message.USER_ALREADY_EXISTS.msg);
        }
    }

    private static void RemoveUser(Scanner in, BikePickUp bpu) {
        String idUser = in.nextLine().trim();
        try {
            bpu.removeUser(idUser);
            System.out.println(Message.REMOVE_USER_SUCESS.msg);
        } catch(UserNotFoundException e) {
            System.out.println(Message.USER_NOT_FOUND.msg);
        }
    }

    private static void getUserInfo(Scanner in,BikePickUp bpu) {
        String userID = in.nextLine().trim();
        try {

        }
    }


}
