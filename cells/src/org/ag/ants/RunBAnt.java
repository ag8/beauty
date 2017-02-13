package org.ag.ants;

import org.ag.ants_display.GridDisplayPane;
import org.ag.ants_utils.BWCell;
import org.ag.ants_utils.IncrementalCell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.ag.ants_utils.Variables.DIM;

class RunBAnt {
    IncrementalCell[][] grid;
    private List<BAnt> ants;

    private static final boolean DISPLAY = true;
    private static final int STEPS = 1000000;
    private static final long DELAY = 1;

    void run() throws InterruptedException {
        grid = new IncrementalCell[DIM][DIM];
        ants = new ArrayList<>();

        ants.add(new BAnt(DIM / 2, DIM / 2, 0, 2)); // Doesn't matter where since it's a torus


        JFrame frame = new JFrame("Langton's Ants!");
        GridDisplayPane g = new GridDisplayPane(grid);
        if (DISPLAY) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(g);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }


        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                grid[i][j] = new IncrementalCell();
            }
        }

        for (int step = 0; step < STEPS; step++) {
            for (BAnt ant : ants) {
                IncrementalCell current = grid[ant.getX()][ant.getY()];
                List<int[]> listToChange = ant.move(current.getState());
                for (int[] toChange : listToChange) {
                    grid[toChange[0]][toChange[1]].increment();
                }

                if (DISPLAY) {
                    Thread.sleep(DELAY);
                    frame.setTitle("Langton's Ants! Step " + step + "/" + STEPS + " (Dir = " + ants.get(0).getDirection() + ")");
                    g.draw(grid);
                }
            }
        }
    }
}
