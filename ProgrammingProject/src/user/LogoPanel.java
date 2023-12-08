package user;

import javax.swing.*;
import java.awt.*;

class LogoPanel extends JPanel {
	
	private ImageIcon icon = new ImageIcon("그림 경로");
	private Image img = icon.getImage();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(img, 0, 0, d.width, d.height, null);
	}
}
