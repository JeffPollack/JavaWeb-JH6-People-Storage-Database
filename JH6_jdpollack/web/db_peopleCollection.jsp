<%-- 
    Document   : db_peopleCollection
    Created on : Nov 11, 2016, 9:45:56 PM
    Author     : Jeff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>A Collection of People, JH6</title>
    </head>
    <body>
        <h1>JH6_jdpollack</h1>
        <form action="DB_PersonServlet">
            <input type="submit" name="action" value="Clear List"/>            
        </form>
        </p>
        <form action="DB_PersonServlet">
            <input type="text" name="name"/> Name
            <br><input type="text" name="eye"/> Eye Color
            <br><input type="text" name="hair"/> Hair Color
            <br><input type="text" name="height"/> Height
            <br><input type="text" name="weight"/> Weight
            <br><input type="submit" name="action" value="add"/>
        </form>
        <hr>
        <h3>${error}</h3>
        <table border="3">
            <tr><th>Name</th><th>EyeColor</th><th>HairColor</th><th>Height</th><th>Weight</th><th></th></tr>
            <c:forEach var="person" items="${PeopleCollection}" varStatus="loopStatus">
                <tr>
                <form action="DB_PersonServlet">
                    <td><input type="text" name="name" value="${person.name}"/></td>
                    <td><input type="text" name="eye" value="${person.eye}"/></td>
                    <td><input type="text" name="hair" value="${person.hair}"/></td>
                    <td><input type="text" name="height" value="${person.height}"/></td>
                    <td><input type="text" name="weight" value="${person.weight}"/></td>
                    <td>
                        <input type="submit" name="action" value="remove"/>
                        <input type="submit" name="action" value="update"/>
                        <input type="hidden" name="index" value="${person.index}"/>
                    </td>
                </form>    
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
