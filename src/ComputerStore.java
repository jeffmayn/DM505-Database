
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
public class ComputerStore {
    
    
 
       public static void main(String[] args) {

        start();
        connect();
        
    }

    public static void start() {
        System.out.println("Welcome to the computerstore ...\n");
    }

    public static void connect() {
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

		All_components(con);
    }
    
    public static void All_components(Connection con){
            try {
			Statement st = con.createStatement();
		        String query = "SELECT *"
                                + "FROM CPU";
                                
                                //+ "WHERE *";

		        ResultSet rs = st.executeQuery(query);
                        
                        System.out.println("Components:\t\t\t\t\t\t\tQuantity:\n-----------------------------------------------------------------");
		        while (rs.next()) {
                                String type = rs.getString("type");
		            	String name = rs.getString("name");
                                Integer quantity = rs.getInt("quantity");

		            	System.out.println("(" + type + ") " + name + "\t" + quantity);
		        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
