
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class IO extends ComputerStore {

    private static void menu() {

           System.out.println("+-------------------------------------------+");
           System.out.println("|                   OPTIONS                 |");
           System.out.println("+-------------------------------------------+");
           System.out.println("| Type [1] -  List of all components        |");
           System.out.println("| Type [2] -  List of all computer systems  |");
           System.out.println("| Type [3] -  Price list                    |");
           System.out.println("| Type [4] -  Price offer                   |");
           System.out.println("| Type [5] -  Sell component or system      |");
           System.out.println("| Type [6] -  Restocking list               |");
           System.out.println("+-------------------------------------------+");
    }

    public static void returnMenu() throws IOException, SQLException {
        try {
            Scanner sc = new Scanner(System.in);

            switch (sc.nextInt()) {
                case 0:
                    input();
                    break;

                default:
                    System.err.println("try again ..");
                    returnMenu();
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("you really need to press [0] .. try again!");
            returnMenu();
        }
    }

    public static void input() throws IOException, SQLException  {

        Scanner in = new Scanner(System.in);
        menu();

	switch (in.nextInt())
	{
	    case 1:
	    Components.All_components(con);
	    break;

            case 2:
            ComputerSystems.ComputerSystems(con);
            break;

	    case 3:
            PriceList.pricelist(con);
	    break;

	    case 4:
	    PriceOffer.priceOffer(con);
	    break;

            case 5:
	    Sell.sell(con);
	    break;

            case 6:
	    Restock.restock(con);
	    break;

	    default:
	    System.err.println ("Not understood. Try again ..");

            input();
	    break;
	}
    }
}