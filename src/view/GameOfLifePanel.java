package view;

import javax.swing.*;
import java.awt.*;
import java.beans.Transient;
import java.util.Arrays;
import java.util.Random;

public class GameOfLifePanel extends JPanel {

    private int[][] grid;
    private static final Random rnd = new Random();
    private final int N;

    public GameOfLifePanel(int width, int height, int n) throws Exception {
        if (n % 2 != 0) throw new Exception("Has to be an even number");
        this.N = n;
        this.grid = new int[width / N][height / N];
//        initGliderSpawner();
    }

    // Ded
    public void resetGrid() {
        Arrays.stream(grid).forEach(ints -> Arrays.fill(ints, 0));
    }

    public void initRandomGrid() {
        for (int[] row : grid) {
            for (int i = 0; i < row.length; i++) {
                if (rnd.nextDouble() > 0.85) row[i] = 1;
                else row[i] = 0;
            }
        }
    }

    public void initGliderSpawner() {
        int offSet = 20;
        int s = grid.length / 2 - offSet;
        int d = grid[0].length / 2 - offSet;
        grid[s][d + 2] = 1;
        grid[s][d + 3] = 1;
        grid[s + 1][d + 2] = 1;
        grid[s + 1][d + 3] = 1;
        grid[s + 8][d + 3] = 1;
        grid[s + 8][d + 4] = 1;
        grid[s + 9][d + 2] = 1;
        grid[s + 9][d + 4] = 1;
        grid[s + 10][d + 2] = 1;
        grid[s + 10][d + 3] = 1;
        grid[s + 16][d + 4] = 1;
        grid[s + 16][d + 5] = 1;
        grid[s + 16][d + 6] = 1;
        grid[s + 17][d + 4] = 1;
        grid[s + 18][d + 5] = 1;
        grid[s + 22][d + 1] = 1;
        grid[s + 22][d + 2] = 1;
        grid[s + 23][d] = 1;
        grid[s + 23][d + 2] = 1;
        grid[s + 24][d] = 1;
        grid[s + 24][d + 1] = 1;
        grid[s + 24][d + 12] = 1;
        grid[s + 24][d + 13] = 1;
        grid[s + 25][d + 12] = 1;
        grid[s + 25][d + 14] = 1;
        grid[s + 26][d + 12] = 1;
        grid[s + 34][d] = 1;
        grid[s + 34][d + 1] = 1;
        grid[s + 35][d] = 1;
        grid[s + 35][d + 1] = 1;
        grid[s + 35][d + 7] = 1;
        grid[s + 35][d + 8] = 1;
        grid[s + 35][d + 9] = 1;
        grid[s + 36][d + 7] = 1;
        grid[s + 37][d + 8] = 1;
    }

    public void updateGame() {
        // Reads from grid, writes to gridCpy
        int[][] gridCpy = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int state = grid[i][j];
                int numOfNbs = getAliveNeighbourCount(i, j, grid);
                if (state == 0 && numOfNbs == 3) gridCpy[i][j] = 1;
                else if (state == 1 && (numOfNbs < 2 || numOfNbs > 3)) gridCpy[i][j] = 0;
                else gridCpy[i][j] = state;
            }
        }

        grid = gridCpy.clone();
    }

    private int getAliveNeighbourCount(int row, int col, int[][] arr) {
        int sum = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((i + row == row) && (j + col) == col) continue;
                if ((i + row) < 0 || (i + row >= grid.length)) continue;
                if ((j + col) < 0 || (j + col >= grid[0].length)) continue;
                sum += arr[i + row][j + col];
            }
        }
        return sum;
    }

    @Override
    @Transient
    public Dimension getPreferredSize() {
        return new Dimension(grid.length * N, grid[0].length * N);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int nbr = getAliveNeighbourCount(i, j, grid);
                    switch (nbr) {
                        case 2:
                            g.setColor(new Color(102, 0, 153));
                            break;
                        case 3:
                            g.setColor(new Color(204, 0, 0));
                            break;
                        case 4:
                            g.setColor(new Color(0, 200, 0));
                            break;
                        case 5:
                            g.setColor(new Color(200, 200, 0));
                            break;
                        default:
                            g.setColor(new Color(0, 0, 0));
//                            g.setColor(Color.getHSBColor(rnd.nextFloat(),
//                                    new Random().nextFloat(), new Random().nextFloat()));
                    }
//                    g.fillRect(j * N, i * N, N, N);
                    g.fillOval(j * N, i * N, N, N);
                }
            }
        }
    }

    private boolean checkBounds(int x, int y) {
        return (y / N >= 0) && (x / N >= 0) && (y / N < grid.length) && (x / N < grid[0].length);
    }

    public void setCellAlive(int x, int y) {
        if (checkBounds(x, y)) {
            grid[y / N][x / N] = 1;
            this.repaint();
        }
    }
}
