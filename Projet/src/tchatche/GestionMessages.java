package tchatche;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GestionMessages {
	private ArrayList<Message> messages;
	private static GestionMessages chat = new GestionMessages();
	
	
	public GestionMessages() {
		this.messages = new ArrayList<Message>();
	}
	
	public String toString() {
		return messages.toString();
	}
	
	public static GestionMessages get() {
		return chat;
	}
	
	public void add(String p,String s) {
		Message m = new Message(p,s, new SimpleDateFormat("HH:mm:SS", Locale.FRANCE).format(new Date()));
		messages.add(m);
	}
	
	public void afficher() {
		System.out.println(this.toString()+"<br/>");
	}
	
}
