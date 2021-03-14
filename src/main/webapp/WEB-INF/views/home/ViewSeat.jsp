<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <c:url value="/storage/css/ViewSeat.css" var="cssUrl" />
        <c:url value="/storage/js/ViewSeat.js" var="jsUrl" />

        <link rel="stylesheet" href='${cssUrl}'>

        <div class="plane">
            <div class="cockpit">
                <h1>Please select a seat</h1>
            </div>

            <div class="business business--front fuselage">

            </div>
            <ol class="cabin fuselage">
                
            </ol>
            <div class="business business--back fuselage">

            </div>
        </div>


        <script src="${jsUrl}"></script>