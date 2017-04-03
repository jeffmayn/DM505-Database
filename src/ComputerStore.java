import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ComputerStore {

    public static Connection con;
    public static int CPU_count;

    ComputerStore() {

                System.out.println("connecting to database ...");
                String url = "jdbc:postgresql://localhost:5432/";
		String user = "postgres";
		String password = "123bum";
		ComputerStore.con = null;

		//CONNECTING
		try {
                       	con = DriverManager.getConnection(url, user, password);
                        System.out.println("connection established!\n\n");

		} catch (SQLException ex) {
                        System.out.println
                        ("connection failed .. restart and try again.");
		}
    }

       public static void main(String[] args) throws IOException, SQLException{

        ComputerSystems pc = new ComputerSystems();
        IO io = new IO();
        io.input();
    }
}
