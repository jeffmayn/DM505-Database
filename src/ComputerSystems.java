
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComputerSystems {

     public static void ComputerSystems(Connection con) throws IOException {

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
                      String line = "|---------------------------";

                      System.out.print("| " + name + "\n" + line
                              + "\n| Motherboard:    " + mainboard
                              + "\n| CPU:            " + cpu
                              + "\n| GPU:            " + gpu
                              + "\n| RAM:            " + ram
                              + "\n| HDD:            " + hdd
                              + "\n| CASE:           " + case_);

                              System.out.print("\n|\n| From current stock '");
                              CalculateNumberOfSystems(con, name, cpu, gpu,
                                      ram, hdd, case_, mainboard);
                              System.out.print("' system(s) can be build\n\n");
                  }
                  System.out.println("press [0] to return.\n");

                    IO.returnMenu();
                    IO.input();

            } catch (SQLException e) {
            }
    }

     public static void PCnames(Connection con) {

        try {

            Statement st = con.createStatement();
            String query = "SELECT name "
                    + "FROM Computersystem ";

            ResultSet rs = st.executeQuery(query);
                    int n = 1;
                  while (rs.next()) {
                      String name = rs.getString("name");


                      System.out.println("[" + n +"]: " + name);
                      n++;
                  }
                   System.out.println("\nTo see computer specifications,\n"
                           + "press [0] and choose"
                           + " 'List of all computersystems'\n\n");

            } catch (SQLException e) {
            }
    }

     public static void CalculateNumberOfSystems(Connection con, String name,
             String CPU, String GPU, String RAM, String HDD,
             String CASE, String Mainboard) throws IOException {

          try {

            Statement st = con.createStatement();
            String query = "SELECT min(amount)"
                    + " FROM Component"
                    + " WHERE Component.name = '" + name + "'"
                    + " OR Component.name = '" + CPU + "'"
                    + " OR Component.name = '" + GPU + "'"
                    + " OR Component.name = '" + Mainboard + "'"
                    + " OR Component.name = '" + RAM + "'"
                    + " OR Component.name = '" + HDD + "'"
                    + " OR Component.name = '" + CASE + "'";

            ResultSet rs = st.executeQuery(query);

                  while (rs.next()) {


                      int count = rs.getInt("min");
                     System.out.print(count);
                  }

            } catch (SQLException e) {
            }
     }
}