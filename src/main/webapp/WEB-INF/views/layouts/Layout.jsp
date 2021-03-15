<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
                <title>Flight Ticket</title>
                <link rel="icon" href="<c:url value="/storage/img/icon.jpg"/>" type="image/jpg">
                <link rel="stylesheet" href="<c:url value="/storage/vendor/bootstrap/css/bootstrap.min.css "/>">
                <script src="<c:url value="/storage/vendor/jquery/jquery.min.js "/>"></script>
                <script src="<c:url value="/storage/vendor/bootstrap/js/bootstrap.min.js "/>"></script>
            </head>

            <body>
                <tiles:insertAttribute name="header" />
                <tiles:insertAttribute name="body" />

            </body>

            </html>
