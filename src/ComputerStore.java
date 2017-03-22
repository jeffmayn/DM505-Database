
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
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
        scan();

    }
       
       public static void scan(){
           Scanner sc = new Scanner(System.in);
           
           
           if (sc.nextInt() == 1) {
                connect();  
           }
       }
       
        public static void menu(){
            
           System.out.println("+---------------------------------------+");
           System.out.println("|               OPTIONS                 |");
           System.out.println("+---------------------------------------+");
           System.out.println("|  Type [1]   -   Current stock         |");
           System.out.println("|  Type [2]   -   Minimum inventory     |");
           System.out.println("|  Type [3]   -   Print price list      |");
           System.out.println("|  Type [4]   -   List for restocking   |");
           System.out.println("|                                       |");
           System.out.println("|                                       |");
           System.out.println("|                                       |");
           System.out.println("+---------------------------------------+");
           
           }

    public static void start() {
        System.out.println("Welcome to the Computerstore Stock Manager (SSM). \n");
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
		        String query = "SELECT CPU.name, CPU.type "
                                + "FROM CPU "
                                + "UNION "

                                + "SELECT RAM.name, RAM.type "
                                + "FROM RAM "
                                + "UNION "

                                + "SELECT GraphicCards.name, GraphicCards.type "
                                + "FROM GraphicCards "
                                + "UNION "

                                + "SELECT Mainboard.name, Mainboard.type "
                                + "FROM Mainboard "
                                + "UNION "

                                + "SELECT CASE_.name, CASE_.type "
                                + "FROM CASE_ "

                                + "ORDER BY type";



		        ResultSet rs = st.executeQuery(query);
                        System.out.println("List of components and their current amount ...\n");
                        System.out.println("Qty:\tComponent:\t\t\t\t\tType:");
		        while (rs.next()) {
                               // String type = rs.getString("type");
		            	String name = rs.getString("name");
                                String type = rs.getString("type");

                                int qty = 1;

		            	System.out.println(qty + "\t" + name + "\t" + type);

		        }
                        System.out.println("\npress [0] to go back.\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
