package com.example.controller;

import com.example.model.Gender;
import com.example.model.Passenger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/add-passenger")
public class AddPassengerServlet extends HttpServlet {

    public AddPassengerServlet() {
        super();
    }

    /**
     * this method shows "add-passenger" page, with "form"
     * for user to fill and send using
     * {@link #doPost(HttpServletRequest, HttpServletResponse)}
     * @param request : {@literal request} gets {@literal RequestDispatcher}
     * @param response : used in @{literal forward} method
     * @throws ServletException : see {@link HttpServlet}
     * @throws IOException : see {@link HttpServlet}
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(
                "WEB-INF/views/add-passenger.jsp"
        );
        view.forward(request, response);
    }

    /**
     * this method process "add-passenger" POST request,
     * adds new passenger. For now it does not much do
     * anything, other than redirects successfully to "/" page,
     * or back to "add-passenger" page, when input is wrong.
     * @param request : {@literal request} gets {@literal RequestDispatcher}
     *                when "forwarded" with error. Also for now following
     *                lecture, we set "error" attribute to {@literal true},
     *                and "field error" for each "field" that has error.
     * @param response : used in @{literal forward} method
     * @throws ServletException : see {@link HttpServlet}
     * @throws IOException : see {@link HttpServlet}
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        // initially set "errors" to false, to avoid NullPointerException
        request.setAttribute("errors", false);

        // check if first name is right
        String firstName = request.getParameter("first-name");
        if (firstName.length() == 0) {
            request.setAttribute("error", true);
            request.setAttribute("first-name error", true);
        }

        // check if last name is right
        String lastName = request.getParameter("last-name");
        if (lastName.length() == 0) {
            request.setAttribute("error", true);
            request.setAttribute("last-name error", true);
        }

        // check if date of birth is right
        String dateOfBirthRaw = request.getParameter("date-of-birth");
        Pattern dateOfBirthPattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        Matcher matcher = dateOfBirthPattern.matcher(dateOfBirthRaw);
        if (matcher.matches()) {
            String[] dateOfBirthArray = dateOfBirthRaw.split("/");

            Calendar calendar = Calendar.getInstance();
            calendar.set(
                    Integer.parseInt(dateOfBirthArray[0]),
                    Integer.parseInt(dateOfBirthArray[1]),
                    Integer.parseInt(dateOfBirthArray[2])
            );
            Date dateOfBirth = calendar.getTime();
        } else {
            request.setAttribute("date-of-birth error", true);
        }

        // gender is always right
        String rawGender = request.getParameter("gender");

        // if there are errors, we redirect:
        if ((Boolean) request.getAttribute("error")) {
            // This getting request dispatcher and view.forward
            // works like a redirect.
            RequestDispatcher view = request.getRequestDispatcher(
                    "WEB-INF/views/add-passenger.jsp"
            );
            view.forward(request, response);
        } else {
            // else we add passengers as attribute to
            // ServletContext with new passenger
            // synchronized is added to make sure List<Passenger>
            // is not lost for different threads
            synchronized (this) {
                List<Passenger> passengers = (List<Passenger>) this.getServletContext()
                        .getAttribute("passengers");

                passengers.add(
                        new Passenger(firstName, lastName, new Date(), Gender.FEMALE)
                );

                ServletContext servletContext = this.getServletContext();
                servletContext.setAttribute("passengers", passengers);
            }
            RequestDispatcher view = request.getRequestDispatcher(
                    "WEB-INF/views/index.jsp"
            );
            view.forward(request, response);
        }
    }

}
