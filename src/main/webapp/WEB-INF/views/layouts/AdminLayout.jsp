
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                <meta name="description" content="">
                <meta name="author" content="">
                <title>Flight TM Admin</title>

                <!-- Custom fonts for this template -->
                <link href='<c:url value="/storage/vendor/fontawesome-free/css/all.min.css "/>' rel="stylesheet" type="text/css">
                <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

                <!-- Custom styles for this template -->
                <link href='<c:url value="/storage/css/sb-admin-2.min.css "/>' rel="stylesheet">

                <!-- Custom styles for this page -->
                <link href='<c:url value="/storage/vendor/datatables/dataTables.bootstrap4.min.css "/>' rel="stylesheet">

                <!-- Bootstrap core JavaScript-->
                <script src='<c:url value="/storage/vendor/jquery/jquery.min.js "/>'></script>

            </head>

            <body id="page-top">
                <tiles:insertAttribute name="menu" />
                <tiles:insertAttribute name="header" />
                <tiles:insertAttribute name="body" />
                <tiles:insertAttribute name="footer" />


                <script src='<c:url value="/storage/vendor/jquery/jquery.min.js"/>'></script>
                <script src='<c:url value="/storage/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>

                <!-- Core plugin JavaScript-->
                <script src='<c:url value="/storage/vendor/jquery-easing/jquery.easing.min.js"/>'></script>

                <!-- Custom scripts for all pages-->
                <script src='<c:url value="/storage/js/sb-admin-2.min.js"/>'></script>

                <!-- Page level plugins -->
                <script src='<c:url value="/storage/vendor/chart.js/Chart.min.js"/>'></script>

                <!-- Page level custom scripts -->
                <script src='<c:url value="/storage/js/demo/chart-area-demo.js"/>'></script>
                <script src='<c:url value="/storage/js/demo/chart-pie-demo.js"/>'></script>
                <script src='<c:url value="/storage/vendor/datatables/jquery.dataTables.min.js"/>'></script>
                <script src='<c:url value="/storage/vendor/datatables/dataTables.bootstrap4.min.js"/>'></script>

                <!-- Page level custom scripts -->
                <script src='<c:url value="/storage/js/demo/datatables-demo.js"/>'></script>

            </body>

            </html>
