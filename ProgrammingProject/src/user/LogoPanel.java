package user;

import javax.swing.*;
import java.awt.*;

class LogoPanel extends JPanel {
	
	private ImageIcon icon = new ImageIcon("resources/logo.png");
	private Image img = icon.getImage();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(img, 0, 0, d.width, d.height, null);
	}
}
