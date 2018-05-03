package clientlourd;

import java.awt.BorderLayout;
//import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	private JButton b1 = new JButton("Envoyer");
	public Chat() {
		int nbmessage;
		String m;
		JTextField editbox;
		
		chat = GestionMessages.get();
		nbmessage = chat.nbmessage()-20;
		if(nbmessage < 0) {
			nbmessage = 0;
		}
		cp = new JPanel(new BorderLayout());
		text = new JLabel[25];
		int j = 0;
		for(int i = 0; i < 24; i++,j += 20) {
			
		  text[i] = new JLabel();
		  text[i].setLayout(null);
		  text[i].setLocation(new Point(0,j));
		  text[i].setSize(200, 13);
		  m = chat.afficherClientLourd(nbmessage);
		  if(m != "Message non existant") {
			  text[i].setText(m);
		  }
		  else {
			  text[i].setText("");
		  }
		  
		  nbmessage++;
		}
	

		
		
		editbox = new JTextField();	
		editbox.setLayout(null);
		editbox.setLocation(new Point(0,500));
		editbox.setSize(300, 30);
		cp.add(editbox);
		
		b1.setBounds(310, 500, 80, 30);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(!editbox.getText().isEmpty()) {
				 chat.add(editbox.getText(),"Lucas");
				}
				editbox.setText("");
				actumessage();
				setContentPane(cp);
			}
		});
	
		cp.add(b1);
		
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
	
	public void actumessage() {
		int nbmessage;
		String m;
		nbmessage = chat.nbmessage()-23;
		if(nbmessage < 0) {
			nbmessage = 0;
		}
		for(int i = 0; i < 24; i++) {
			m = chat.afficherClientLourd(nbmessage);
			if(m != "Message non existant") {
			 text[i].setText(m);
			}
			else {
			 text[i].setText("");
			}
			nbmessage++;
		}
	}
}
