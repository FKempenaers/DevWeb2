package basededonnees;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test extends Request {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connect c = new Connect();

		Statement st = c.getSmt();
		
		//clear_all_tables(st);
		
		//init(st);
		
		/*addUser(st, 1, "tao", "1234");
		addUser(st, 2, "francis", "4567");
		addUser(st, 3, "lucas", "8901");
		addGroup(st, 1, "L3 info");
		
		boolean ok = check_user("tao", "1234");
		
		if (ok) {
			System.out.println("true");
		}
		else {
			System.out.println("false");
		}
		
		ResultSet rs = select_all(st, "Utilisateur");
		
		while (rs.next()) {
			System.out.println("ID : " + rs.getString(1));
			System.out.println("Pseudo : " + rs.getString("pseudo"));
			System.out.println("MDP : " + rs.getString(3));
		}
		
		/*
		 Connect c = new Connect();
		 

		Statement st = c.getSmt();
		
		String query1 = "SELECT * FROM Utilisateur;";
		
		//ResultSet res = st.executeQuery(query1);
		c.reqSQL(query1, 's');
		ResultSet rs= c.getRes();
		while (rs.next()) {
			System.out.println("ID : " + rs.getString(1));
			System.out.println("Pseudo : " + rs.getString("pseudo"));
		}
		*/
		
		/*rs.close();
		st.close();
		c.getCnx().close();*/
	}
}