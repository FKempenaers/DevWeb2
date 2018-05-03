package clientlourd;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

public class Administration extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel cp;
	private JLabel[] fichiers;
	private JButton[] bAF;
	
	public Administration() {
		
		cp = new JPanel(null);
		JLabel lf = new JLabel();
		lf.setLayout(null);
		lf.setLocation(new Point(0,20));
		lf.setSize(200, 13);
		lf.setText("Liste des fichiers");
		cp.add(lf);
		affiche_fichiers();
		
		
		setContentPane(cp);
		setTitle("Administration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void affiche_fichiers() {
		int j = 40;
		fichiers = new JLabel[20];
		bAF = new JButton[20];
		for(int i = 0; i < 20;i++,j+=20) {
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
