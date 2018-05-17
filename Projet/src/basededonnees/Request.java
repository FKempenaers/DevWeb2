package basededonnees;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Request {
	public static void init(Statement st) throws ClassNotFoundException, SQLException {		
		String foreign_key_check = "set FOREIGN_KEY_CHECKS = 0;";
		
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
							   "type_fichier VARCHAR(20)," +
							   "visibilite INT," +
							   "PRIMARY KEY(id_file));";
		
		
		String table_modifie = "create table Modifie (id_user_m INT," +
								"id_file_m INT," + 
								"CONSTRAINT Mpk PRIMARY KEY (id_user_m, id_file_m)," +
								"CONSTRAINT Mfk1 FOREIGN KEY (id_user_m) REFERENCES Utilisateur(id_user) ON DELETE CASCADE," + 
								"CONSTRAINT Mfk2 FOREIGN KEY (id_file_m) REFERENCES Fichier(id_file) ON DELETE CASCADE);";
		
		String table_appartient = "create table Appartient (id_user_a INT," +
								   "id_groupe_a INT," +
								   "CONSTRAINT Afk1 FOREIGN KEY (id_user_a) REFERENCES Utilisateur(id_user) ON DELETE CASCADE," +
								   "CONSTRAINT Afk2 FOREIGN KEY (id_groupe_a) REFERENCES Fichier(id_file) ON DELETE CASCADE, " +
								   "CONSTRAINT Apk PRIMARY KEY (id_user_a, id_groupe_a));";
		
		String table_contient = "create table Contient (id_groupe_c INT," + 
								 "id_file_c INT," +
								 "CONSTRAINT Cfk1 FOREIGN KEY (id_groupe_c) REFERENCES Groupe(id_groupe) ON DELETE CASCADE," +
								 "CONSTRAINT Cfk2 FOREIGN KEY (id_file_c) REFERENCES Fichier(id_file) ON DELETE CASCADE," +
								 "CONSTRAINT Cpk PRIMARY KEY (id_groupe_c, id_file_c));" ;
		
		st.executeUpdate(foreign_key_check);
		
		st.executeUpdate(table_user);
		st.executeUpdate(table_groupe);
		st.executeUpdate(table_fichier);
		
		st.executeUpdate(table_modifie);
		st.executeUpdate(table_appartient);
		st.executeUpdate(table_contient);
	}
	
	public static void clear_all_tables(Statement st) throws ClassNotFoundException, SQLException {	
		String clear_all_tables = "drop table Appartient, Modifie, Contient, Groupe, Utilisateur, Fichier";
		
		st.executeUpdate(clear_all_tables);
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
	
	public static boolean addUser(String pseudo, String mdp) throws SQLException, ClassNotFoundException {
		Connect cnx = new Connect();
		
		Statement st = cnx.getSmt();
		
		String user_already_exists = "select pseudo from Utilisateur where pseudo = \""+pseudo+"\";";
		String previous_id = "select * from Utilisateur;";
		
		ResultSet p_id = st.executeQuery(previous_id);
		boolean check = p_id.last();
		int new_id = 0;
		if (check) {
			new_id = p_id.getInt(1) + 1;
		}
		
		ResultSet rs_user = st.executeQuery(user_already_exists);
			
		if (!rs_user.isBeforeFirst()) {
			String addUser = "insert into Utilisateur values ("+new_id+", "+"\""+pseudo+"\""+", "+"\""+mdp+"\");";
			
			st.executeUpdate(addUser);
				
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean check_user(String pseudo, String mdp) throws ClassNotFoundException, SQLException {
		Connect cnx = new Connect();	
		
		Statement st = cnx.getSmt();
		
		String check_user = "select pseudo, mdp from Utilisateur where pseudo = \""+pseudo+"\" and mdp = \""+mdp+"\";";
		
		ResultSet rs = st.executeQuery(check_user);
		
		boolean check = rs.isBeforeFirst();
		
		rs.close();
		st.close();
		cnx.getCnx().close();
		
		return check;
	}
	
	public static ArrayList<String[]> user_file (String pseudo) throws ClassNotFoundException, SQLException {
		int i = 0;
		ArrayList<String[]> fichier_infos = new ArrayList<String[]>();

		Connect cnx = new Connect();
		
		Statement st = cnx.getSmt();
		
		String user_file = "select id_file, pseudo, nom, lien_fichier from Utilisateur, Fichier, Modifie where id_user_m = id_user and id_file = id_file_m and pseudo = \""+pseudo+"\";";
		
		ResultSet rs = st.executeQuery(user_file);
		
		while (rs.next()) {
			String[] infos = new String[4];
			infos[0] = rs.getString(1);
			infos[1] = rs.getString(2);
			infos[2] = rs.getString(3);
			infos[3] = rs.getString(4);
			
			/*
			System.out.println("id : " + rs.getString(1));
			System.out.println("pseudo : " + rs.getString(2));
			System.out.println("nom fichier : " + rs.getString(3));
			System.out.println("lien fichier : " + rs.getString(4));
			*/
			
			fichier_infos.add(i, infos);
			i++;
		}
		
		rs.close();
		st.close();
		cnx.getCnx().close();
		
		return fichier_infos;
	}
	
	public static int addFile(String pseudo, String name_file, String link_file) throws ClassNotFoundException, SQLException {
		Connect cnx = new Connect();
		
		Statement st = cnx.getSmt();
		
		String previous_id = "select * from Fichier;";
		String uid ="select id_user from Utilisateur where pseudo = \""+pseudo+"\";";
		
		String delFile = "delete from Fichier where lien_fichier =\""+link_file+"\";";	
		st.executeUpdate(delFile);
		
		ResultSet u_id = st.executeQuery(uid);
		
		boolean checkuser = u_id.last();
		if (checkuser) {
			int id_user = 0;
			if (checkuser) {
				id_user = u_id.getInt(1);
			}
			
			ResultSet p_id = st.executeQuery(previous_id);
			
			boolean check = p_id.last();
			int new_id = 0;
			if (check) {
				new_id = p_id.getInt(1) + 1;
			}
				
			String addFile = "insert into Fichier values ("+new_id+", "+"\""+name_file+"\""+", "+"\""+link_file+"\", \"src\", \"0\");";
			String modify = "insert into Modifie values ("+id_user+", "+new_id+");";
		
			st.executeUpdate(addFile);
			st.executeUpdate(modify);
			
			p_id.close();
			u_id.close();
			st.close();
			cnx.getCnx().close();
			
			return new_id;
		}
		else {
			u_id.close();
			st.close();
			cnx.getCnx().close();
			
			return -1;
		}
	}
	
	public static boolean addUsertoFile (String pseudo, int id_file) throws ClassNotFoundException, SQLException {
		Connect cnx = new Connect();
		Statement st = cnx.getSmt();
		
		String getUid = "select id_user from Utilisateur where pseudo = \""+pseudo+"\";";
		
		ResultSet rs = st.executeQuery(getUid);
		
		boolean check = rs.last();
		if (check) {
			int id = rs.getInt(1);
			
			String addUsertoFile = "insert into Modifie values ("+id+", "+id_file+");";
			
			st.executeUpdate(addUsertoFile);
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean delUsertoFile (String pseudo, int id_file) throws ClassNotFoundException, SQLException {
		Connect cnx = new Connect();
		Statement st = cnx.getSmt();
		
		String getUid = "select id from Utilisateur where pseudo = \""+pseudo+"\";";
		
		ResultSet rs = st.executeQuery(getUid);
		boolean check = rs.last();
		if (check) {
			int id = rs.getInt(1);
			
			String delUsertoFile = "delete from Modifie where is_user_m = "+id+" and id_file_m = "+id_file+";";
			
			st.executeUpdate(delUsertoFile);
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public static ArrayList<String> list_user (int id_file) throws ClassNotFoundException, SQLException {
		int i = 0;
		Connect cnx = new Connect();
		Statement st = cnx.getSmt();
		
		ArrayList<String> liste = new ArrayList<String>();
		
		String list_user = "select pseudo from Utilisateur, Modifie where id_file_m = "+id_file+" and id_user_m = id_user;";
		
		ResultSet rs = st.executeQuery(list_user);
		
		while (rs.next()) {
			liste.add(i, rs.getString(2));
			i++;
		}
		
		rs.close();
		st.close();
		cnx.getCnx().close();
		
		return liste;
	}
}

