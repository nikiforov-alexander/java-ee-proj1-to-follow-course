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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        // initially set "error" to false, to avoid NullPointer
        request.setAttribute("error", false);
        String firstName = request.getParameter("first-name");

        if (firstName.length() == 0) {
            request.setAttribute("error", true);
            request.setAttribute("first_name error", true);
        }

        String lastName = request.getParameter("last-name");
        String dateOfBirthRaw = request.getParameter("date-of-birth");
        Pattern dateOfBirthPattern = Pattern.compile("^\\d\\d/\\d\\d/\\d\\d\\d\\d$");
        Matcher matcher = dateOfBirthPattern.matcher(dateOfBirthRaw);
        if (matcher.matches()) {
            String[] dateOfBirthArray = dateOfBirthRaw.split("\\/");

            Calendar calendar = Calendar.getInstance();
            calendar.set(
                    Integer.parseInt(dateOfBirthArray[0]),
                    Integer.parseInt(dateOfBirthArray[1]),
                    Integer.parseInt(dateOfBirthArray[2])
            );
            Date dateOfBirth = calendar.getTime();
        } else {
            request.setAttribute("error date-of-birth", true);
        }

        String rawGender = request.getParameter("gender");

        // if there are errors, we redirect:
        if ((Boolean) request.getAttribute("error")) {
            // This getting request dispatcher and view.forward
            // works like a redirect.
            RequestDispatcher view = request.getRequestDispatcher(
                    "WEB-INF/views/add-passenger.jsp"
            );
            view.forward(request, response);
        }

        System.out.println();
    }

}
