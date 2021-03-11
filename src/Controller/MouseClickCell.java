package Controller;

import view.GameOfLifePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
    Sets the cell to be alive on click
 */
public class MouseClickCell extends MouseAdapter {

    private final GameOfLifePanel gameOfLifePanel;

    public MouseClickCell(GameOfLifePanel gameOfLifePanel) {
        this.gameOfLifePanel = gameOfLifePanel;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(p, gameOfLifePanel);
        gameOfLifePanel.setCellAlive(p.x, p.y);
    }
}
