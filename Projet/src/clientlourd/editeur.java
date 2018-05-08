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

public class editeur extends JFrame implements ActionListener {

   private static final long serialVersionUID = 1L;

   private RSyntaxTextArea textArea;
   private JTextField searchField;
   private JCheckBox regexCB;
   private JCheckBox matchCaseCB;
   private String doc;
   
   final int PORT = 8888;
   private InputStream in;
   private OutputStream out;
   BufferedReader reader;
   private Socket s;

   public editeur(String doc,String style) {

      JPanel cp = new JPanel(new BorderLayout());
      this.doc = doc;
      textArea = new RSyntaxTextArea(this.doc,45, 100);
      textArea.setSyntaxEditingStyle(style);
      textArea.setCodeFoldingEnabled(true);
      RTextScrollPane sp = new RTextScrollPane(textArea);
      cp.add(sp);
      cp.addKeyListener(new KeyListener() {

    	  @Override
	      public void keyPressed(KeyEvent arg0) {
		      // TODO Auto-generated method stub
		
	      }

    	  @Override
    	  public void keyReleased(KeyEvent arg0) {
    		  String text = searchField.getText();
    		  // TODO Auto-generated method stub
    		  try {
    				s = new Socket("localhost",PORT);
    				in = s.getInputStream();
    				out = s.getOutputStream();
    				reader = new BufferedReader(new InputStreamReader(in));
    				PrintWriter writer = new PrintWriter(out);
    			    writer.print("fichier\n"+text+"\n;;//*::::;;;;:;\nxyz\n");
    				writer.flush();
    				
    			} catch (UnknownHostException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
		
    	  }

    	  @Override
    	  public void keyTyped(KeyEvent arg0) {
    		  // TODO Auto-generated method stub
		
    	  }

      });
  

      setContentPane(cp);
      setTitle("Editeur");
      pack();
      setLocationRelativeTo(null);

   }
   public RSyntaxTextArea gettextarea() {
	   return textArea;
   }

   public void actionPerformed(ActionEvent e) {

      // "FindNext" => search forward, "FindPrev" => search backward
      /*String command = e.getActionCommand();
      boolean forward = "FindNext".equals(command);
      System.err.println("lol");
      // Create an object defining our search parameters.
      SearchContext context = new SearchContext();
      String text = searchField.getText();
      System.out.println("test!!");
      if (text.length() == 0) {
         return;
      }
      
      context.setSearchFor(text);
      context.setMatchCase(matchCaseCB.isSelected());
      context.setRegularExpression(regexCB.isSelected());
      context.setSearchForward(forward);
      context.setWholeWord(false);

      try {
		s = new Socket("localhost",PORT);
		in = s.getInputStream();
		out = s.getOutputStream();
		reader = new BufferedReader(new InputStreamReader(in));
		PrintWriter writer = new PrintWriter(out);
	    writer.print("fichier\n"+text+"\n;;//*::::;;;;:;\nxyz\n");
		writer.flush();
		
	} catch (UnknownHostException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

      
      boolean found = SearchEngine.find(textArea, context).wasFound();
      if (!found) {
         JOptionPane.showMessageDialog(this, "Text not found");
      }*/

   }

}