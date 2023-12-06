package seatUITest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintP extends JFrame {
    private MyPanel panel = new MyPanel();

    public PaintP() {
        setTitle("JPanel ex");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(1000, 1000);
        setVisible(true);
    }

    class MyPanel extends JPanel {
        private Color[][] panelColors;

        public MyPanel() {
            panelColors = new Color[3][10]; // 3 rows, 10 columns
            initializePanelColors();
            addMouseListener(new MyMouseAdapter());
        }

        private void initializePanelColors() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 10; j++) {
                    panelColors[i][j] = new Color(255, 0, 0); // Initialize all panels to red
                }
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(0, 0, 0));
            g.drawRect(100, 20, 800, 100);
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 10; i++) {
                    g.setColor(panelColors[j][i]);
                    g.fillRect(110 + 80 * i, 160 + 80 * j, 60, 60);
                }
            }
        }

        private class MyMouseAdapter extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = (e.getX() - 110) / 80;
                int row = (e.getY() - 160) / 80;

                if (row >= 0 && row < 3 && column >= 0 && column < 10) {
                    // Toggle the color of the clicked panel
                    if (panelColors[row][column].equals(new Color(255, 0, 0))) {
                        panelColors[row][column] = new Color(0, 255, 0); // Change to green when clicked
                    } else {
                        panelColors[row][column] = new Color(255, 0, 0); // Change back to red when clicked again
                    }
                    repaint(); // Trigger repaint to reflect the color change
                }
            }
        }
    }

    public static void main(String[] args) {
        new PaintP();
    }
}
