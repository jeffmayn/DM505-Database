
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
public class Exercises_uge6_2_4_1_b {
    
    public static void main(String[] args) {
                
		String url = "jdbc:postgresql://localhost:5432/";
		String user = "postgres";
		String password = "123bum";
		Connection con = null;

		//CONNECTING
		try {
                       	con = DriverManager.getConnection(url, user, password);

		} catch (SQLException ex) {
		        Logger lgr = Logger.getLogger(ComputerStore.class.getName());
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
