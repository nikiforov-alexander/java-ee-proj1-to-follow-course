package com.example.controller;

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
