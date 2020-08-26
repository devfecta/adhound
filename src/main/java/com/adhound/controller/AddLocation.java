package com.adhound.controller;

import com.adhound.entity.*;
import com.adhound.persistence.LocationData;
import com.adhound.persistence.UserData;
import com.adhound.service.CrudService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.*;

/**
 * This class is the controller for the add location page.
 *
 * @author kkelm
 */
@WebServlet(
        urlPatterns = {"/dashboard/addLocation"}
)

public class AddLocation extends HttpServlet {

    private HttpSession session;

    private  CrudService stateCrud = new CrudService(State.class);
    private List<State> states = this.stateCrud.getAll();

    private  CrudService regionCrud = new CrudService(Region.class);
    private List<Region> regions = this.regionCrud.getAll();

    private static Validator validator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        request.setAttribute("states", states);

        request.setAttribute("regions", regions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/addLocation.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        // HashMap for the returned errors
        Map<String, String> errors = new HashMap<>();

        session = request.getSession();

        UserData userData = new UserData();
        int userId = userData.userAuthentication(request.getUserPrincipal().getName());
        User user = (User) userData.crud.getById(userId);

        Location newLocation = new Location();

        newLocation.setUser(user);

        newLocation.setName(request.getParameter("nameTextbox").trim());
        newLocation.setPhone(request.getParameter("phoneTextbox").trim());
        newLocation.setFax(request.getParameter("faxTextbox").trim());
        newLocation.setAddress(request.getParameter("addresssTextbox").trim());
        newLocation.setCity(request.getParameter("cityTextbox").trim());
        newLocation.setStateId(Integer.parseInt(request.getParameter("stateIdDropdown").trim()));
        newLocation.setZipcode(request.getParameter("zipcodeTextbox").trim());
        newLocation.setRegionId(Integer.parseInt(request.getParameter("regionIdDropdown").trim()));

        Set<ConstraintViolation<Location>> constraintViolations = validator.validate(newLocation);
        // FOR REFERENCE
        //constraintViolations.isEmpty()
        //constraintViolations.iterator().next()
        //constraintViolations.iterator().next().getPropertyPath().toString()
        //constraintViolations.iterator().next().getInvalidValue()
        //constraintViolations.iterator().next().getMessage()
        if (constraintViolations.isEmpty()) {

            int newId = 0;
            //newId = (int) userData.crud.insertRecord(newUser);

                newId = (int) userData.crud.insertRecord(newLocation);

                LocationData locationData = new LocationData();
                Location insertedLocation = (Location) locationData.crud.getById(newId);

                if (insertedLocation != null) {
                    /**
                     * Instantiate a new array list of user data.
                     */
                    List <Location> location = new ArrayList<>();
                    /**
                     * Add the user data to the array list.
                     */
                    location.add(insertedLocation);
                    /**
                     * Pass the array list to the view.
                     */
                    request.setAttribute("locationName", insertedLocation.getName());

                }
                else {

                    request.setAttribute("registrantFirstName", "");
                }

        }
        else {

            Iterator<ConstraintViolation<Location>> errorMessages = constraintViolations.iterator();

            while (errorMessages.hasNext()) {

                ConstraintViolation<Location> next = errorMessages.next();

                String property = next.getPropertyPath().toString();
                String message = next.getMessage();

                errors.put(property, message);
            }

        }

        request.setAttribute("errormessages", errors);

        request.setAttribute("states", states);

        request.setAttribute("regions", regions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/addLocation.jsp");
        dispatcher.forward(request, response);

    }

}
