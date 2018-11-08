import BikePickUp.Park.Park;
import BikePickUp.PickUp.PickUp;
import BikePickUp.BikePickUp;
import BikePickUp.BikePickUpClass;
import BikePickUp.Exceptions.*;
import BikePickUp.User.User;
import dataStructures.Iterator;

import java.io.*;
import java.util.Scanner;

/**
 * @author Goncalo Areia (52714) g.areia@campus.fct.unl.pt
 * @author Tiago Guerreiro (53649) tf.guerreiro@campus.fct.unl.pt
 */
public class Main {


    /**
     * Saved data name
     */
    private static final String DATA_FILE = "storedBikePickUp.data";

    /**
     * Output Messages
     */
    private enum Message{
        ADD_USER_SUCCESS("Insercao de utilizador com sucesso."),
        USER_ALREADY_EXISTS("Utilizador existente."),
        REMOVE_USER_SUCCESS("Utilizador removido com sucesso."),
        USER_NOT_FOUND("Utilizador inexistente."),
        USER_USED_SYSTEM("Utilizador ja utilizou o sistema."),
        ADD_PARK_SUCCESS("Parque adicionado com sucesso."),
        PARK_ALREADY_EXISTS("Parque existente."),
        ADD_BIKE_SUCCESS("Bicicleta adicionada com sucesso."),
        BIKE_ALREADY_EXISTS("Bicicleta existente."),
        PARK_NOT_FOUND("Parque inexistente."),
        REMOVE_BIKE_SUCCESS("Bicicleta removida com sucesso."),
        BIKE_NOT_FOUND("Bicicleta inexistente."),
        USED_BIKE("Bicicleta ja foi utilizada."),
        PICK_UP_SUCCESS("PickUp com sucesso."),
        BIKE_ON_THE_MOVE("Bicicleta em movimento."),
        USER_ON_THE_MOVE("Utilizador em movimento."),
        INSUFFICIENT_BALANCE("Saldo insuficiente."),
        PICK_DOWN_SUCCESS("Pickdown com sucesso: "),
        BIKE_STOPPED("Bicicleta parada."),
        INVALID_DATA("Dados invalidos."),
        CHARGE_USER_SUCCESS("Saldo: "),
        BIKE_NOT_USED("Bicicleta nao foi utilizada."),
        BIKE_ON_FIRST_PICK_UP("Bicicleta em movimento em primeiro pickup."),
        USER_NOT_USED_SYSTEM("Utilizador nao utilizou o sistema."),
        USER_ON_FIRST_PICKUP("Utilizador em primeiro PickUp."),
        PARKED_BIKE_SUCCESS("Bicicleta estacionada no parque."),
        BIKE_NOT_IN_PARK("Bicicleta nao esta em parque."),
        NO_TARDINESS("Nao se registaram atrasos."),
        NO_PICK_UPS_MADE("Nao foram efetuados pickups."),
        EXIT("Gravando e terminando...");



        private final String msg;
        Message(String msg) {
            this.msg = msg;
        }

    }

    /**
     * String formatter
     */
    private enum Formatter {

        USER_INFO_FORMATTER("%s: %s, %s, %s, %s, %s, %s"),
        PARK_INFO_FORMATTER("%s: %s, %s"),
    	BIKE_USER_PICK_UPS_FORMATTER("%s %s %s %s %s %d"),
    	PICK_DOWN_SUCCESS_FORMATTER("%s%d euros, %d pontos"),
        USER_BALANCE_FORMATTER("%s%d euros");

    	private final String formatter;
    	Formatter(String formatter){
    		this.formatter = formatter;
    	}
    }

    /**
     * Commands
     */
    private enum Command{
        ADDUSER, REMOVEUSER, GETUSERINFO, ADDPARK,
        ADDBIKE, REMOVEBIKE, GETPARKINFO, PICKUP,
        PICKDOWN, CHARGEUSER, BIKEPICKUPS, USERPICKUPS,
        PARKEDBIKE, LISTDELAYED, FAVORITEPARKS,XS,UNKNOWN
    }

