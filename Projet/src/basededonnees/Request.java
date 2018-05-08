package basededonnees;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Request {
	public void init(Statement st) throws ClassNotFoundException, SQLException {		
		//String foreign_key_check = "set FOREIGN_KEY_CHECKS = 0;";
		
		String table_user = "create table Utilisateur (id_user INT," +
							"pseudo VARCHAR(40)," +
							"mdp VARCHAR(40), " +
							"PRIMARY KEY(id_user));";
		
		String table_groupe = "create table Groupe (id_groupe INT," +
							  "nom VARCHAR(20)," +
							  "PRIMARY KEY(id_groupe));";
		
		String table_fichier = "create table Fichier (id_file INT," +
							   "nom VARCHAR(40)," +
							   "lien_fichier VARCHAR(100)," +
							   "visibilite INT," +
							   "PRIMARY KEY(id_file));";
		
		
		String table_modifie = "create table Modifie (id_user_m INT," +
								"id_file_m INT," + 
								"CONSTRAINT Mpk PRIMARY KEY (id_user_m, id_file_m)," +
								"CONSTRAINT Mfk1 FOREIGN KEY (id_user_m) REFERENCES Utilisateur(id_user)," + 
								"CONSTRAINT Mfk2 FOREIGN KEY (id_file_m) REFERENCES Fichier(id_file));";
		
		String table_appartient = "create table Appartient (id_user_a INT," +
								   "id_groupe_a INT," +
								   "CONSTRAINT Afk1 FOREIGN KEY (id_user_a) REFERENCES Utilisateur(id_user)," +
								   "CONSTRAINT Afk2 FOREIGN KEY (id_groupe_a) REFERENCES Fichier(id_file), " +
								   "CONSTRAINT Apk PRIMARY KEY (id_user_a, id_groupe_a));";
		
		String table_contient = "create table Contient (id_groupe_c INT," + 
								 "id_file_c INT," +
								 "CONSTRAINT Cfk1 FOREIGN KEY (id_groupe_c) REFERENCES Groupe(id_groupe)," +
								 "CONSTRAINT Cfk2 FOREIGN KEY (id_file_c) REFERENCES Fichier(id_file)," +
								 "CONSTRAINT Cpk PRIMARY KEY (id_groupe_c, id_file_c));" ;
		
		st.executeUpdate(table_user);
		st.executeUpdate(table_groupe);
		st.executeUpdate(table_fichier);
		
		st.executeUpdate(table_modifie);
		st.executeUpdate(table_appartient);
		st.executeUpdate(table_contient);
	}
	
	public void clear_all_tables(Statement st) throws ClassNotFoundException, SQLException {	
		String clear_all_tables = "drop table Appartient, Modifie, Contient, Groupe, Utilisateur, Fichier";
		
		st.executeUpdate(clear_all_tables);
	}
	
	public void addUser(Statement st, int id, String pseudo, String mdp) throws SQLException {
		String addUser = "insert into Utilisateur values ("+id+", "+"\""+pseudo+"\""+", "+"\""+mdp+"\""+");";
		
		st.executeUpdate(addUser);
	}
	
	public void addGroup(Statement st, int id, String name) throws SQLException {
		String addGroup = "insert into Groupe values ("+id+", "+"\""+name+"\""+");";
		
		st.executeUpdate(addGroup);
	}
	
	public ResultSet select_all(Statement st, String table) throws SQLException {
		String select = "select * from "+table+";";

		ResultSet rs = st.executeQuery(select);
		
		return rs;
	}
	
	public boolean check_user(String pseudo, String mdp) throws ClassNotFoundException, SQLException {
		Connect cnx = new Connect();	
		
		Statement st = cnx.getSmt();
		
		String check_user = "select pseudo, mdp from Utilisateur where pseudo = \""+pseudo+"\" and mdp = \""+mdp+"\";";
		System.out.println(check_user);
		
		ResultSet rs = st.executeQuery(check_user);
		
		if (rs == null) {
			return false;
		} 
		else {
			return true;
		}
	}
}

