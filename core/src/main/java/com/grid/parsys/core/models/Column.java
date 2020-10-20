package com.grid.parsys.core.models;

public class Column {
    private int count;
    private int rowCount;

    /**
     * Get the count of column
     *
     * @return <int>count
     */
    public int getCount() {
        return count;
    }
    /**
     * Constructor will set the count of column.
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
}
