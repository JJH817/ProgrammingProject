package seatUITest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelClass extends JFrame {
    private MyPanel panel = new MyPanel();
    private JLabel selectionLabel = new JLabel("Selected Grade: None, Panel Number: None");
    private int panelsToSelect = 0;
    private int remainingPanels;
    private String selectedGrade = null;
    private int[] selectedPanelNumbers = new int[2]; // Store panel numbers for the selected panels
    private int selectedPanelsCount = 0; // Counter for the selected panels
    private Color[][] originalColors;

    public PanelClass() {
        setTitle("JPanel ex");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Prompt the user to choose the number of panels to select (1 or 2)
        while (true) {
            String input = JOptionPane.showInputDialog(this, "Enter the number of panels to select (1 or 2):");
            try {
                panelsToSelect = Integer.parseInt(input);
                if (panelsToSelect >= 1 && panelsToSelect <= 2) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter 1 or 2.");
            }
        }

        remainingPanels = panelsToSelect;
        originalColors = new Color[9][10];

        setLayout(new BorderLayout());
        add(selectionLabel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(1000, 1000));
        pack();
        setVisible(true);
    }

    class MyPanel extends JPanel {
        private Color[][] panelColors;

        public MyPanel() {
            panelColors = new Color[9][10]; // 9 rows (3 for each grade), 10 columns
            initializePanelColors();
            addMouseListener(new MyMouseAdapter());
        }

        private void initializePanelColors() {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 10; j++) {
                    panelColors[i][j] = new Color(255, 0, 0); // Initialize all panels to red
                }
            }
            // Save the original colors
            originalColors = new Color[9][10];
            for (int i = 0; i < 9; i++) {
                System.arraycopy(panelColors[i], 0, originalColors[i], 0, 10);
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(0, 0, 0));

            // Draw panels with different grades in separate rows
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 10; j++) {
                    g.setColor(panelColors[i][j]);
                    g.fillRect(110 + 80 * j, 160 + 80 * i, 60, 60);
                }
            }
        }

        private class MyMouseAdapter extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (remainingPanels > 0) {
                    int column = (e.getX() - 110) / 80;
                    int row = (e.getY() - 160) / 80;

                    if (row >= 0 && row < 9 && column >= 0 && column < 10) {
                        // Toggle the color of the clicked panel
                        if (panelColors[row][column].equals(new Color(255, 0, 0))) {
                            panelColors[row][column] = new Color(0, 255, 0); // Change to green when clicked
                        } else {
                            panelColors[row][column] = new Color(255, 0, 0); // Change back to red when clicked again
                        }

                        // Check if the grade is selected or select the grade
                        if (selectedGrade == null) {
                            selectedGrade = assignGrade(row, column);
                            selectedPanelNumbers[selectedPanelsCount] = row * 10 + column + 1;
                            selectedPanelsCount++;
                        } else {
                            String grade = assignGrade(row, column);
                            if (!selectedGrade.equals(grade)) {
                                JOptionPane.showMessageDialog(null, "Please select panels with the same grade.");
                                // Revert the color change for the mistakenly selected panel
                                panelColors[row][column] = toggleColor(panelColors[row][column]);
                                return; // Do not decrease remainingPanels or continue if grades are different
                            }
                            selectedPanelNumbers[selectedPanelsCount] = row * 10 + column + 1;
                            selectedPanelsCount++;
                        }

                        updateSelectionLabel();
                        repaint(); // Trigger repaint to reflect the color change
                        remainingPanels--;

                        if (remainingPanels == 0) {
                            JOptionPane.showMessageDialog(null, "All panels selected. You can't click anymore.");
                        }
                    }
                }
            }

            private void updateSelectionLabel() {
                if (selectedPanelsCount == panelsToSelect) {
                    selectionLabel.setText("Selected Grade: " + selectedGrade +
                            ", Panel Numbers: " + selectedPanelNumbers[0] + ", " + selectedPanelNumbers[1]);
                } else {
                    selectionLabel.setText("Selected Grade: " + selectedGrade +
                            ", Panel Number: " + selectedPanelNumbers[0]);
                }

                // Schedule the message dialog to ensure it appears after the label is updated
                SwingUtilities.invokeLater(() -> {
                    if (remainingPanels == 0) {
                        JOptionPane.showMessageDialog(null, "All panels selected. You can't click anymore.");
                        JOptionPane.showMessageDialog(null, selectionLabel);
                    }
                });

                repaint(); // Trigger repaint to immediately update the GUI
            }



            private String assignGrade(int row, int column) {
                // Assign grades based on the panel's position
                int panelNumber = row * 10 + column + 1;
                if (panelNumber <= 30) {
                    return "A";
                } else if (panelNumber <= 60) {
                    return "B";
                } else {
                    return "C";
                }
            }

            private Color toggleColor(Color color) {
                // Toggle the color
                return color.equals(new Color(255, 0, 0)) ? new Color(0, 255, 0) : new Color(255, 0, 0);
            }
        }
    }

    public static void main(String[] args) {
        new PanelClass();
    }
}



