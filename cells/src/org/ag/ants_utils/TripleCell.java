package org.ag.ants_utils;

public class TripleCell implements Cell {
    private int value;

    public TripleCell() {
        this.value = 0;
    }

    public TripleCell(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void increment() {
        this.value++;
        if (this.value > 2) {
            this.value = 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TripleCell that = (TripleCell) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    public String toString() {
        return "" + value + "";
    }

    @Override
    public int getState() {
        return this.value;
    }
}