    /**
     * Main program
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BikePickUp bpu = load();
        initializeProgram(in,bpu);

    }

    /**
     * Initializes program
     * @param in Scanner
     * @param bpu program's system object
     */
    private static void initializeProgram(Scanner in, BikePickUp bpu) {
        Command command;
        do {
        	command = getCommand(in);
            switch (command) {
                case ADDUSER:
                    addUserCommand(in,bpu);
                    break;
                case REMOVEUSER:
                    removeUser(in,bpu);
                    break;
                case GETUSERINFO:
                	getUserInfo(in,bpu);
                    break;
                case ADDPARK:
                	addPark(in,bpu);
                    break;
                case ADDBIKE:
                	addBike(in,bpu);
                    break;
                case REMOVEBIKE:
                	removeBike(in,bpu);
                    break;
                case GETPARKINFO:
                	getParkInfo(in, bpu);
                    break;
                case PICKUP:
                    pickUp(in,bpu);
                    break;
                case PICKDOWN:
                    pickDown(in,bpu);
                    break;
                case CHARGEUSER:
                    chargeUser(in,bpu);
                    break;
                case BIKEPICKUPS:
                	bikePickUps(in,bpu);
                    break;
                case USERPICKUPS:
                	userPickUps(in, bpu);
                    break;
                case PARKEDBIKE:
                	parkedBike(in,bpu);
                    break;
                case LISTDELAYED:
                    listDelayed(bpu);
                    break;
                case FAVORITEPARKS:
                    favouriteParks(bpu);
                    break;
                case XS:
                	save(bpu);
                    break;
                case UNKNOWN:
                    break;
            }
            System.out.print("\n");
        } while(!command.equals(Command.XS));
        in.close();
    }

