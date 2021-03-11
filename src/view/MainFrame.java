package view;

import Controller.MouseClickCell;
import Controller.MouseDragCell;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final static MainFrame INSTANCE = new MainFrame();

    private MainFrame() {
        super("GoL");
        try {
            initComponents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MainFrame getInstance() {
        return INSTANCE;
    }

    private void initComponents() throws Exception {
        JButton allRandomButton = new JButton("All random");
        JButton resetButton = new JButton("Reset");
        JButton gliderButton = new JButton("Spawn glider");

        JPanel jPanel = new JPanel();
        // Change the size and scale here
        GameOfLifePanel gPanel = new GameOfLifePanel(800, 800, 8);

        gPanel.setBackground(Color.GRAY);
        gPanel.repaint();
        gPanel.addMouseListener(new MouseClickCell(gPanel));
        gPanel.addMouseMotionListener(new MouseDragCell(gPanel));

        jPanel.setLayout(new GridLayout(1, 3));
        jPanel.add(allRandomButton);
        jPanel.add(resetButton);
        jPanel.add(gliderButton);

        allRandomButton.addActionListener(e -> {
            gPanel.initRandomGrid();
        });

        resetButton.addActionListener(e -> {
            gPanel.resetGrid();
        });

        gliderButton.addActionListener(e -> {
            gPanel.initGliderSpawner();
        });

        add(gPanel);
        add(jPanel, BorderLayout.SOUTH);
        pack();
        setLocationByPlatform(true);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // update interval
        new Timer(20, e -> {
            gPanel.updateGame();
            gPanel.repaint();
        }).start();
    }
}
