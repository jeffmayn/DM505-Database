
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Restock {

    public static void restock(Connection con) throws IOException {

        int amount;
        int prefered;
        int restockAmount;

          System.out.println("Components to restock on saturday\n");
          System.out.printf("%-39s %3s %22s",
                  "Component", "Kind", "restock amount");

        try {

            Statement st = con.createStatement();
            String query = "SELECT *"
                    + " FROM Component "
                    + "WHERE amount <= allowedmin";

                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {

                            String name = rs.getString("name");
                            String kind = rs.getString("kind");
                            amount = rs.getInt("amount");
                            prefered = rs.getInt("preferedAmount");
                            restockAmount = prefered - amount;

                            System.out.printf("%n %-39s %3s %9d",
                                    name, kind, restockAmount);
                    }

            System.out.println("\n\npress [0] to return.\n");
            IO.returnMenu();
            IO.input();
             } catch (SQLException e) {
            }
    }
    public static void restockUpdate(Connection con, int restockAmount) {
    }
}
