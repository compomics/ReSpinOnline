<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link REL="stylesheet" TYPE="text/css" HREF="${pageContext.request.contextPath}/resources/css/style.css">
        <title>Available Identifications</title>
    </head>
    <body>

        <div id="form-wrapper">
            <form action="${pageContext.request.contextPath}/query" method="GET">  
                <input type="text" name="query_value" value="${query_value}">  <input type="submit" value="Search" /><br>
                <input type="radio" name="query_type" value="sequence" <c:if test='${query_type == "sequence"}'>checked</c:if>>Sequence
                <input type="radio" name="query_type" value="experiment" <c:if test='${query_type == "experiment"}' >checked</c:if>> Experiment
            </form>
        </div>

        <div>
            <h2><b>List of Identifications</b></h2>  
            <table >
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>ASSAY</th>
                        <th>SPECTRUM</th>
                        <th>SEQUENCE</th>
                        <th>CONFIDENCE</th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${identifications}" var="ident">
                        <tr>
                            <td>${ident.id}</td>
                            <td>${ident.assay}</td>
                            <td>${ident.spectrumID}</td>
                            <td>${ident.sequence}</td>
                            <td>${ident.confidence}</td>
                            <td> <a href="${pageContext.request.contextPath}/respin/view/${ident.id}" target="_blank">view</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <br/>


    </body>
</html>