
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PriceList {

    public static void pricelist(Connection con) throws IOException{

                    try {

            Statement st = con.createStatement();
            String query = "SELECT * FROM Component";

                    ResultSet rs = st.executeQuery(query);
                    System.out.printf("%-39s %3s %22s",
                            "Component", "Kind", "Selling price");

                    while (rs.next()) {

                            String name = rs.getString("name");
                            String kind = rs.getString("kind");
                            double price = rs.getInt("price");
                            double sellingPrice = price * 1.3;

                            System.out.printf("%n %-39s %3s %10.2f %s",
                                    name, kind, sellingPrice, "DKK");
                    }
                    System.out.println("\n\n");
                    pcList(con);

            } catch (SQLException e) {
            }
    }

    public static void pcList(Connection con) throws IOException {
         try {

            Statement st = con.createStatement();
            String query = "SELECT *"
                    + " FROM Computersystem";

            ResultSet rs = st.executeQuery(query);

                  while (rs.next()) {

                    String name = rs.getString("name");
                    String gpu = rs.getString("GPU");
                    String cpu = rs.getString("CPU");
                    String mainboard = rs.getString("Mainboard");
                    String ram = rs.getString("RAM");
                    String case_ = rs.getString("CASE_");
                    String hdd = rs.getString("hdd");
                    String line = "|----------------------------------------"
                            + "----------------------------";

                    System.out.printf("%-3s %-56s%s%n","| PC: ",name,"Price:");
                    System.out.println(line);
                    System.out.printf("%-17s %-40s","| Motherboard: ",
                            mainboard); CalculatePCListPrices(con, mainboard);

                    System.out.printf("%n%-17s %-40s","| CPU: ",
                            cpu); CalculatePCListPrices(con, cpu);

                    System.out.printf("%n%-17s %-40s","| GPU: ",
                            gpu); CalculatePCListPrices(con, gpu);

                    System.out.printf("%n%-17s %-40s","| RAM: ",
                            ram); CalculatePCListPrices(con, ram);

                    System.out.printf("%n%-17s %-40s","| HDD: ",
                            hdd); CalculatePCListPrices(con, hdd);

                    System.out.printf("%n%-17s %-40s","| CASE: ",
                            case_); CalculatePCListPrices(con, case_);

                    System.out.print("\n|\n| Total price: ");
                    CalculatePCListTotal(con, cpu, gpu, mainboard,
                            ram, hdd, case_);
                    System.out.print("\n\n");
                  }
                  System.out.println("press [0] to return.\n");

                    IO.returnMenu();
                    IO.input();

            } catch (SQLException e) {
            }
    }
    public static void CalculatePCListPrices(Connection con, String component)
            throws IOException {

          try {

            Statement st = con.createStatement();
            String query = "SELECT price"
                    + " FROM Component"
                    + " WHERE Component.name = '" + component + "'";

            ResultSet rs = st.executeQuery(query);

                  while (rs.next()) {

                      double price = rs.getDouble("price");
                      double sellingPrice = price * 1.3;
                     System.out.printf("%,.2f%s",sellingPrice," DKK");
                  }
            } catch (SQLException e) {
            }
     }
    public static void CalculatePCListTotal(Connection con, String CPU,
            String GPU, String Mainboard, String RAM,
            String HDD, String CASE) throws IOException {

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

                      double price = rs.getDouble("price");
                      double sellingPrice = price * 1.3;
                     System.out.printf("%,.2f%s",sellingPrice," DKK");
                  }
            } catch (SQLException e) {
            }
     }
}
