<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Available Identifications</title>

        <style>
            tr:first-child{
                font-weight: bold;
                background-color: #C6C9C4;
            }
        </style>

    </head>
    <body>
        <h2>List of Identifications</h2>  
        <table>
            <tr>
                <td>ID</td><td>ASSAY</td><td>SPECTRUM</td><td>SEQUENCE</td><td>CONFIDENCE</td><td></td>
            </tr>
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
        </table>
        <br/>

      <h2>List of Identifications for sequence KENNETH and in experiment 1</h2>  
        <table>
            <tr>
                <td>ID</td><td>ASSAY</td><td>SPECTRUM</td><td>SEQUENCE</td><td>CONFIDENCE</td><td></td>
            </tr>
            <c:forEach items="${identificationsfilteredpep}" var="ident">
                <tr>
                    <td>${ident.id}</td>
                    <td>${ident.assay}</td>
                    <td>${ident.spectrumID}</td>
                    <td>${ident.sequence}</td>
                    <td>${ident.confidence}</td>
                    <td> <a href="${pageContext.request.contextPath}/respin/view/${ident.id}" target="_blank">view</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>