package org.ag.ants_utils;

public class BWCell implements Cell {
    private boolean state;

    public BWCell() {
        this.state = false;
    }

    public BWCell(boolean state) {
        this.state = state;
    }

    public int getState() {
        return this.state ? 1 : 0;
    }

    public boolean isFalse() {
        return !this.state;
    }

    public void increment() {
        state = !state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BWCell that = (BWCell) o;

        return state == that.state;

    }

    @Override
    public int hashCode() {
        return (state ? 1 : 0);
    }

    public String toString() {
        return "" + state + "";
    }
}