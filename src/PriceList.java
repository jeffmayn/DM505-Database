
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
                              + "---------------";
                                // formats magic
                      System.out.printf(
                                // systemname, price, line-seperator
                              "%n%s%-31s%20s%n%s"
                                // motherboard: name, price
                              + "%n%s%-35s%s"
                                // cpu: name, price
                              + "%n%s%-43s%s"
                                // gpu: name, price
                              + "%n%s%-43s%s"
                                // ram: name, price
                              + "%n%s%-43s%s"
                                // hdd: name, price
                              + "%n%s%-43s%s"
                                // case: name, price
                              + "%n%s%-42s%s%n",
                                // output
                              "| PC: ", name, " Price: ", line,
                              "| Motherboard: ", mainboard, "price",
                              "| CPU: ", cpu, "price",
                              "| GPU: ", gpu, "price",
                              "| RAM: ", ram, "price",
                              "| HDD: ", hdd,"price",
                              "| CASE: ", case_,"price"
                                      + ""
                                      + "");

//                      System.out.print("| " + name + "\t\t\t\tPrice:\n" + line
//                              + "\n| Motherboard:    " + mainboard
//                              + "\n| CPU:            " + cpu
//                              + "\n| GPU:            " + gpu
//                              + "\n| RAM:            " + ram
//                              + "\n| HDD:            " + hdd
//                              + "\n| CASE:           " + case_);
//
//                      
//                      
//                      
//                              System.out.print("\n|\n| \t\t\t\t\t\tTotal price: 10.000 kr\n\n");
                  }
                  System.out.println("press [0] to return.\n");

                    IO.returnMenu();
                    IO.input();

            } catch (SQLException e) {
            }
    }
}
