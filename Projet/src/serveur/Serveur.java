package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import tchatche.GestionMessages;

public class Serveur extends Thread{
	private GestionMessages chat;
	
	public Serveur() {
		chat = new GestionMessages();
		this.start();
	}
	
	public void run() {
		final int PORT = 8888;
		try {
			ServerSocket server = new ServerSocket(PORT, 1);
			while(true) {
			Socket s = server.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream());
			String ligne,l1,l2;
			System.err.println("SERVER - NB Messages : " + chat.nbmessage());
			
				ligne = in.readLine();
				while(!(ligne == "xyz")) {
					System.err.println("SERVER - NB Messages : " + chat.nbmessage());
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
					else {
						if(!ligne.equals("xyz")) {
						 out.println(chat.afficherClientLourd(Integer.parseInt(ligne)));
						 out.flush();
						}
					}
					ligne = in.readLine();
				}
				
			}
		} catch (IOException e) {}
		
	}
	
	public GestionMessages getChat() {
		return chat;
	}
	
}
