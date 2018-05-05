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
			GestionMessages chat = new GestionMessages();
			ServerSocket server = new ServerSocket(PORT, 1);
			
			Socket s = server.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream());
			String ligne;
			while(true) {
				ligne = in.readLine();
				if(ligne.equals("nbmessages")) {
					out.println(chat.nbmessage());
					out.flush();
				}
				else {
					out.println(chat.afficherClientLourd(Integer.parseInt(ligne)));
				  	out.flush();
				}
			}
		} catch (IOException e) {}
	}
	
	public GestionMessages getChat() {
		return chat;
	}
	
}
