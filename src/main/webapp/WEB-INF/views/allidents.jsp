<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link REL="stylesheet" TYPE="text/css" HREF="${pageContext.request.contextPath}/resources/css/scrollable_table.css">
        <title>Available Identifications</title>
    </head>
    <body>
        <h2>List of Identifications</h2>  
        <div id="tableContainer" class="tableContainer">
            <table border="0" cellpadding="0" cellspacing="0" width="100%" class="scrollTable">
                <thead class="fixedHeader">
                    <tr>
                        <th>ID</th>
                        <th>SEQUENCE</th>
                        <th>ASSAY</th>
                        <th>SPECTRUM</th>
                        <th>CONFIDENCE</th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody class="scrollContent">
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