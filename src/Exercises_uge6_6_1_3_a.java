
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LRRR
 */
public class Exercises_uge6_6_1_3_a {
    
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
		        String query = "SELECT model, speed AS gigahertz, hd AS gigabytes "
                                + "FROM PC "
                                + "WHERE price < 1000";
                        
		        ResultSet rs = st.executeQuery(query);
                        System.out.println("Find modelnummer, hastighed og HDD størrelse for alle PC'ere hvor prisen er under $1000:\n");
		        while (rs.next()) {
		            	String model = rs.getString("model");
                                int speed = rs.getInt("gigahertz");
                                int hd = rs.getInt("gigabytes");
		            	System.out.printf("Model: " + model + "Hastighed: " + speed + "Ghz               " + "Harrdisk størrelse: " + hd + "gb\n");
		        }
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
    
}
