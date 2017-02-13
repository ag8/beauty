package org.ag.ants;

import org.ag.ants_utils.Direction;

import java.util.ArrayList;
import java.util.List;

import static org.ag.ants_utils.Variables.DIM;

public class BAnt {
    private int x;
    private int y;
    private Direction dir;
    private int flavor;

    public BAnt(int x, int y, int flavor) {
        this.x = x;
        this.y = y;
        this.dir = new Direction(0);
        this.flavor = flavor;
    }

    BAnt(int x, int y, int dir, int flavor) {
        this.x = x;
        this.y = y;
        this.dir = new Direction(dir);
        this.flavor = flavor;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    List<int[]> move(int state) {
        if (moveRule(state)) {
            this.dir.right();
        } else {
            this.dir.left();
        }

        List<int[]> changed = new ArrayList<>();
        if (this.flavor == 1) {
            changed.add(new int[]{bound(this.x), bound(this.y)});
            changed.add(new int[]{bound(this.x + 1), bound(this.y)});
            changed.add(new int[]{bound(this.x), bound(this.y + 1)});
            changed.add(new int[]{bound(this.x - 1), bound(this.y)});
            changed.add(new int[]{bound(this.x), bound(this.y - 1)});
        } else if (this.flavor == 2) {
            changed.add(new int[]{bound(this.x), bound(this.y)});
            changed.add(new int[]{bound(this.x + 1), bound(this.y)});
            changed.add(new int[]{bound(this.x + 1), bound(this.y + 1)});
            changed.add(new int[]{bound(this.x), bound(this.y + 1)});
            changed.add(new int[]{bound(this.x - 1), bound(this.y + 1)});
            changed.add(new int[]{bound(this.x - 1), bound(this.y)});
            changed.add(new int[]{bound(this.x - 1), bound(this.y - 1)});
            changed.add(new int[]{bound(this.x), bound(this.y - 1)});
            changed.add(new int[]{bound(this.x + 1), bound(this.y - 1)});
        }

        moveForward();

        return changed;
    }

    private boolean moveRule(int state) {
        return state % 2 == 0;
    }

    public Direction getDirection() {
        return dir;
    }

    /**
     * Torus!
     */
    private void moveForward() {
        if (this.dir.getDirection() == 0) {
            this.x--;

            if (this.x < 0) {
                this.x = DIM - 1;
            }
        }
        if (this.dir.getDirection() == 1) {
            this.y--;

            if (this.y < 0) {
                this.y = DIM - 1;
            }
        }
        if (this.dir.getDirection() == 2) {
            this.x++;

            if (this.x > DIM - 1) {
                this.x = 0;
            }
        }
        if (this.dir.getDirection() == 3) {
            this.y++;

            if (this.y > DIM - 1) {
                this.y = 0;
            }
        }
    }

    private int bound(int n) {
        n = n % DIM;
        while (n < 0) {
            n += n;
        }
        return n;
    }
}
