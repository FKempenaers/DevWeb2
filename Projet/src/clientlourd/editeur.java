package clientlourd;

import java.awt.*;
import java.awt.event.*;
import java.beans.EventHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class editeur extends JFrame /*implements ActionListener,*/ {

   private static final long serialVersionUID = 1L;

   private RSyntaxTextArea textArea;
   private static JTextField searchField;
   private JCheckBox regexCB;
   private JCheckBox matchCaseCB;
   private String doc;

   
   public editeur(String doc,String style) {
	   super();
      JPanel cp = new JPanel(new BorderLayout());
      this.doc = doc;
      textArea = new RSyntaxTextArea(this.doc,45, 100);
      textArea.setSyntaxEditingStyle(style);
      textArea.setCodeFoldingEnabled(true);
      RTextScrollPane sp = new RTextScrollPane(textArea);
      cp.add(sp);
      System.out.println("okv");
      setContentPane(cp);
      setTitle("Editeur");
      pack();
      setLocationRelativeTo(null);
      textArea.addKeyListener(new TitreKeyListener(this));
      textArea.setFocusable(true);
   }
   public RSyntaxTextArea gettextarea() {
	   return textArea;
   }
   
   
   public String gettext() {
	   
	   return textArea.getText();
   }
   public void settext(String s) {

	   textArea.setText(s);
   }

  
}

class TitreKeyListener implements KeyListener {
	   final int PORT = 8888;
	   private InputStream in;
	   private OutputStream out;
	   BufferedReader reader;
	   private Socket s;
		
	   boolean newmodif = true;
	   editeur ed;
    public TitreKeyListener(editeur e) {
    	this.ed = e;
    }
 
    public void keyPressed(KeyEvent e) {
    	String st,ligne;
    	try {
			s = new Socket("localhost",PORT);
			in = s.getInputStream();
			out = s.getOutputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			PrintWriter writer = new PrintWriter(out);
			writer.print("getfichier\nxyz\n");
			writer.flush();
			st ="";
			ligne = reader.readLine();
			while(!ligne.equals(";;//*::::;;;;:;")) {
				st = st + ligne;
				ligne = reader.readLine();
				if(!ligne.equals(";;//*::::;;;;:;")) {
					st = st + '\n';
				}
			}
			ed.settext(st);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
 
    public void keyReleased(KeyEvent e) {
    	try {
    		s = new Socket("localhost",PORT);
			in = s.getInputStream();
			out = s.getOutputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			PrintWriter writer = new PrintWriter(out);

			System.out.println(ed.gettext()+"FIN!!!");
			writer.print("fichier\n"+ed.gettext()+"\n"+";;//*::::;;;;:;\nxyz\n");
			writer.flush();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
 
    public void keyTyped(KeyEvent e) {
        // on ne fait rien
    }
}