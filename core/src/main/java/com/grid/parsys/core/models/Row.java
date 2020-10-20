package com.grid.parsys.core.models;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Column> columnList=new ArrayList<>();

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

}
