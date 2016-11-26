<%--
  Created by IntelliJ IDEA.
  User: nikiforov-alexander
  Date: 11/18/16
  Time: 7:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="resources/css/normalize.css">
    <link rel="stylesheet" href="resources/css/theme.css">
</head>
<body>
    <h2>Passenger Form</h2>

    <div class="container">

        <%--
         This is called scriplet. All that is inside, will be
         invoken, when condition is true
        --%>
        <%
            if (request.getAttribute("error") != null) {

        %>
            <div>
                <span>Error</span>
            </div>
        <%
            }
        %>

        <fieldset>

            <legend>Passenger Details</legend>

            <form action="add-passenger" method="post">

                <div class="inputfield">
                    <label for="first-name">
                        First Name
                    </label>

                    <input id="first-name"
                           name="first-name"
                           type="text">
                </div>

                <div class="inputfield">
                    <label for="last-name">
                        Last Name
                    </label>

                    <input id="last-name"
                           name="last-name"
                           type="text">
                </div>

                <div class="inputfield">
                    <label for="date-of-birth">
                        Date of Birth
                    </label>

                    <input id="date-of-birth"
                           name="date-of-birth"
                           type="text">
                </div>

                <div class="inputfield">
                    <label for="gender">
                        Gender
                    </label>
                    <select name="gender" id="gender">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                </div>

                <div class="inputfield">
                    <input id="submit-button"
                           type="submit"
                           value="Add New Passenger">
                </div>
            </form>

        </fieldset>

    </div>
</body>
</html>
