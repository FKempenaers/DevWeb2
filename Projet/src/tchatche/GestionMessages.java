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
	
	public String afficher() {
		String s = "";
		if (messages.size() != 0) {
			s += "<table>";
			for(Message m : messages) {
				s += "<tr><td>"+m.getDate()+"</td><td>"+m.getUser()+" : "+m.getMessage()+"</td></tr>";
			}
			s+= "</table>";
		}
		return s;
	}
	public String afficherClientLourd(int n) {
		for(Message m : messages) {
			System.out.println(m.getMessage());
			if(m.getNum() == n) {
				return m.getMessage();
			}
		}
		return "Message non existant";
	}
	public int nbmessage() {
		return messages.size();
	}
	
}
