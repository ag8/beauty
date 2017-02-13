package org.ag.ants_utils;

public class IncrementalCell implements Cell {
    private int value;

    public IncrementalCell() {
        this.value = 0;
    }

    public IncrementalCell(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void increment() {
        this.value++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncrementalCell that = (IncrementalCell) o;

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