package com.adhound.controller;

import com.adhound.entity.*;
import com.adhound.persistence.LocationData;
import com.adhound.persistence.LocationContactData;
import com.adhound.service.CrudService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.*;
import java.io.IOException;
import java.util.*;

/**
 * This class is the controller for the add location page.
 *
 * @author kkelm
 */
@WebServlet(
        urlPatterns = {"/dashboard/location/addContact"}
)

public class AddLocationContact extends HttpServlet {

    private HttpSession session;

    private  CrudService stateCrud = new CrudService(State.class);
    private List<State> states = this.stateCrud.getAll();

    private static Validator validator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        String locationId = request.getParameter("location");

        request.setAttribute("locationId", locationId);

        request.setAttribute("states", states);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/addLocationContact.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        // HashMap for the returned errors
        Map<String, String> errors = new HashMap<>();

        session = request.getSession();

        LocationContact locationContact = new LocationContact();
        LocationContactData locationContactData = new LocationContactData();
        int locationId = Integer.parseInt(request.getParameter("locationId").trim());

        locationContact.setFirstName(request.getParameter("firstNameTextbox").trim());
        locationContact.setLastName(request.getParameter("lastNameTextbox").trim());
        locationContact.setPhone(request.getParameter("phoneTextbox").trim());
        locationContact.setFax(request.getParameter("faxTextbox").trim());
        locationContact.setEmail(request.getParameter("emailTextbox").trim());
        locationContact.setAddress(request.getParameter("addresssTextbox").trim());
        locationContact.setCity(request.getParameter("cityTextbox").trim());
        locationContact.setStateId(Integer.parseInt(request.getParameter("stateIdDropdown").trim()));
        locationContact.setZipcode(request.getParameter("zipcodeTextbox").trim());
        locationContact.setTypeId(Integer.parseInt(request.getParameter("contactTypeDropdown").trim()));

        Set<ConstraintViolation<LocationContact>> constraintViolations = validator.validate(locationContact);
        // FOR REFERENCE
        //constraintViolations.isEmpty()
        //constraintViolations.iterator().next()
        //constraintViolations.iterator().next().getPropertyPath().toString()
        //constraintViolations.iterator().next().getInvalidValue()
        //constraintViolations.iterator().next().getMessage()
        if (constraintViolations.isEmpty()) {

            int newId = 0;

            newId = (int) locationContactData.crud.insertRecord(locationContact);
            LocationContact newLocationContact = (LocationContact) locationContactData.crud.getById(newId);

            if (newLocationContact != null) {
                LocationData locationData = new LocationData();
                Location location = (Location) locationData.crud.getById(locationId);
                Set<LocationContact> locationContacts = location.getLocationContacts();
                locationContacts.add(locationContact);
                location.setLocationContacts(locationContacts);
                locationData.crud.updateRecords(location);

                request.setAttribute("contactFirstName", locationContact.getFirstName());
            }
            else {

                request.setAttribute("contactFirstName", "");
            }
        }
        else {

            Iterator<ConstraintViolation<LocationContact>> errorMessages = constraintViolations.iterator();

            while (errorMessages.hasNext()) {

                ConstraintViolation<LocationContact> next = errorMessages.next();

                String property = next.getPropertyPath().toString();
                String message = next.getMessage();

                errors.put(property, message);
            }

        }

        request.setAttribute("locationId", locationId);

        request.setAttribute("errormessages", errors);

        request.setAttribute("states", states);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/addLocationContact.jsp");
        dispatcher.forward(request, response);

    }

}
