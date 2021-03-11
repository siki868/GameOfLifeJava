package Controller;

import view.GameOfLifePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/*
    Sets all cells to be alive while dragging
 */
public class MouseDragCell extends MouseMotionAdapter {
    private final GameOfLifePanel gameOfLifePanel;

    public MouseDragCell(GameOfLifePanel gameOfLifePanel) {
        this.gameOfLifePanel = gameOfLifePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(p, gameOfLifePanel);
        gameOfLifePanel.setCellAlive(p.x, p.y);
    }
}
