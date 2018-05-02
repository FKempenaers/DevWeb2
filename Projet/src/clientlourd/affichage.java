package clientlourd;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**La fenetre du jeu, utilise les variable de IConfig, ajoute le Panneau de Jeu et rend la fenetre visible */
public class affichage{
	private static JTextArea zone_text;
	private static final long serialVersionUID = 6876475270346699947L;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLocation(POSITION_X, POSITION_Y);
		frame.setPreferredSize(new Dimension(1024, 640));

		JPanel pan = new JPanel();
		//pan.setLayout(null);
		pan.setBackground(Color.GRAY);

		Panneau panneau = new Panneau();
		frame.getContentPane().add(panneau);
		frame.pack();
		frame.setVisible(true);
	}

}