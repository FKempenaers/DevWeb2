package basededonnees;
import java.util.*;
import java.text.*;
public class Message {

	private String message;
	private String user;
	private String date;
	private final int NUM_MESSAGE;
	private static int nbMessages = 0;

	Message(String message, String user, String date) {
		this.message = message;
		this.user = user;
		this.date = date;
		NUM_MESSAGE = ++nbMessages;
	}
	public String getMessage() {
		return message;
	}
	public int getNum() {
		return NUM_MESSAGE;
	}
	public String getUser() {
		return user;
	}
	public String getDate() {
		return date;
	}
	
	public String toString() {
		return "["+date.toString()+"] "+user+" : "+message+"<br/>";	
	}
	
}
