package clientlourd;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Panneau extends JPanel {
	private JTextArea zone_text;
	private JScrollPane scroll_text;
	public Panneau() {
		zone_text = new JTextArea("Francis est pas très intéligent!!!",40,50);
		scroll_text = new JScrollPane(zone_text);
		add(scroll_text);
		repaint();
	}
}
