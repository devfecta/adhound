package com.adhound.controller;

import com.adhound.persistence.UserData;
import com.adhound.service.CrudService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class is the controller for the dashboard.
 *
 * @author kkelm
 */
@WebServlet(
        urlPatterns = {"/dashboard"}
)

public class Dashboard extends HttpServlet {

    private HttpSession session;

    private CrudService crud;

    private UserData userData = new UserData();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/index.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/index.jsp");
        dispatcher.forward(request, response);


    }

}
