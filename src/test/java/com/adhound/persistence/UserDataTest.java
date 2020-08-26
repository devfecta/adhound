package com.adhound.persistence;

import com.adhound.entity.Location;
import com.adhound.entity.User;
import com.adhound.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.validation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User data test.
 */
class UserDataTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The User data.
     */
    UserData userData;

    /**
     * The New user.
     */
    User newUser;

    private static Validator validator;

    /**
     * Sets up the tests with a new objects.
     */
    @BeforeEach
    void setUp() {
        //Database database = Database.getInstance();
        //database.createDatabase("adhound.sql");
        userData = new UserData();

        newUser = new User("testUsername@email.com", "testPassword", "testFirstName", "testLastName", "123-456-7890", "987-654-3210", "test@email.com", "123 Test Street", "testCity", 33, "12345");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Test get all users data.
     */
    @Test
    void testGetAllUsers() {
        List<User> users = userData.crud.getAll();
        int currentSize = users.size();
        assertEquals(currentSize, users.size());
        logger.info("Got all users");
    }

    /**
     * Test get one user data success.
     */
    @Test
    void testGetByIdUser() {
        User getUser = (User) userData.crud.getById(1);
        assertEquals("Kevin", getUser.getFirstName());
        logger.info("Got user information for ID: " + getUser.getId());
    }

    /**
     * Test the creation of a new user record.
     */
    @Test
    void testInsertUser() {
        //String newUsername = "testUsername" + Math.round(Math.random()*100) + "@email.com";
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(newUser);

        try {
            UserRole userRole = new UserRole();
            userRole.setUsername(newUser.getUsername());
            newUser.setUserRole(userRole);

            int newId = (int) userData.crud.insertRecord(newUser);
            User insertedUser = (User) userData.crud.getById(newId);

            assertEquals(newUser, insertedUser);

            logger.info("Inserted record for ID: " + newId);
        }
        catch(ConstraintViolationException e) {
            assertEquals(ConstraintViolationException.class, e.getClass());
            logger.info("Threw Exception when inserting user:  " + e.getCause());
        }

    }

    /**
     * Test an update of a user record.
     */
    @Test
    void testUpdateUser() {
        String password = "testPassword";
        User updateUser = (User) userData.crud.getById(1);
        updateUser.setPassword(password);
        userData.crud.updateRecords(updateUser);
        User getUser = (User) userData.crud.getById(1);
        assertEquals(getUser, updateUser);
        logger.info("Updated the password for ID: " + updateUser.getId());
    }

    /**
     * Test the deletion of a user record.
     */
    @Test
    void testDeleteUser() {
        List<User> allUsers = userData.crud.getAll();
        int deleteId = allUsers.get((allUsers.size() - 1)).getId();
        userData.crud.deleteRecord(userData.crud.getById(deleteId));
        assertNull(userData.crud.getById(deleteId));
        // List<User> allUsersMinusOne = userData.crud.getAll();
        // assertNotEquals(allUsersMinusOne, allUsers);
        logger.info("Deleted record for ID: " + deleteId);

        LocationData locationData = new LocationData();
        Location location = new Location();
        Set<Location> userLocations = location.getLocations((User) userData.crud.getById(deleteId));

        assertEquals(0, userLocations.size());

    }

}