import bikePickUp.BikePickUp;
import bikePickUp.BikePickUpClass;
import bikePickUp.Bike.PickUp;
import bikePickUp.Exceptions.*;
import bikePickUp.dataStructures.Iterator;

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
    
    private enum Formatter {
    	
    	USER_INFO_FORMATTER_1("%s; "),
    	USER_PARK_INFO_FORMATTER_2("%s, "),
    	PARK_INFO_FORMATTER_1("%s: "),
    	PARK_INFO_FORMATTER_2("%s, "),
    	BIKE_USER_PICK_UPS_FORMATTER("%s %s %s %s %s %d \n"),
    	PICK_DOWN_SUCESS_FORMATTER("%s: %d euros, %d pontos");
   
    	private final String formatter;
    	Formatter(String formatter){
    		this.formatter = formatter;
    	}
    }

    private enum Command{
        AddUser,RemoveUser,GetUserInfo,AddPark,
        AddBike,RemoveBike,GetParkInfo,PickUp,
        PickDown,ChargeUser,BikePickUps,UserPickUps,
        ParkedBike,ListDelayed,FavoriteParks,XS,UNKNOWN
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BikePickUpClass bpu = new BikePickUpClass();
        initializeProgramme(in,bpu);

    }

    private static void initializeProgramme(Scanner in, BikePickUpClass bpu) {
        Command command;
        do {
        	command = getCommand(in);
            switch (command) {
                case AddUser:
                    addUserCommand(in,bpu);
                    break;
                case RemoveUser:
                    removeUser(in,bpu);
                    break;
                case GetUserInfo:
                	getUserInfo(in,bpu);
                    break;
                case AddPark:
                	addPark(in,bpu);
                    break;
                case AddBike:
                	addBike(in,bpu);
                    break;
                case RemoveBike:
                	removeBike(in,bpu);
                    break;
                case GetParkInfo:
                	getParkInfo(in, bpu);
                    break;
                case PickUp:
                    pickUp(in,bpu);
                    break;
                case PickDown:
                    pickDown(in,bpu);
                    break;
                case ChargeUser:
                    chargeUser(in,bpu);
                    break;
                case BikePickUps:
                	bikePickUps(in,bpu);
                    break;
                case UserPickUps:
                	userPickUps(in, bpu);
                    break;
                case ParkedBike:
                	parkedBike(in,bpu);
                    break;
                case ListDelayed:
                    break;
                case FavoriteParks:
                    break;
                case XS:
                	System.out.println(Message.EXIT.msg);
                	in.close();
                    break;
                case UNKNOWN:
                    break;
            }
            System.out.println("\n");
        } while(!command.equals(Command.XS));
    }

    private static Command getCommand(Scanner in) {
        try {
            return Command.valueOf(in.next().trim());
        } catch(IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

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

    private static void removeUser(Scanner in, BikePickUp bpu) {
        String idUser = in.nextLine().trim();

        try {
            bpu.removeUser(idUser);
            System.out.println(Message.REMOVE_USER_SUCESS.msg);
        } catch(UserNotFoundException e) {
            System.out.println(Message.USER_NOT_FOUND.msg);
        } catch(UserUsedSystemException e) {
            System.out.println(Message.USER_USED_SYSTEM.msg);
        }
    }

    private static void getUserInfo(Scanner in,BikePickUp bpu) {
        String userID = in.nextLine().trim();
        String msg = "";

        try {
        	Iterator<String> userInfo = bpu.getUserInfo(userID);
        	if(userInfo.hasNext())
        	    msg += String.format(Formatter.USER_INFO_FORMATTER_1.formatter, userInfo.next());
        	while(userInfo.hasNext())
        	    msg += String.format(Formatter.USER_PARK_INFO_FORMATTER_2.formatter, userInfo.next());

        	System.out.println(msg.substring(0, msg.length()-2));
        } catch(UserNotFoundException e) {
        	System.out.println(Message.USER_NOT_FOUND.msg);
        }  
    }
    
    private static void addPark(Scanner in, BikePickUp bpu) {
    	String idPark = in.next().trim();
    	String name = in.nextLine().trim();
    	String address = in.nextLine();

    	try {
    		bpu.addPark(idPark,name,address);
    		System.out.println(Message.ADD_PARK_SUCESS.msg);
    	} catch(ParkAlreadyExistsException e) {
    		System.out.println(Message.PARK_ALREADY_EXISTS.msg);
    	}
    }
    
    private static void addBike(Scanner in,BikePickUp bpu) {
    	String bikeID = in.next().trim();
    	String parkID = in.next().trim();
    	String bikeLicense = in.nextLine().trim();
    	
    	try {
    		bpu.addBike(bikeID,parkID,bikeLicense);
    		System.out.println(Message.ADD_BIKE_SUCESS.msg);
    	} catch(BikeAlreadyExistsException e) {
    		System.out.println(Message.BIKE_ALREADY_EXISTS.msg);
    	} catch(ParkNotFoundException e) {
    		System.out.println(Message.PARK_NOT_FOUND.msg);
    	}
    }
    
    private static void removeBike(Scanner in,BikePickUp bpu) {
    	String bikeID = in.nextLine().trim();
    	
    	try {
    		bpu.removeBike(bikeID);
    		System.out.println(Message.REMOVE_BIKE_SUCESS.msg);
    	} catch(BikeNotFoundException e) {
    		System.out.println(Message.BIKE_NOT_FOUND.msg);
    	} catch(UsedBikeException e) {
    		System.out.println(Message.USED_BIKE.msg);
    	}
    }
    
    private static void getParkInfo(Scanner in,BikePickUp bpu) {
    	String parkID = in.nextLine().trim();
    	String msg = "";
    	try {
    		Iterator<String> parkInfo = bpu.getParkInfo(parkID);
    		if(parkInfo.hasNext())
    		    msg += String.format(Formatter.PARK_INFO_FORMATTER_1.formatter, parkInfo.next());
    		while(parkInfo.hasNext()) {
    			msg += String.format(Formatter.USER_PARK_INFO_FORMATTER_2.formatter, parkInfo.next());
    		}
    		System.out.println(msg.substring(0, msg.length()-2));
    	} catch(ParkNotFoundException e) {
    		System.out.println(Message.PARK_NOT_FOUND.msg);
    	}
    }

    private static void pickUp(Scanner in, BikePickUpClass bpu) {
        String idBike = in.next().trim();
        String idUser = in.nextLine().trim();
        try {
            bpu.pickUp(idBike,idUser);
            System.out.println(Message.PICK_UP_SUCESS.msg);
        } catch(BikeNotFoundException e) {
            System.out.println(Message.BIKE_NOT_FOUND.msg);
        } catch(BikeOnTheMoveException e) {
            System.out.println(Message.BIKE_ON_THE_MOVE.msg);
        } catch(UserNotFoundException e) {
            System.out.println(Message.USER_NOT_FOUND.msg);
        } catch(UserOnTheMoveException e) {
            System.out.println(Message.USER_ON_THE_MOVE.msg);
        } catch(InsufficientBalanceException e) {
            System.out.println(Message.INSUFICIENT_BALANCE.msg);
        }
    }

    private static void pickDown(Scanner in,BikePickUp bpu) {
        String idBike = in.next().trim();
        String idPark = in.next().trim();
        int minutes = in.nextInt();
        in.nextLine();
        try {
            bpu.pickDown(idBike,idPark,minutes);
            System.out.println(String.format(Formatter.PICK_DOWN_SUCESS_FORMATTER.formatter, Message.PICK_DOWN_SUCESS.msg, bpu.getUserBalance(), bpu.getUserPoints()));
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

    private static void chargeUser(Scanner in,BikePickUp bpu) {
        String idUser = in.next().trim();
        int value = in.nextInt();
        in.nextLine();
        try {
            bpu.chargeUser(idUser,value);
            System.out.println(Message.CHARGE_USER_SUCESS.msg);
        } catch(UserNotFoundException e) {
            System.out.println(Message.USER_NOT_FOUND.msg);
        } catch(InvalidDataException e) {
            System.out.println(Message.INVALID_DATA.msg);
        }
    }
    
    private static void bikePickUps(Scanner in,BikePickUp bpu) {
    	String idBike = in.nextLine().trim();
    	try {
    		Iterator<PickUp> it = bpu.getBikePickUps(idBike);
    		while(it.hasNext()) {
    			PickUp p = it.next();
    			System.out.println(String.format(Formatter.BIKE_USER_PICK_UPS_FORMATTER.formatter, p.getUserID(), p.getInitialIDPark(), 
    					p.getFinalParkID(), p.getMinutes(), p.minutesLate(), p.getCost()));
    		}
    	} catch(BikeNotFoundException e) {
    		System.out.println(Message.BIKE_NOT_FOUND.msg);
    	} catch(BikeNotUsedException e) {
    		System.out.println(Message.BIKE_NOT_USED.msg);
    	} catch(BikeOnFirstPickUpException e) {
    		System.out.println(Message.BIKE_ON_FIRST_PICK_UP.msg);
    	}
    }
    
    private static void userPickUps(Scanner in,BikePickUp bpu) {
    	String idUser = in.nextLine().trim();
    	try {
    		Iterator<PickUp> it = bpu.getUserPickUps(idUser);
    		while(it.hasNext()) {
    			PickUp p = it.next();
    			System.out.println(String.format(Formatter.BIKE_USER_PICK_UPS_FORMATTER.formatter, p.getBikeID(), p.getInitialIDPark(), 
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
    
    private static void parkedBike(Scanner in, BikePickUp bpu) {
    	String idBike = in.next().trim();
    	String idPark = in.nextLine().trim();
    	try {
    		bpu.isBikeParked(idBike, idPark);
    		System.out.println(Message.PARKED_BIKE_SUCESS.msg);
    	} catch(BikeNotFoundException e) {
    		System.out.println(Message.USED_BIKE.msg);
    	} catch(ParkNotFoundException e) {
    		System.out.println(Message.PARK_NOT_FOUND.msg);
    	} catch(BikeNotInParkException e) {
    		System.out.println(Message.BIKE_NOT_IN_PARK.msg);
    	}
    }




}
