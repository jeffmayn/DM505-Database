
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class PriceOffer {

    public static void priceOffer
        (Connection con) throws IOException, SQLException {

        String name;
        double amount;
        int system;

        System.out.println("Available systems for sale:"
                + "\n-----------------------");
        ComputerSystems.PCnames(con);

        Scanner sc = new Scanner(System.in);
        System.out.println("Type which computersystem, to see offers: ");
        system = sc.nextInt();
        if (system == 0) {
            IO.returnMenu();
            IO.input();
        } else {

             System.out.println("How many would you like?: ");
        amount = sc.nextInt();

         try {
            Statement st = con.createStatement();
            String query = "SELECT DISTINCT Computersystem.name,"
                    + " gpu, cpu, mainboard, ram, case_ "
                    + "FROM Computersystem"
                    + " JOIN Component ON Computersystem.name = '"
                    + system + "'";

            ResultSet rs = st.executeQuery(query);
            System.out.println("Offer on computersystems:\n");
                  while (rs.next()) {

                      amount = 10000;
                      double amount2 = amount*0.98;
                      double amount3 = amount*0.96;
                      double amount4 = amount*0.94;
                      double amount5 = amount*0.92;
                      double amount6 = amount*0.90;
                      double amount7 = amount*0.88;
                      double amount8 = amount*0.86;
                      double amount9 = amount*0.84;
                      double amount10 = amount*0.82;
                      double amount11 = amount*0.80;

                  }
                  System.out.println("\npress [0] to return.\n");

                    IO.returnMenu();
                    IO.input();

            } catch (SQLException e) {
            }
        }
    }
}
