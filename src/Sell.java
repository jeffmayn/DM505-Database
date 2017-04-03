
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Sell {

        public static void sell(Connection con) throws IOException {

        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wish to sell component(s)"
                + " or pc-system(s)?\n");
        System.out.println("press [1] for component");
        System.out.println("press [2] for pc-system(s)\n");
        choice = sc.nextInt();

        switch (choice) {
            case 1:
                sellComponent(con);
                break;
            case 2:
               sellSystem(con);
                break;
            default:
                System.out.println("bye!");
                break;
        }
    }
     private static void sellComponent(Connection con) throws IOException {

        String choice;
        int antal;

        System.out.println("Amount:\tComponent:");
        try {
            Statement st = con.createStatement();
            String query = "SELECT * FROM Component";

                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {

                            String name = rs.getString("name");
                            int amount = rs.getInt("amount");
                            System.out.println(amount + "\t" + name);
                    }
                    Scanner sc = new Scanner(System.in);
        System.out.println("\n\nWhat component do you want to sell?");
        System.out.println("... (write component-name"
                + " - OBS: its case-sensitive!)");

        choice = sc.nextLine();
        System.out.println("How many do you wish to sell?");
        antal = sc.nextInt();

        SellComponentExecute(con, choice, antal);

            } catch (SQLException e) {
            }
    }
     private static void SellComponentExecute
        (Connection con, String choice, int antal) throws IOException {

          try {

            PreparedStatement ps = con.prepareStatement("UPDATE Component "
                    + "SET amount = GREATEST(0, amount - " + antal + ") "
                    + "WHERE Component.name = '" + choice + "'");

            System.out.println("Sold " + antal + " " + choice + "\n");
            System.out.println("\npress [0] to return.\n");

            ps.executeUpdate();
            ps.close();

            IO.returnMenu();
            IO.input();

        } catch(SQLException se) {
        }
     }

        public static void sellSystemExecute(Connection con, String choice,
                String gpu, String cpu, String mainboard, String ram,
                String case_, String hdd, int antal) throws IOException {

             try {

            PreparedStatement ps = con.prepareStatement("UPDATE Component "
                    + "SET amount = GREATEST(0, amount - " + antal + ")"
                    + " WHERE Component.name = '" + gpu + "'"
                    + " OR  Component.name = '" + cpu + "'"
                    + " OR  Component.name = '" + mainboard + "'"
                    + " OR  Component.name = '" + ram + "'"
                    + " OR  Component.name = '" + case_ + "'"
                    + " OR  Component.name = '" + hdd + "'");

            System.out.println("Sold " + antal + " " + choice + "\n");
            System.out.println("\npress [0] to return.\n");

            ps.executeUpdate();
            ps.close();

            IO.returnMenu();
            IO.input();
                        } catch(SQLException se) {
        }

        }

        public static void sellSystem(Connection con) throws IOException {

        String choice;
        int antal;
        System.out.println("PC-systems:\n----------------------------------");
        ComputerSystems.PCnames(con);
        Scanner sc = new Scanner(System.in);
        System.out.println("What PC-system do you want to sell?");
        System.out.println("... (write system-name"
                + " - OBS: its case-sensitive!)");

        choice = sc.nextLine();
        System.out.println("How many do you wish to sell?");
        antal = sc.nextInt();

        try {

            Statement st = con.createStatement();
            String query = "SELECT DISTINCT Computersystem.name, gpu,"
                    + " cpu, mainboard, ram, case_, hdd"
                    + " FROM Computersystem"
                    + " JOIN Component ON Computersystem.name = '"
                    + choice + "'";

                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {

                            String gpu = rs.getString("gpu");
                            String cpu = rs.getString("cpu");
                            String mainboard = rs.getString("mainboard");
                            String ram = rs.getString("ram");
                            String case_ = rs.getString("case_");
                            String hdd = rs.getString("hdd");
                            //int amount = rs.getInt("amount");
                            sellSystemExecute(con, choice, gpu, cpu, mainboard,
                                    ram, case_, hdd, antal);
                    }
            } catch (SQLException e) {
            }
        }
}