package clientlourd;

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
	private JLabel conUser;
	private JLabel conMP;
	JTextField conUserr;
	JTextField conMPr;
	private JButton connexion;
	private JButton[] bAF;
	private JButton[] bAFA;
	private JButton preced,suivant;
	private int n;
	
	final int PORT = 8888;
	private InputStream in;
	private OutputStream out;
	BufferedReader reader;
	private Socket s;
	
	public Administration() {
		
		cp = new JPanel(null);
		n = 0;
		connexion();
		connexion = new JButton("Connexion ");
		connexion.setBounds(150,80,100,20);
		connexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ligne;
				try {
					s = new Socket("localhost",PORT);
					in = s.getInputStream();
					out = s.getOutputStream();
					reader = new BufferedReader(new InputStreamReader(in));
					PrintWriter writer = new PrintWriter(out);
					writer.print("connexion\n"+conUserr.getText()+"\n"+conMPr.getText()+"\nxyz\n");
					writer.flush();
					ligne = reader.readLine();
					if(ligne.equals("true")) {
						JLabel lf = new JLabel();
						lf.setLayout(null);
						lf.setLocation(new Point(0,20));
						lf.setSize(200, 13);
						lf.setText("Liste des fichiers");
						cp.add(lf);
						affiche_fichiers();
					}
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});			
		cp.add(connexion);
		setContentPane(cp);
		setTitle("Administration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void connexion() {
		String ligne;
		conUserr = new JTextField();	
		conUserr.setLayout(null);
		conUserr.setLocation(new Point(0,10));
		conUserr.setSize(300, 30);
		cp.add(conUserr);
		conMPr = new JTextField();	
		conMPr.setLayout(null);
		conMPr.setLocation(new Point(0,40));
		conMPr.setSize(300, 30);
		cp.add(conMPr);
		conUser = new JLabel();
		conUser.setLayout(null);
		conUser.setLocation(new Point(300,10));
		conUser.setSize(300, 17);
		conUser.setText("Utilisateur");
		cp.add(conUser);
		conMP = new JLabel();
		conMP.setLayout(null);
		conMP.setLocation(new Point(300,40));
		conMP.setSize(300, 17);
		conMP.setText("Mot de passe");
		cp.add(conMP);
		
	}
	
	public void affiche_fichiers() {
		int j = 40;
		fichiers = new JLabel[20];
		bAF = new JButton[20];
		bAFA = new JButton[20];
		for(int i = n; i < n+20;i++,j+=20) {
			fichiers[i-n] = new JLabel();
			fichiers[i-n].setLayout(null);
			fichiers[i-n].setLocation(new Point(0,j));
			fichiers[i-n].setSize(100, 17);
			fichiers[i-n].setText("fichier "+i);
			
			bAFA[i-n] = new JButton("Admin "+i);
			bAFA[i-n].setBounds(260,j,100,20);
			
			bAFA[i-n].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JLabel membres = new JLabel();
					membres.setLayout(null);
					membres.setLocation(new Point(400,20));
					membres.setSize(300, 17);
					membres.setText("Membres du fichier");
					cp.add(membres);
					repaint();
				}
			});
			cp.add(bAFA[i-n]);
			bAF[i-n] = new JButton("Editer "+i);
			bAF[i-n].setBounds(110, j, 150, 20);

			bAF[i-n].addActionListener(new ActionListener() {
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
			
			cp.add(fichiers[i-n]);
			cp.add(bAF[i-n]);
			
		}
		preced = new JButton("Precedant");
		preced.setBounds(10, j+40, 100, 20);
		preced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				n -= 20;
				if(n < 0) {
					n = 0;
				}
				for(int i = 0;i<20;i++) {
				  remove(bAF[i]);
				  remove(fichiers[i]);
				}
				affiche_fichiers();
				repaint();
			}
		});
		cp.add(preced);
		
		suivant= new JButton("Suivant");
		suivant.setBounds(110, j+40, 100, 20);
		suivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				n += 20;
				for(int i = 0;i<20;i++) {
					  remove(bAF[i]);
					  remove(fichiers[i]);
				}
				affiche_fichiers();
				repaint();
			}
		});
		cp.add(suivant);
	}

	
	
}