    /**
     * Obtains the next command
     * @param in Scanner
     * @return next Command
     */
    private static Command getCommand(Scanner in) {
        try {
            return Command.valueOf(in.next().trim().toUpperCase());
        } catch(IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    /**
     * AddUser Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void addUserCommand(Scanner in, BikePickUp bpu) {
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

    /**
     * RemoveUser Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void removeUser(Scanner in, BikePickUp bpu) {
        String userID = in.nextLine().trim();


        try {
            bpu.removeUser(userID);
            System.out.println(Message.REMOVE_USER_SUCCESS.msg);
        } catch(UserNotFoundException e) {
            System.out.println(Message.USER_NOT_FOUND.msg);
        } catch(UserUsedSystemException e) {
            System.out.println(Message.USER_USED_SYSTEM.msg);
        }
    }

    /**
     * GetUserInfo Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void getUserInfo(Scanner in,BikePickUp bpu) {
        String userID = in.nextLine().trim();

        try {
        	User user = bpu.getUserInfo(userID);
        	System.out.println(String.format(Formatter.USER_INFO_FORMATTER.formatter,user.getName(),user.getNIF(),user.getAddress(),
                    user.getEmail(),user.getPhone(),Integer.toString(user.getBalance()),user.getPoints()));
        } catch(UserNotFoundException e) {
        	System.out.println(Message.USER_NOT_FOUND.msg);
        }  
    }

    /**
     * AddPark Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void addPark(Scanner in, BikePickUp bpu) {
    	String parkID = in.next().trim();
    	String name = in.nextLine().trim();
    	String address = in.nextLine();

    	try {
    		bpu.addPark(parkID,name,address);
    		System.out.println(Message.ADD_PARK_SUCCESS.msg);
    	} catch(ParkAlreadyExistsException e) {
    		System.out.println(Message.PARK_ALREADY_EXISTS.msg);
    	}
    }

    /**
     * AddBike Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void addBike(Scanner in,BikePickUp bpu) {
    	String bikeID = in.next().trim();
    	String parkID = in.next().trim();
    	String bikeLicense = in.nextLine().trim();

    	
    	try {
    		bpu.addBike(bikeID,parkID,bikeLicense);
    		System.out.println(Message.ADD_BIKE_SUCCESS.msg);
    	} catch(BikeAlreadyExistsException e) {
    		System.out.println(Message.BIKE_ALREADY_EXISTS.msg);
    	} catch(ParkNotFoundException e) {
    		System.out.println(Message.PARK_NOT_FOUND.msg);
    	}
    }

    /**
     * RemoveBike Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void removeBike(Scanner in,BikePickUp bpu) {
    	String bikeID = in.nextLine().trim();

    	try {
    		bpu.removeBike(bikeID);
    		System.out.println(Message.REMOVE_BIKE_SUCCESS.msg);
    	} catch(BikeNotFoundException e) {
    		System.out.println(Message.BIKE_NOT_FOUND.msg);
    	} catch(UsedBikeException e) {
    		System.out.println(Message.USED_BIKE.msg);
    	}
    }

    /**
     * GetParkInfo Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void getParkInfo(Scanner in,BikePickUp bpu) {
    	String parkID = in.nextLine().trim();

    	try {
    		Park park = bpu.getParkInfo(parkID);
    		System.out.println(String.format(Formatter.PARK_INFO_FORMATTER.formatter, park.getName(),park.getAddress(),park.getNBikes()));
    	} catch(ParkNotFoundException e) {
    		System.out.println(Message.PARK_NOT_FOUND.msg);
    	}
    }

    /**
     * PickUp Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void pickUp(Scanner in, BikePickUp bpu) {
        String bikeID = in.next().trim();
        String userID = in.nextLine().trim();

        try {
            bpu.pickUp(bikeID,userID);
            System.out.println(Message.PICK_UP_SUCCESS.msg);
        } catch(BikeNotFoundException e) {
            System.out.println(Message.BIKE_NOT_FOUND.msg);
        } catch(BikeOnTheMoveException e) {
            System.out.println(Message.BIKE_ON_THE_MOVE.msg);
        } catch(UserNotFoundException e) {
            System.out.println(Message.USER_NOT_FOUND.msg);
        } catch(UserOnTheMoveException e) {
            System.out.println(Message.USER_ON_THE_MOVE.msg);
        } catch(InsufficientBalanceException e) {
            System.out.println(Message.INSUFFICIENT_BALANCE.msg);
        }
    }

    /**
     * PickDown Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void pickDown(Scanner in,BikePickUp bpu) {
        String bikeID = in.next().trim();
        String parkID = in.next().trim();
        int minutes = in.nextInt();
        in.nextLine();
        try {
            User user = bpu.pickDown(bikeID,parkID,minutes);
            System.out.println(String.format(Formatter.PICK_DOWN_SUCCESS_FORMATTER.formatter, Message.PICK_DOWN_SUCCESS.msg, user.getBalance(), user.getPoints()));
        } catch(BikeNotFoundException e) {
            System.out.println(Message.BIKE_NOT_FOUND.msg);
        } catch (BikeStoppedException e) {
            System.out.println(Message.BIKE_STOPPED.msg);
        } catch (ParkNotFoundException e) {
            System.out.println(Message.PARK_NOT_FOUND.msg);
        } catch (InvalidDataException e) {
            System.out.println(Message.INVALID_DATA.msg);
        }
    }

    /**
     * ChargeUser Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void chargeUser(Scanner in,BikePickUp bpu) {
        String userID = in.next().trim();
        int value = in.nextInt();
        in.nextLine();
        try {
            User user = bpu.chargeUser(userID,value);
            System.out.println(String.format(Formatter.USER_BALANCE_FORMATTER.formatter,Message.CHARGE_USER_SUCCESS.msg,user.getBalance()));
        } catch(UserNotFoundException e) {
            System.out.println(Message.USER_NOT_FOUND.msg);
        } catch(InvalidDataException e) {
            System.out.println(Message.INVALID_DATA.msg);
        }
    }

    /**
     * BikePickUps Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void bikePickUps(Scanner in,BikePickUp bpu) {
    	String bikeID = in.nextLine().trim();
    	try {
    		Iterator<PickUp> iterator = bpu.getBikePickUps(bikeID);
    		while(iterator.hasNext()) {
    			PickUp pickUp = iterator.next();
    			System.out.println(String.format(Formatter.BIKE_USER_PICK_UPS_FORMATTER.formatter, pickUp.getUserID(), pickUp.getInitialParkID(),
    					pickUp.getFinalParkID(), pickUp.getMinutes(), pickUp.minutesLate(), pickUp.getCost()));
    		}
    	} catch(BikeNotFoundException e) {
    		System.out.println(Message.BIKE_NOT_FOUND.msg);
    	} catch(BikeNotUsedException e) {
    		System.out.println(Message.BIKE_NOT_USED.msg);
    	} catch(BikeOnFirstPickUpException e) {
    		System.out.println(Message.BIKE_ON_FIRST_PICK_UP.msg);
    	}
    }

    /**
     * UserPickUps Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void userPickUps(Scanner in,BikePickUp bpu) {
    	String userID = in.nextLine().trim();
    	try {
    		Iterator<PickUp> iterator = bpu.getUserPickUps(userID);
    		while(iterator.hasNext()) {
    			PickUp p = iterator.next();
    			System.out.println(String.format(Formatter.BIKE_USER_PICK_UPS_FORMATTER.formatter, p.getBikeID(), p.getInitialParkID(),
    					p.getFinalParkID(), p.getMinutes(), p.minutesLate(), p.getCost()));
    		}
    	} catch(UserNotFoundException e) {
    		System.out.println(Message.USER_NOT_FOUND.msg);
    	} catch(UserNotUsedSystemException e) {
    		System.out.println(Message.USER_NOT_USED_SYSTEM.msg);
    	} catch(UserOnFirstPickUpException e) {
    		System.out.println(Message.USER_ON_FIRST_PICKUP.msg);
    	}
    }

    /**
     * ParkedBike Command
     * @param in Scanner
     * @param bpu BikePickUp application
     */
    private static void parkedBike(Scanner in, BikePickUp bpu) {
    	String bikeID = in.next().trim();
    	String parkID = in.nextLine().trim();
    	try {
    		bpu.bikeParked(bikeID, parkID);
    		System.out.println(Message.PARKED_BIKE_SUCCESS.msg);
    	} catch(BikeNotFoundException e) {
    		System.out.println(Message.BIKE_NOT_FOUND.msg);
    	} catch(ParkNotFoundException e) {
    		System.out.println(Message.PARK_NOT_FOUND.msg);
    	} catch(BikeNotInParkException e) {
    		System.out.println(Message.BIKE_NOT_IN_PARK.msg);
    	}
    }

    /**
     * ListDelayed Command
     * @param bpu BikePickUp application
     */
    private static void listDelayed(BikePickUp bpu) {
        try{
            bpu.listDelayed();
        } catch (NoTardinessException e){
            System.out.println(Message.NO_TARDINESS.msg);
        }
    }

    /**
     * FavouriteParks Command
     * @param bpu BikePickUp application
     */
    private static void favouriteParks(BikePickUp bpu) {
        try {
            Park park = bpu.favouriteParks();
            System.out.println(String.format(Formatter.PARK_INFO_FORMATTER.formatter, park.getName(),park.getAddress(),park.getNPickUps()));
        } catch(NoPickUpsMadeException e) {
            System.out.println(Message.NO_PICK_UPS_MADE.msg);
        }
    }

    /**
     * Saves all the data of the BikePickUp application into a file
     * @param bpu BikePickUp application
     */
    private static void save(BikePickUp bpu) {
        try {
            System.out.println(Message.EXIT.msg);
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(DATA_FILE));
            file.writeObject(bpu);
            file.flush();
            file.close();
        } catch (IOException e ){
            System.out.println("Data file corrupted");
        }
    }

    /**
     * Loads the data to the BikePickUp application, if the file is not found creates a new one
     * @return BikePickUp application
     */
    private static BikePickUp load() {
        try{
            ObjectInputStream file  = new ObjectInputStream(new FileInputStream(DATA_FILE));
            BikePickUp bpu = (BikePickUp) file.readObject();
            file.close();
            return bpu;
        } catch(IOException e){
        	
        }
        catch(ClassNotFoundException e){
            System.out.println("Corrupted file");

        }return new BikePickUpClass();
    }
}
