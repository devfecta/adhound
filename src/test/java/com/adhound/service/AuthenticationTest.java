package com.adhound.service;

import com.adhound.persistence.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Authentication test.
 */
class AuthenticationTest {

    /**
     * The User data.
     */
    UserData userData;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        userData = new UserData();
    }

    /**
     * Test user authentication.
     */
    @Test
    void testUserAuthentication() {

        assertEquals(2, userData.userAuthentication("distributor@email.com"));

    }
}