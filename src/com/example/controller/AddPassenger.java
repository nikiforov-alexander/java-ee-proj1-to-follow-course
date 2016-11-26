package com.example.controller;

import com.example.model.Gender;
import org.glassfish.logging.annotation.LogMessagesResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Servlet implementation class AddPassenger
 */
@WebServlet("/add-passenger")
public class AddPassenger extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPassenger() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().append("Served at: ").append(request.getContextPath());
        RequestDispatcher view = request.getRequestDispatcher(
                "WEB-INF/views/add-passenger.jsp"
        );

        view.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
        String firstName = request.getParameter("first-name");

        if (firstName.length() == 0) {
            request.setAttribute("error", true);
            response.sendRedirect("/");
        }


        String lastName = request.getParameter("last-name");
        String dateOfBirthRaw = request.getParameter("date-of-birth");
        String[] dateOfBirthArray = dateOfBirthRaw.split("\\/");

        Calendar calendar = Calendar.getInstance();
        calendar.set(
                Integer.parseInt(dateOfBirthArray[0]),
                Integer.parseInt(dateOfBirthArray[1]),
                Integer.parseInt(dateOfBirthArray[2])
        );
        Date dateOfBirth = calendar.getTime();

        String rawGender = request.getParameter("gender");

        System.out.println();
    }

}
