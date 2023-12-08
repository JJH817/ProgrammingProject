package seatUITest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class PanelName extends JFrame {
    private MyPanel panel = new MyPanel();
    private JLabel selectionLabel = new JLabel("Selected Grade: None, Panel Number: None");
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String[]> selectedPanels = new ArrayList<>();
    private int panelsToSelect = 0;
    private int remainingPanels;
    private String selectedGrade = null;
    private int[] selectedPanelNumbers = new int[2];
    private int selectedPanelsCount = 0;

    public PanelName() {
        setTitle("JPanel ex");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the input dialog for name and panel count
        enterNameAndPanelCount();

        remainingPanels = panelsToSelect;

        setLayout(new BorderLayout());
        add(selectionLabel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(1000, 1000));
        pack();
        setVisible(true);
    }

    private void enterNameAndPanelCount() {
        JTextField nameField = new JTextField();
        JTextField panelCountField = new JTextField();
        Object[] message = {
                "Name:", nameField,
                "Number of Panels to Select (1 or 2):", panelCountField
        };

        while (true) {
            int option = JOptionPane.showConfirmDialog(this, message, "Enter Name and Panel Count",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                String name = nameField.getText().trim();
                try {
                    panelsToSelect = Integer.parseInt(panelCountField.getText().trim());

                    if (panelsToSelect >= 1 && panelsToSelect <= 2 && !name.isEmpty()) {
                        names.add(name);
                        selectedPanels.add(getSelectedPanelsInfo());
                        resetSelection();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please enter a name and select 1 or 2 panels.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number for panel count.");
                }
            } else {
                System.exit(0); // Exit the program if cancel is clicked
            }
        }
    }

    private String[] getSelectedPanelsInfo() {
        String[] info = new String[selectedPanelsCount];
        for (int i = 0; i < selectedPanelsCount; i++) {
            info[i] = selectedGrade + "-" + selectedPanelNumbers[i];
        }
        return info;
    }

    private void resetSelection() {
        remainingPanels = panelsToSelect;
        selectedGrade = null;
        selectedPanelsCount = 0;
        Arrays.fill(selectedPanelNumbers, 0);
        updateSelectionLabel();
        panel.resetPanelColors();
    }

    private void updateSelectionLabel() {
        if (selectedPanelsCount == panelsToSelect) {
            selectionLabel.setText("Selected Grade: " + selectedGrade +
                    ", Panel Numbers: " + Arrays.toString(selectedPanelNumbers));
        } else {
            selectionLabel.setText("Selected Grade: " + selectedGrade +
                    ", Panel Number: " + selectedPanelNumbers[0]);
        }
    }

    class MyPanel extends JPanel {
        private Color[][] panelColors;
        private Color[][] originalColors;

        public MyPanel() {
            panelColors = new Color[9][10];
            initializePanelColors();
            addMouseListener(new MyMouseAdapter());
        }

        public void resetPanelColors() {
            for (int i = 0; i < 9; i++) {
                System.arraycopy(originalColors[i], 0, panelColors[i], 0, 10);
            }
            repaint();
        }

        private Color assignGradeColor(int row, int column) {
            int panelNumber = row * 10 + column + 1;
            if (panelNumber <= 30) {
                return new Color(255, 127, 50);
            } else if (panelNumber <= 60) {
                return new Color(0, 181, 226);
            } else {
                return new Color(255, 205, 0);
            }
        }

        private void initializePanelColors() {
            originalColors = new Color[9][10];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 10; j++) {
                    panelColors[i][j] = assignGradeColor(i, j);
                    originalColors[i][j] = panelColors[i][j];
                }
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(0, 0, 0));

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
                        if (panelColors[row][column].equals(assignGradeColor(row, column))) {
                            panelColors[row][column] = Color.red;
                        } else {
                            panelColors[row][column] = assignGradeColor(row, column);
                        }

                        if (selectedGrade == null) {
                            selectedGrade = assignGrade(row, column);
                            selectedPanelNumbers[selectedPanelsCount] = row * 10 + column + 1;
                            selectedPanelsCount++;
                        } else {
                            String grade = assignGrade(row, column);
                            if (!selectedGrade.equals(grade)) {
                                JOptionPane.showMessageDialog(null, "Please select panels with the same grade.");
                                panelColors[row][column] = assignGradeColor(row, column);
                                return;
                            }
                            selectedPanelNumbers[selectedPanelsCount] = row * 10 + column + 1;
                            selectedPanelsCount++;
                        }

                        updateSelectionLabel();
                        repaint();
                        remainingPanels--;

                        if (remainingPanels == 0) {
                            SwingUtilities.invokeLater(() -> {
                                JOptionPane.showMessageDialog(null, selectionLabel);
                                enterNameAndPanelCount();
                            });
                        }
                    }
                }
            }

            private String assignGrade(int row, int column) {
                int panelNumber = row * 10 + column + 1;
                if (panelNumber <= 30) {
                    return "A";
                } else if (panelNumber <= 60) {
                    return "B";
                } else {
                    return "C";
                }
            }
        }
    }

    public static void main(String[] args) {
        new PanelName();
    }
}
