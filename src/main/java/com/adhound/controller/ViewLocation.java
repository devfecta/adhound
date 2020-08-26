package com.adhound.controller;

import com.adhound.entity.*;
import com.adhound.persistence.LocationData;
import com.adhound.service.CrudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

/**
 * This class is the controller for the view location page.
 *
 * @author kkelm
 */
@WebServlet(
        urlPatterns = {"/dashboard/location"}
)

public class ViewLocation extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private HttpSession session;

    private CrudService stateCrud = new CrudService(State.class);
    private List<State> states = this.stateCrud.getAll();

    private CrudService regionCrud = new CrudService(Region.class);
    private List<Region> regions = this.regionCrud.getAll();

    private static Validator validator;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(domain + "/adhound/api/locations")
                .path("{username}").resolveTemplate("username", request.getUserPrincipal().getName())
                .path("{location}").resolveTemplate("location", "location")
                .path("{id}").resolveTemplate("id", request.getParameter("id"));

        String json = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();

        Location location = mapper.reader().forType(Location.class).readValue(json);

        request.setAttribute("location", location);

        request.setAttribute("states", states);

        request.setAttribute("regions", regions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/location.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        // HashMap for the returned errors
        Map<String, String> errors = new HashMap<>();

        // Get Location data
        LocationData locationData = new LocationData();
        Location location = (Location) locationData.crud.getById(Integer.parseInt(request.getParameter("id")));
        // Set updated Location data
        location.setName(request.getParameter("nameTextbox").trim());
        location.setPhone(request.getParameter("phoneTextbox").trim());
        location.setFax(request.getParameter("faxTextbox").trim());
        location.setAddress(request.getParameter("addresssTextbox").trim());
        location.setCity(request.getParameter("cityTextbox").trim());
        location.setStateId(Integer.parseInt(request.getParameter("stateIdDropdown").trim()));
        location.setZipcode(request.getParameter("zipcodeTextbox").trim());
        location.setRegionId(Integer.parseInt(request.getParameter("regionIdDropdown").trim()));
        // Validate updated Location data
        Set<ConstraintViolation<Location>> constraintViolations = validator.validate(location);

        if (constraintViolations.isEmpty()) {

            String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

            Client client = ClientBuilder.newClient();

            WebTarget target = client.target(domain + "/adhound/api/locations/update");

            ObjectMapper mapper = new ObjectMapper();

            String locationJSON = mapper.writeValueAsString(location);

            Response responseJSON = target.path(request.getUserPrincipal().getName()).request().put(Entity.json(locationJSON));

            location = responseJSON.readEntity(Location.class);

            request.setAttribute("locationName", location.getName());

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

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/location.jsp");
        dispatcher.forward(request, response);
    }

}
