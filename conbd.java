package service_file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




/**
 * class of connection to data bases Sqllite
 * @author alejandro.a
 *	@date 22-10-18
 *
 */
public class conbd {
	Connection connect = (Connection) new conbd();
	String url = "http://localhost:8080/my_databases";
	/**
	 * Method connect to DB
	 */
	public void conect(){
		//String url = http:/localhost:8039/databases ;  
		try{
			connect = DriverManager.getConnection("Connected"+url);
		}catch(SQLException ex){
			System.out.println("Cant to Connected Data Bases" +ex.getMessage());
			
		}
	}
	/**
	 *  Method disconect to DB
	 * @throws SQLException 
	 */
	public void close() throws SQLException{
		connect.close();
	}
}
