package com.adhound.persistence;

import com.adhound.entity.Location;
import com.adhound.entity.LocationContact;
import com.adhound.entity.User;
import com.adhound.api.Locations;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.subscriptions.Plan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Location data test.
 */
class LocationDataTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The User data.
     */
    UserData userData;
    /**
     * The Location data.
     */
    LocationData locationData;
    /**
     * The Location contact data.
     */
    LocationContactData locationContactData;
    /**
     * The Location.
     */
    Location location;
    /**
     * The Mapper.
     */
    ObjectMapper mapper;

    /**
     * Sets up the tests with a new objects.
     */
    @BeforeEach
    void setUp() {
        userData = new UserData();
        location = new Location();
        locationData = new LocationData();
        locationContactData = new LocationContactData();

        mapper = new ObjectMapper();
    }

    /**
     * Test get all locations data.
     */
    @Test
    void testGetAllLocations() {
        logger.info("Start Location getAll()");

        List<Location> locations = locationData.crud.getAll();
        int currentSize = locations.size();
        assertEquals(currentSize, locations.size());
        logger.info("Got all users");
        logger.info("End Location getAll()");

    }

    /**
     * Test get one location data success.
     */
    @Test
    void testGetByIdLocation() {
        logger.info("Start Location getById()");
        List<User> users = userData.crud.getAll();
        Iterator userList = users.iterator();

        User getUser = (User) userData.crud.getById(1);

        Set<Location> locations = location.getLocations(getUser);

        Iterator locationIterator = locations.iterator();

        Location location = (Location) locationData.crud.getById(293);

        assertTrue(locations.contains(location));
        logger.info("End Location getById()");

    }

    /**
     * Test the creation of a new location record.
     */
    @Test
    void testInsertLocation() {
        logger.info("Start Location Insert");
        User user = (User) userData.crud.getById(2);
        String newLocationName = "Test Location " + Math.round(Math.random()*100);
        Location newObject = new Location(user, newLocationName, "(123) 456-7890", "(987) 654-3210", "123 Test Street", "Madison", 33, "12345", 1);
        //user.getLocations().add(newObject);
        //newObject.setUser(user);
        int newId = (int) locationData.crud.insertRecord(newObject);
        logger.info("Inserted record with the ID: " + newId);

        newLocationName = "Test Location " + Math.round(Math.random()*100);
        newObject = new Location(user, newLocationName, "(123) 456-7890", "(987) 654-3210", "123 Test Street", "Madison", 33, "12345", 1);
        //user.getLocations().add(newObject);
        //newObject.setUser(user);
        newId = (int) locationData.crud.insertRecord(newObject);
        logger.info("Inserted record with the ID: " + newId);

        logger.info("End Location Insert");
        Location getRecord = (Location) locationData.crud.getById(newId);

        assertEquals(getRecord, newObject);
    }

    /**
     * Test an update of a user record.
     */
    @Test
    void testUpdateLocation() {
        logger.info("Start Location Update");
        // Get user information
        User user = (User) userData.crud.getById(2);
        // Get locations associated with the user
        //Set<Location> userLocations = user.getLocations();
        Set<Location> userLocations = location.getLocations(user);
        // Get a location from the set
        Location userLocation = userLocations.iterator().next();
        // Get the location name, add "Updated" to the name, and set the new name
        userLocation.setName(userLocation.getName() + " Updated");
        // Update the location database record with the updated updated object
        locationData.crud.updateRecords(userLocation);
        // Get the updated location from the database based on the user location ID
        Location updatedRecord = (Location) locationData.crud.getById(userLocation.getId());

        logger.info("End Location Update");

        assertEquals(userLocation, updatedRecord);
    }

    /**
     * Test the deletion of a user record.
     */
    @Test
    void testDeleteLocation() {
        // Get user information
        User user = (User) userData.crud.getById(2);
        // Get locations associated with the user
        Set<Location> userLocations = location.getLocations(user);
        // Get a location from the set
        Location userLocation = userLocations.iterator().next();
        // Get the ID of the location to delete
        int deleteId = userLocation.getId();
        // Remove location from the database
        locationData.crud.deleteRecord(userLocation);
        // Test if record is gone
        assertNull(userData.crud.getById(deleteId));

        logger.info("Deleted record for ID: " + deleteId);
    }

    /**
     * Test get locations api.
     *
     * @throws JsonProcessingException the json processing exception
     */
    @Test
    void testGetLocationsAPI () throws JsonProcessingException {

        // Get user information
        User user = (User) userData.crud.getById(2);
        // Get locations associated with the user
        Set<Location> userLocations = location.getLocations(user);
        // Get a location from the set
        Location userLocation = userLocations.iterator().next();
        // Get the ID of the location to delete
        int locationId = userLocation.getId();

        Locations locationsApi = new Locations();

        Response response = locationsApi.getLocation(user.getUsername(), locationId);

        Location location = mapper.readValue((String) response.getEntity(), Location.class);

        assertEquals(200, response.getStatus());

    }

    /**
     * Test delete location api.
     */
    @Test
    void testDeleteLocationAPI () {

        // Get user information
        User user = (User) userData.crud.getById(2);
        // Get locations associated with the user
        Set<Location> userLocations = location.getLocations(user);
        // Get a location from the set
        Location userLocation = userLocations.iterator().next();
        // Get the ID of the location to delete
        int deleteId = userLocation.getId();

        Locations locationsApi = new Locations();

        Response response = locationsApi.deleteLocation(user.getUsername(), deleteId);

        assertEquals(200, response.getStatus());

    }

    /**
     * Test get location contact.
     */
    @Test
    void testGetLocationContact () {

        LocationContact newLocationContact = (LocationContact) locationContactData.crud.getById(7);

        assertEquals("FirstName", newLocationContact.getFirstName());
    }

    /**
     * Test insert location contact.
     */
    @Test
    void testInsertLocationContact () {
        LocationContact newObject = new LocationContact("FirstName", "LastName", "(123) 456-7890", "(987) 654-3210", "test@email.com", "123 Test Street", "Madison", 33, "12345", 1);
        int newId = (int) locationContactData.crud.insertRecord(newObject);
        LocationContact newLocationContact = (LocationContact) locationContactData.crud.getById(newId);

        assertEquals(newObject, newLocationContact);
    }
}