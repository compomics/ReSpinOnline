<!DOCTYPE html>
<html>
    <head>
                <title>Experiment metadata for ${experiment}</title>
        <link REL="stylesheet" TYPE="text/css" HREF="${pageContext.request.contextPath}/resources/css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script>
            function loadMetaData(url) {
                $(document).ready(function () {
                    var experiment_table = document.getElementById('experiment_metadata');
                    //load metadata for assay
                    $.getJSON(url, function (result) {
                        $.each(result, function (i, field) {
                            addRows(i, field, experiment_table, false);
                            if (i == "projectAccession") {
                                //get metadata for project (should be last entry...)
                                $.getJSON("https://www.ebi.ac.uk:443/pride/ws/archive/project/".concat(field), function (result) {
                                    $.each(result, function (i, field) {
                                        addRows(i, field, experiment_table, true);
                                    });
                                });
                            }
                        });
                    });
                });
            }
            function addRows(key, value, table, project) {
                //ignore unparsed objects
                if (!(value)) {
                    value = "Not available";
                }
                if (value.toString().indexOf("[object Object]") === -1) {
                    var row = table.insertRow(-1);
                    var cell1 = row.insertCell(0);
                    var cell2 = row.insertCell(1);
                    cell1.innerHTML = key;
                    cell2.innerHTML = value;
                    if (project) {
                        cell1.style.color = "#33cc33";
                    } else {
                        cell1.style.color = "#0099ff";
                    }
                }
            }

        </script>
    </head>
    <body onload="loadMetaData('https://www.ebi.ac.uk/pride/ws/archive/assay/${experiment}')">
        <div>
            <h1>Metadata for experiment ${experiment}</h1>
            Note that project metadata is <font color="#33cc33">green</font> while experiment metadata is <font color="#0099ff">blue</font>.
            <table id='experiment_metadata'>
                <tr>
                    <th>Parameter</th>
                    <th>Value</th>
                </tr>
            </table>
        </div>
    </body>
</html>