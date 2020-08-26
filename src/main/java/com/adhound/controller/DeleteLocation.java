package com.adhound.controller;

import com.adhound.entity.Location;
import com.adhound.persistence.LocationData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * This class is the controller for the delete location page.
 *
 * @author kkelm
 */
@WebServlet(
        urlPatterns = {"/dashboard/deleteLocation/*"}
)

public class DeleteLocation extends HttpServlet {

    private HttpSession session;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        LocationData locationData = new LocationData();

        Location location = (Location) locationData.crud.getById(Integer.parseInt(request.getParameter("id")));

        request.setAttribute("locationName", location.getName());
        request.setAttribute("locationId", location.getId());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/deleteLocation.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(domain + "/adhound/api/locations")
                .path("{username}").resolveTemplate("username", request.getUserPrincipal().getName())
                .path("{location}").resolveTemplate("location", "location")
                .path("{delete}").resolveTemplate("delete", "delete")
                .path("{id}").resolveTemplate("id", request.getParameter("id"));

        String json = target.request(MediaType.APPLICATION_JSON).delete(String.class);

        response.sendRedirect("locations");

    }

}
