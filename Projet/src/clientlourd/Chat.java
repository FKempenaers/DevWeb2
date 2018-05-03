package clientlourd;

import java.awt.BorderLayout;
//import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tchatche.GestionMessages;

//import tchatche.GestionMessages;

public class Chat extends JFrame {
	private GestionMessages chat;
	private JPanel cp;
	private JLabel text[];
	public Chat() {
		int nbmessage;
		JTextField editbox;
		
		chat = GestionMessages.get();
		nbmessage = chat.nbmessage()-20;
		if(nbmessage < 0) {
			nbmessage = 0;
		}
		cp = new JPanel(new BorderLayout());
		text = new JLabel[25];
		int j = 0;
		for(int i = 0; i < 25; i++,j += 20) {
			
		  text[i] = new JLabel();
		  text[i].setLayout(null);
		  text[i].setLocation(new Point(0,j));
		  text[i].setSize(200, 13);
		  text[i].setText(chat.afficherClientLourd(nbmessage));
		  
		  nbmessage++;
		}
	
		
	
		
		editbox = new JTextField();	
		editbox.setLayout(null);
		editbox.setLocation(new Point(0,500));
		editbox.setSize(300, 30);
		cp.add(editbox);
		
		text[24] = new JLabel();
		text[24].setLayout(null);
		text[24].setLocation(new Point(0,480));
		text[24].setSize(100, 13);
		text[24].setText("");
		
	    for(int i = 0;i<25;i++) {
			 cp.add(text[i]);
		}
		
		
		setContentPane(cp);
		setTitle("Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	    setLocationRelativeTo(null);

	}
}
