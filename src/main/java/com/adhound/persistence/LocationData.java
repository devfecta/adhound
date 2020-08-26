package com.adhound.persistence;

import com.adhound.entity.Location;
import com.adhound.service.CrudService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Location data.
 *
 * @author kkelm
 */
public class LocationData {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The CRUD.
     */
    public CrudService crud;

    /**
     * Instantiates a new CRUD service to access the CRUD methods with this class.
     */
    public LocationData() {
        this.crud = new CrudService(Location.class);
    }

}
