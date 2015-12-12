<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link REL="stylesheet" TYPE="text/css" HREF="${pageContext.request.contextPath}/resources/css/style.css">
        <title>Available Identifications</title>
    </head>
    <body>
        <div style='width:100%;text-align:center;background: #BDB76B;border:1px solid black;'><h1>ReSpin Identifications</h1></div>
        <div id="sidebar">
            <div id="query_panel">
                <form action="${pageContext.request.contextPath}/query" method="GET" onsubmit="return validate()">  
                    <label><h3>Query</h3></label>
                    <label>( ${fn:length(identifications)} records found )</label>
                    <ul>
                        <li>Sequence<input id='sequence' type="text" name="sequence" value="${sequence}"></li>
                    </ul>
                    <label> <a href="#" onclick="toggle_visibility();"><p id="message">Show filters</p></a></label>   
                    <div id='filters'>
                        <label> <h3>Filters</h3></label>
                        <ul>    
                            <li>Experiment<input id='experiment' type="text" name="experiment" value="${experiment}"></li>
                            <li>Confidence Threshold<input id='confidence' type="text" name="confidence" value="${confidence}"></li>  
                            <input id = "expanded" name ="expanded" type="hidden" value="${expanded}"/><br>
                        </ul>
                    </div>
                    <input type="submit" value="Search" style="display: block; margin: 0 auto;"/><br>
                </form>
            </div>
            <div id="result_panel">
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
                                <td> <a href="${pageContext.request.contextPath}/respin/experiment/${ident.assay}" target="_blank">${ident.assay}</a></td>
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
        </div>
        <c:if test="${expanded==1}">
            <script>
                document.getElementById('message').innerHTML = "Hide filters";
                document.getElementById('filters').style.display = "block";
            </script>
        </c:if>
        <div style='width:100%;text-align:center;background: #BDB76B;border:1px solid black;'><h5>By Kenneth Verheggen</h5></div>
    </body>

    <script type="text/javascript">

        function toggle_visibility() {
            var e = document.getElementById('filters');
            var msg = document.getElementById('message');
            var exp = document.getElementById('expanded');
            if (is_collapsed(exp.value)) {
                msg.innerHTML = "Hide filters";
                document.getElementById('expanded').value = 1;
                e.style.display = "block";
            } else {
                msg.innerHTML = "Show filters";
                document.getElementById('expanded').value = 0;
                e.style.display = "none";
            }
        }
        function is_collapsed(str) {
            var n = ~~Number(str);
            return String(n) === str && n === 0;
        }
        function validate() {


            var re = /^[A-Za-z]+$/;
            var re2 = /[^0-9]/;
            var validated = true;
            var error = "Errors\n-------\n";

            //validate the sequence
            var sequence = document.getElementById('sequence').value;
            if ((sequence) && !re.test(sequence)) {
                error = error.concat(" - Sequence can not contain numbers or characters other than single letter amino acids:" + sequence + "\n");
                validated = false;
            }

            //validate the confidence input
            var conf = document.getElementById('confidence').value;
            if (((conf) && re2.test(conf)) || ((conf) && (Number(conf) < 0) || (Number(conf) > 100))) {
                validated = false;
                error = error.concat("- Confidence Threshold has to be numeric between 0 and 100 :" + conf + "\n");
            }
            //@ToDo validate other input
            if (!validated) {
                alert(error);
            }
            return validated;
        }
    </script>

</html>