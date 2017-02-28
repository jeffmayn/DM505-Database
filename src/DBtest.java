import java.sql.*;
import java.util.logging.*;


// java -cp postgresql-9.4-1201.jdbc4.jar:. DBtest

public class DBtest {

    
	public static void main(String[] args) {
                
		String url = "jdbc:postgresql://localhost:5432/";
		String user = "postgres";
		String password = "123bum";
		Connection con = null;

		//CONNECTING
		try {
                       	con = DriverManager.getConnection(url, user, password);

		} catch (SQLException ex) {
		        Logger lgr = Logger.getLogger(DBtest.class.getName());
    			lgr.log(Level.WARNING, ex.getMessage(), ex);

		}

		testDB(con);
	}

	public static void testDB(Connection con){
         
            try {
			Statement st = con.createStatement();
		        String query = "SELECT DISTINCT maker, hd "
                                + "FROM Product, Laptop "
                                + "WHERE (Product.model = Laptop.model) AND Laptop.hd >= 100";
                        
		        ResultSet rs = st.executeQuery(query);
                        System.out.println("Følgende producenter har lavet bærbare med en harddisk størrelse på minimum 100gb:\n");
		        while (rs.next()) {
		            	String maker = rs.getString("maker");
                                int hd = rs.getInt("hd");
		            	System.out.printf("Producent: " + maker + "Harddisk størrelse: " + hd + "gb \n");
		        }
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
      
      
}
