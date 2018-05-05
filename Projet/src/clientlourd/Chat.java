package clientlourd;

//import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tchatche.GestionMessages;

//import tchatche.GestionMessages;

public class Chat extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		cp = new JPanel(null);
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
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	    setLocationRelativeTo(null);

	}
	
	public void actumessage() {
		int nbmessage;
		String m;
		nbmessage = snbmessage()-23;
		if(nbmessage < 0) {
			nbmessage = 0;
		}
		for(int i = 0; i < 24; i++) {
			m = message(nbmessage);
			if(m != "Message non existant") {
			 text[i].setText(m);
			}
			else {
			 text[i].setText("");
			}
			nbmessage++;
		}
	}
	public int snbmessage() {
		final int PORT = 8888;
		try {
			Socket s = new Socket("localhost",PORT);
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			PrintWriter writer = new PrintWriter(out);
			writer.print("nbmessages");
			writer.flush();
			return Integer.parseInt(reader.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public String message(int n) {
		final int PORT = 8888;
		try {
			Socket s = new Socket("localhost",PORT);
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			PrintWriter writer = new PrintWriter(out);
			writer.print("n");
			writer.flush();
			return reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
