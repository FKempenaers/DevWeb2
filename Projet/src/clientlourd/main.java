package clientlourd;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * La classe principale du clientlourd qui cree la fenetre d'administration
 */
public class main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					String laf = UIManager.getSystemLookAndFeelClassName();
					UIManager.setLookAndFeel(laf);
				} catch (Exception e) {
					/* never happens */ }
				Administration admin = new Administration();
				admin.setSize(1024, 760);
				admin.setVisible(true);
				admin.requestFocus();
			}
		});

	}
}
