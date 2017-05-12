package org.ag.koelner;

import org.ag.ants_display.GridDisplayPane;
import org.ag.ants_utils.TripleCell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.ag.ants_utils.Variables.DIM;

class RunAnt {
    TripleCell[][] grid;
    private List<Ant> ants;

    private static final boolean DISPLAY = true;
    private static final int STEPS = 10000;
    private static final long DELAY = 10;

    void run() throws InterruptedException {
        grid = new TripleCell[DIM][DIM];
        ants = new ArrayList<>();

        ants.add(new Ant(DIM / 2, DIM / 2, 0, 2)); // Doesn't matter where since it's a torus
        ants.add(new Ant(DIM / 2, DIM / 2, 1, 2)); // Doesn't matter where since it's a torus
        ants.add(new Ant(DIM / 2, DIM / 2, 2, 2)); // Doesn't matter where since it's a torus
        ants.add(new Ant(DIM / 2, DIM / 2, 3, 2)); // Doesn't matter where since it's a torus


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
                grid[i][j] = new TripleCell(2);
            }
        }

        for (int step = 0; step < STEPS; step++) {
            for (Ant ant : ants) {
                TripleCell current = grid[ant.getX()][ant.getY()];
                List<int[]> listToChange = ant.move(current.getState());
//                System.out.println("At " + ant.getX() + ", " + ant.getY() + ".");
                for (int[] toChange : listToChange) {
//                    System.out.println("\tChanging " + toChange[0] + ", " + toChange[1] + ".");
                    grid[toChange[0]][toChange[1]].increment();
                }

                if (DISPLAY) {
                    Thread.sleep(DELAY);
                    frame.setTitle("Space colonization! Step " + step + "/" + STEPS + " (Dir = " + ants.get(0).getDirection() + ")");
                    g.draw(grid, step);
                }
            }
        }
    }
}
