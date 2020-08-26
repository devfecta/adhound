package com.adhound.persistence;

import com.adhound.entity.LocationContact;
import com.adhound.service.CrudService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Location Contact data.
 *
 * @author kkelm
 */
public class LocationContactData {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The CRUD.
     */
    public CrudService crud;

    /**
     * Instantiates a new CRUD service to access the CRUD methods with this class.
     */
    public LocationContactData() {
        this.crud = new CrudService(LocationContact.class);
    }

}
