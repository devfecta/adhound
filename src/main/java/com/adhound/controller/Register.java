package com.adhound.controller;

import com.adhound.entity.*;
import com.adhound.persistence.UserData;
import com.adhound.service.CrudService;
import com.adhound.service.PayPal;
import com.paypal.subscriptions.Subscribe;
import org.hibernate.exception.ConstraintViolationException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * This class is the controller for the registration page.
 *
 * @author kkelm
 */
@WebServlet(
        urlPatterns = {"/register"}
)

public class Register extends HttpServlet {

    private HttpSession session;

    private CrudService crud = new CrudService(State.class);

    private List <State> states = this.crud.getAll();

    private static Validator validator;
    //private Object ConstraintViolationException;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        request.setAttribute("states", states);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> errors = new HashMap<>();

        session = request.getSession();

        UserData userData = new UserData();

        User newUser = new User();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        newUser.setUsername(request.getParameter("emailTextbox").trim());
        newUser.setPassword(request.getParameter("passwordTextbox").trim());
        newUser.setFirstName(request.getParameter("firstNameTextbox").trim());
        newUser.setLastName(request.getParameter("lastNameTextbox").trim());
        newUser.setPhone(request.getParameter("phoneTextbox").trim());
        newUser.setFax(request.getParameter("faxTextbox").trim());
        newUser.setEmail(request.getParameter("emailTextbox").trim());
        newUser.setAddress(request.getParameter("addresssTextbox").trim());
        newUser.setCity(request.getParameter("cityTextbox").trim());
        newUser.setStateId(Integer.parseInt(request.getParameter("stateIdDropdown").trim()));
        newUser.setZipcode(request.getParameter("zipcodeTextbox").trim());

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(newUser);

        if (constraintViolations.isEmpty()) {

            int newId = 0;

            try {
                UserRole userRole = new UserRole();
                userRole.setUsername(newUser.getUsername());
                newUser.setUserRole(userRole);

                newId = (int) userData.crud.insertRecord(newUser);
                newUser = (User) userData.crud.getById(newId);

                //UserRole userRole = new UserRole(newUser, newUser.getUsername());
                //Serializable userRoleId = userData.crud.insertRecord(userRole);

                User insertedUser = (User) userData.crud.getById(newId);

                if (insertedUser != null) {
                    /**
                     * Instantiate a new array list of user data.
                     */
                    List <User> user = new ArrayList<>();
                    /**
                     * Add the user data to the array list.
                     */
                    user.add(insertedUser);
                    /**
                     * Pass the array list to the view.
                     */
                    request.setAttribute("registrantFirstName", insertedUser.getFirstName());

                    PayPal payPal = new PayPal();
                    Subscribe subscribe = payPal.getSubscription(newUser);

                    String payPalLink = subscribe.getLinks().get(0).getHref();

                    response.sendRedirect(payPalLink);
                    return;

                }
                else {

                    request.setAttribute("registrantFirstName", "");
                }
            }
            catch (ConstraintViolationException constraintException) {

                if (constraintException.getConstraintName().equals("users.username") && constraintException.getCause().getMessage().contains("Duplicate")) {
                    errors.put("username", "Username Already Exists");
                }

            }

        }
        else {

            Iterator<ConstraintViolation<User>> errorMessages = constraintViolations.iterator();

            while (errorMessages.hasNext()) {

                ConstraintViolation<User> next = errorMessages.next();

                String property = next.getPropertyPath().toString();
                String message = next.getMessage();

                errors.put(property, message);
            }
        }

        request.setAttribute("errormessages", errors);

        request.setAttribute("states", states);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);

    }

}
