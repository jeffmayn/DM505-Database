import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Components {

        public static void All_components(Connection con) throws IOException{

                    try {

            Statement st = con.createStatement();
            String query = "SELECT * FROM Component"
                    + " ORDER BY kind";

                    ResultSet rs = st.executeQuery(query);
                    System.out.printf("%-39s %3s %22s %18s %10s",
                            "Component", "Kind", "Current amount",
                            "Allowed minimum", "Prefered");

                    while (rs.next()) {
                            int amount = rs.getInt("amount");
                            int allowedMin = rs.getInt("allowedMin");
                            int prefered = rs.getInt("preferedAmount");
                            String name = rs.getString("name");
                            String kind = rs.getString("kind");

                            System.out.printf("%n %-39s %3s %9d %17d %13d",
                                    name, kind, amount, allowedMin, prefered);
                    }
                    System.out.println("\n\npress [0] to return.\n");
                    IO.returnMenu();
                    IO.input();

            } catch (SQLException e) {
            }
	}
}
