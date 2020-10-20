package com.grid.parsys.core.models;

import com.grid.parsys.core.services.ContentManager;
import org.apache.sling.api.resource.*;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class)
public class MultiColumnControl {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    //inject dialog values
    @Inject
    @Optional
    public Integer columns;


    //inject dialog values
    @Inject @Optional
    public Integer rows;

    @Inject
    @Self
    public Resource res;

    @Inject
    public ContentManager contentManager;


    private List<Row> rowsList;

    @PostConstruct
    protected void init() {
        LOG.info("Column Control  **** INIT@@@@@ ***"+ res.toString());
        rowsList=new ArrayList<>();
        LOG.info("Column Control  **** Check1 ***");

        if(columns != null || rows != null){

            Row row;
            for (int i=0; i<rows; i++) {
                row=new Row();
                for (int j = 0; j < columns; j++) {
                    Column item = new Column();
                    item.setCount(j);
                    item.setRowCount(i);
                    row.getColumnList().add(item);
                }
                rowsList.add(row);
            }
        }

        contentManager.clear(res, rows, columns);

    }

    public List<Row> getRowsList() {
        return rowsList;
    }

    public void setRowsList(List<Row> rowsList) {
        this.rowsList = rowsList;
    }

}
