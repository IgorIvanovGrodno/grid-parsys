package com.grid.parsys.core.services;

import org.apache.sling.api.resource.Resource;

public interface ContentManager {
    void clear(Resource resource, Integer rows, Integer columns);
}
