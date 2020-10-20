package com.grid.parsys.core.services;

import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(service=ContentManager.class)
public class ContentManagerImpl implements ContentManager {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private String pathGridParsys;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public void clear(Resource res, Integer rows, Integer columns) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(ResourceResolverFactory.SUBSERVICE, "parsys");
        LOG.info("ContentManagerImpl  **** Check2 ***");
        ResourceResolver resolver = null;
        Integer oldColumnsCount;
        Integer oldRowsCount;

        try {
            resolver = resolverFactory.getServiceResourceResolver(param);
            pathGridParsys = res.getPath();
            Resource resource = resolver.getResource(pathGridParsys);
            ValueMap properties = res.getValueMap();
            oldColumnsCount = properties.get("oldColumns", Integer.class);
            oldRowsCount = properties.get("oldRows", Integer.class);

            if(oldColumnsCount!=null
                    &&oldRowsCount!=null
                    &&oldColumnsCount!=0
                    &&oldRowsCount!=0){

               clearColumnsResources(columns, oldColumnsCount, oldRowsCount, resolver);
               clearRowsResources(rows,oldRowsCount, oldColumnsCount, resolver);

            }

            setOldColumnsAndRowsCount(resolver, resource, rows, columns);
            resolver.close();

        } catch (LoginException e) {
            LOG.info("Error"+e.getMessage());
            e.printStackTrace();
        }
    }

    private void setOldColumnsAndRowsCount(ResourceResolver resolver, Resource resource, Integer rows, Integer columns){
        if (resource != null) {
            try {
                ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);
                if(properties!=null) {
                    int columnsInt = columns!=null?columns:0;
                    int rowsInt = rows!=null?rows:0;
                    properties.put("oldColumns", columnsInt);
                    properties.put("oldRows", rowsInt);
                    resolver.commit();
                }
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
        }
    }

    private  void deleteResource(ResourceResolver resourceResolver, String resourcePath){
        try {
            Resource resourceForDeleting = resourceResolver.getResource(resourcePath);
            if(resourceForDeleting!=null){
                resourceResolver.delete(resourceForDeleting);
                resourceResolver.commit();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    private void clearColumnsResources(Integer columns, Integer oldColumnsCount, Integer oldRowsCount, ResourceResolver resolver){
        for(int i = columns; oldColumnsCount>i; i++){
            for(int j=0; oldRowsCount>j;j++) {
                deleteResource(resolver, pathGridParsys + "/par_" + i + j);
            }
        }
    }

    private void clearRowsResources(Integer rows, Integer oldRowsCount, Integer oldColumnsCount, ResourceResolver resolver){
        for(int i=rows; oldRowsCount>i; i++) {
            for (int j=0; oldColumnsCount>j;j++) {
                deleteResource(resolver, pathGridParsys + "/par_" + j + i);
            }
        }
    }
}
