package clientlourd;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class AllFiles  extends JFrame{
	private JPanel cp;
	private JLabel[] fichiers;
	private JButton[] bAF;
	
	public AllFiles() {
		cp = new JPanel(null);
		afficher_fichiers();
		JScrollPane sp = new JScrollPane();
		//RTextScrollPane sp = new RTextScrollPane(cp);
	    //cp.add(s);
		add(sp.add(cp));
		setContentPane(sp);
		setTitle("Liste des fichiers");
		pack();
	    setLocationRelativeTo(null);
	}
	public void afficher_fichiers() {
		int j = 40;
		fichiers = new JLabel[60];
		bAF = new JButton[60];
		for(int i = 0; i < 60;i++,j+=20) {
			fichiers[i] = new JLabel();
			fichiers[i].setLayout(null);
			fichiers[i].setLocation(new Point(0,j));
			fichiers[i].setSize(100, 17);
			fichiers[i].setText("fichier "+i);
			
			bAF[i] = new JButton("Editer "+i);
			bAF[i].setBounds(110, j, 80, 20);

			bAF[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
					  public void run() {
						try {
							String laf = UIManager.getSystemLookAndFeelClassName();
							UIManager.setLookAndFeel(laf);
						} catch (Exception e) { /* never happens */ }
						Chat chat = new Chat();
						chat.setSize(400, 600);
						chat.setVisible(true);
						chat.requestFocus();
					 }
				    });
					//chat.setVisible(true);
				    //Start all Swing applications on the EDT.
				    SwingUtilities.invokeLater(new Runnable() {
					 public void run() {
						 try {
							 String laf = UIManager.getSystemLookAndFeelClassName();
							 UIManager.setLookAndFeel(laf);
						 } catch (Exception e) { /* never happens */ }
						 editeur demo = new editeur(null,SyntaxConstants.SYNTAX_STYLE_C);
						 demo.setVisible(true);
						demo.gettextarea().requestFocusInWindow();
					  }
					});
				}
			});
			
			cp.add(fichiers[i]);
			cp.add(bAF[i]);
			
		}
	}
}
