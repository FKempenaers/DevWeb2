package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import tchatche.GestionMessages;

public class Serveur extends Thread{
	private GestionMessages chat;
	private boolean newmodif;
	private String fichier;
	
	public Serveur() {
		chat = new GestionMessages();
		this.fichier = "";
		this.start();
	}
	public boolean getnewmodif() {
		return newmodif;
	}
	public void setnewmodif(boolean newmodif) {
		this.newmodif = newmodif;
	}
	public String getfichier() {
		return fichier;
	}
	public void setfichier(String fichier) {
		this.fichier = fichier;
	}
	
	public void run() {
		final int PORT = 8888;
		try {
			ServerSocket server = new ServerSocket(PORT, 1);
			String ligne,l1,l2;
			while(true) {
			Socket s = server.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream());
			
				ligne = in.readLine();
				while(!(ligne.equals("xyz") && ligne != null)) {
					if(ligne.equals("nbmessages")) {
						out.println(chat.nbmessage());
						out.flush();
					}
					else if(ligne.equals("add")) {
						l1= in.readLine();
						l2= in.readLine();
						if(!ligne.equals("")) {
							chat.add(l1, l2);
						}
					}
					else if(ligne.equals("fichier")) {
						String f ="";
						ligne = in.readLine();
						while(!(ligne.equals(";;//*::::;;;;:;"))) {
							f = f + ligne;
							ligne = in.readLine(); 
							if(!(ligne.equals(";;//*::::;;;;:;"))) {
								f = f + '\n';
							}
						}
						fichier = f;
						newmodif = true;
					}
					else if(ligne.equals("getfichier")) {
						out.println(fichier+"\n;;//*::::;;;;:;\n");
						out.flush();
					}
					else if(ligne.equals("connexion")) {
						l1 = in.readLine();
						l2 = in.readLine();
						try {
							if(basededonnees.Request.check_user(l1, l2)) {
								out.println("true\n");
								out.flush();
							}
							else {
								out.println("false\n");
								out.flush();
							}
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
						if(!ligne.equals("xyz")) {
						 out.println(chat.afficherClientLourd(Integer.parseInt(ligne)));
						 out.flush();
						}
					}
					ligne = in.readLine();
				}
				s.close();
				
			}
		} catch (IOException e) {}
		
	}
	
	public GestionMessages getChat() {
		return chat;
	}
	
}
