package basededonnees;

import java.sql.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Classe qui permet la connexion a la base de donnees
 */
public class Connect {
	private Connection c;
	private Statement st;
	private ResultSet rs;

	public Connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Java", "root", "mdproot");
		st = (Statement) c.createStatement();
	}

	public Connection getCnx() {
		return c;
	}

	public Statement getSmt() {
		return st;
	}

	public ResultSet getRes() {
		return rs;
	}

	public void reqSQL(String query, char type) {
		if (type == 's') {
			try {
				rs = st.executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				st.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
