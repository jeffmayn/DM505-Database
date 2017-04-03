
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class PriceOffer {

    public static void priceOffer
        (Connection con) throws IOException, SQLException {

        int amount;
        String choice;

        System.out.println("Available systems for sale:"
                + "\n-----------------------");
        ComputerSystems.PCnames(con);

        Scanner sc = new Scanner(System.in);
        System.out.println("Type which computersystem, to see offers:");
        System.out.println("( ... OBS: its case-sensitive!)");
        choice = sc.nextLine();

        System.out.println("\nHow many would you like?: ");
        amount = sc.nextInt();

         try {

            Statement st = con.createStatement();
            String query = "SELECT *"
                    + " FROM Computersystem"
                    + " WHERE Computersystem.name ='" + choice + "';";

            ResultSet rs = st.executeQuery(query);

                  while (rs.next()) {
                      String gpu = rs.getString("GPU");
                      String cpu = rs.getString("CPU");
                      String mainboard = rs.getString("Mainboard");
                      String ram = rs.getString("RAM");
                      String case_ = rs.getString("CASE_");
                      String hdd = rs.getString("hdd");
                      calculateDiscount(con, amount, choice, cpu,
                              gpu, mainboard, ram, hdd, case_, amount);
                  }
            } catch (SQLException e) {
            }

    }

        public static void calculateDiscount(Connection con, int n,
                String name, String CPU, String GPU, String Mainboard,
                String RAM, String HDD, String CASE, int amount)
                throws IOException {

          try {

           Statement st = con.createStatement();
            String query = "SELECT sum(price) AS price"
                    + " FROM Component"
                    + " WHERE Component.name = '" + CPU + "'"
                    + " OR Component.name = '" + GPU + "'"
                    + " OR Component.name = '" + Mainboard + "'"
                    + " OR Component.name = '" + RAM + "'"
                    + " OR Component.name = '" + HDD + "'"
                    + " OR Component.name = '" + CASE + "'";

            ResultSet rs = st.executeQuery(query);

                  while (rs.next()) {

                      // total price for computersystem + 30%
                      double price = rs.getDouble("price");
                      double sellingPrice = price * 1.3;

                      // rounded to nearest '99
                      double roundedPrice =
                              (Math.round(sellingPrice/100) * 100) - 1;

                      if (n == 1) {
                          System.out.println("\n" + n + "x '"
                                  + name + "': with 0% discount:");

                          System.out.printf("%s%,.2f%s", "Total price: ",
                                  roundedPrice, "\n\n");

                          System.out.println("press [0] to return.\n");
                            IO.returnMenu();
                            IO.input();

                      } else if (n > 1 && n < 11) {

                          double newPrice =
                                  n * roundedPrice * (1 - ((n-1) * 0.02));

                          double percentage = (100*(n-1)*0.02);

                          System.out.println("\n" + n + "x '" + name
                                  + "' (with " + (int)percentage
                                  + "% discount):");

                          System.out.printf("%s%,.2f%s", "Total price: ",
                                  newPrice, "\n\n");

                          System.out.println("press [0] to return.\n");
                            IO.returnMenu();
                            IO.input();

                      } else {

                          double newnewPrice = n * roundedPrice * 0.8;
                           System.out.println("\n" + n + "x '" + name
                                   + "' with 20% discount:");

                           System.out.printf("%s%,.2f",
                                   "Total price: ", newnewPrice);

                           System.out.println("\n\npress [0] to return.\n");
                            IO.returnMenu();
                            IO.input();
                      }
                  }
            } catch (SQLException e) {
            }
     }
}