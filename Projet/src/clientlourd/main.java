package clientlourd;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;



public class main {

	public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			try {
				String laf = UIManager.getSystemLookAndFeelClassName();
				UIManager.setLookAndFeel(laf);
			} catch (Exception e) { /* never happens */ }
			Administration admin = new Administration();
			admin.setSize(1024, 760);
			admin.setVisible(true);
			admin.requestFocus();
		}
	});
	/*SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			try {
				String laf = UIManager.getSystemLookAndFeelClassName();
				UIManager.setLookAndFeel(laf);
			} catch (Exception e) { /* never happens */ /*}
			/*Chat chat = new Chat();
			chat.setSize(400, 600);
			chat.setVisible(true);
			chat.requestFocus();
		}
	   });*/
		//chat.setVisible(true);
	 // Start all Swing applications on the EDT.
	 /*SwingUtilities.invokeLater(new Runnable() {
		 public void run() {
			 try {
				 String laf = UIManager.getSystemLookAndFeelClassName();
				 UIManager.setLookAndFeel(laf);
			 } catch (Exception e) { /* never happens */ /*}
			 editeur demo = new editeur(null,SyntaxConstants.SYNTAX_STYLE_C);
			 demo.setVisible(true);
			demo.gettextarea().requestFocusInWindow();
		  }
		 });*/
	 

	}
}
