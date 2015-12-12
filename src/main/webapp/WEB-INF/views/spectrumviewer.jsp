<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

        <title>Spectrum Viewing Panel</title>

        <!--[if IE]><script language="javascript" type="text/javascript" src="../js/excanvas.min.js"></script><![endif]-->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.4/jquery-ui.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.flot.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.flot.selection.js"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/specview.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/peptide.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/aminoacid.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ion.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/internal.js"></script>    

       <link REL="stylesheet" TYPE="text/css" HREF="${pageContext.request.contextPath}/resources/css/lorikeet.css"> 

    </head>

    <body>
        <!-- PLACE HOLDER DIV FOR THE SPECTRUM -->
        <div id="lorikeet"></div>
        <script type="text/javascript">

            $(document).ready(function () {

                /* render the spectrum with the given options */
                $("#lorikeet").specview({sequence: sequence,
                    scanNum: 2441,
                    charge: 2,
                    precursorMz: 1012.1,
                    fileName: fileName,
                    //staticMods: staticMods, 
                    variableMods: varMods,
                    ntermMod: ntermMod,
                    showInternalIonOption: true,
                    showMHIonOption: true,
                    showAllTable: true,
                    //ctermMod: ctermMod,
                    peaks: peaks
                });

            });
            //the name of the mgf file
            var fileName = "${identification.assay}";
            //sequence
            var sequence = "${identification.sequence}";
            var varMods = [];
            // modification index = 14; modification mass = 16.0; modified residue = 'M'
            varMods[0] = {index: 14, modMass: 16.0, aminoAcid: 'M'};
            // mass to be added to the N-terminus
            var ntermMod = 164.07;
            // peaks in the scan: [m/z, intensity] pairs.
            var peaks = ${spectrum.ms2Peaks};
        </script>

    </body>

</html>