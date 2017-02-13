package org.ag.ants_display;

import org.ag.ants.BAnt;
import org.ag.ants_utils.Cell;
import org.ag.ants_utils.TripleCell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.ag.ants_utils.Variables.DIM;

public class GridDisplayPane extends JPanel {
    private static final boolean SAVE_TO_IMAGES = true;
    private Cell[][] grid;
    private List<Rectangle> cells;

    public GridDisplayPane(Cell[][] grid) {
        this.grid = grid.clone();
        cells = new ArrayList<>(DIM * DIM);
    }

    public void draw(Cell[][] grid, int step) {
        this.grid = grid.clone();
        repaint();

        if (SAVE_TO_IMAGES) {
            System.out.println("Saving to image.");
            BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
            Graphics m = bi.createGraphics();
            this.paint(m);  //this == JComponent
            m.dispose();
            try {
                ImageIO.write(bi, "png", new File("run_images/step" + step + ".png"));
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 1000);
    }

    @Override
    public void invalidate() {
        cells.clear();
        super.invalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / DIM;
        int cellHeight = height / DIM;

        int xOffset = (width - (DIM * cellWidth)) / 2;
        int yOffset = (height - (DIM * cellHeight)) / 2;

        if (cells.isEmpty()) {
            for (int row = 0; row < DIM; row++) {
                for (int col = 0; col < DIM; col++) {
                    Rectangle cell = new Rectangle(
                            xOffset + (col * cellWidth),
                            yOffset + (row * cellHeight),
                            cellWidth,
                            cellHeight);
                    cells.add(cell);
                }
            }
        }

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                int index = i + (j * DIM);
                Rectangle cell = cells.get(index);
                g2d.setStroke(new BasicStroke(0));
                if (grid[i][j] instanceof BAnt) {
                    g2d.setColor(grid[i][j].getState() == 1 ? Color.WHITE : Color.BLACK);
                } else if (grid[i][j] instanceof TripleCell) {
                    g2d.setColor(getColor(grid[i][j].getState()));
                }
                g2d.fill(cell);
            }
        }

        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }

        g2d.dispose();
    }

    private Color getColor(int state) {
//        return new Color(state * 85, state * 85, state * 85);
        if (state == 0) {
            return Color.BLACK;
        } else if (state == 1) {
            return Color.GRAY;
        } else if (state == 2) {
            return Color.WHITE;
        }

        return Color.BLACK;
    }

}