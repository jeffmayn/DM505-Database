
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
public class Exercises_uge6_6_1_3_d {
    
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
		        String query = "SELECT model, ram, screen "
                                + "FROM Laptop "
                                + "WHERE price > 1500";
                        
		        ResultSet rs = st.executeQuery(query);
                        System.out.println("Find modelnummer, hukommelses-størrelse, og skærm-størrelse for bærbare der koster mere end $1500: \n");
		        while (rs.next()) {
		            	int model = rs.getInt("model");
                                int memorySize = rs.getInt("ram");
                                int screenSize = rs.getInt("screen");
		            	System.out.printf("Modelnummer: " + model + "       Hukommelse: " + memorySize + "      Skærm: " + screenSize + "\"\n");
		        }
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
    
}
