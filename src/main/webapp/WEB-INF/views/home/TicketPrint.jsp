<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href='<c:url value="/storage/css/TicketPrint.css"></c:url>'>


	<div class="box">
		<ul class="left">
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		</ul>
		
		<ul class="right">
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		  <li></li>
		</ul>
		<div class="ticket">
		  <span class="airline">ATOM</span>
		  <span class="airline airlineslip">ATOM</span>
		  <span class="boarding">Boarding pass</span>
		  <div class="content">
			<span class="jfk">${ticketDTO.flight.fromAirport.airport_Id}</span>
			<span class="plane"><?xml version="1.0" ?><svg clip-rule="evenodd" fill-rule="evenodd" height="60" width="60" image-rendering="optimizeQuality" shape-rendering="geometricPrecision" text-rendering="geometricPrecision" viewBox="0 0 500 500" xmlns="http://www.w3.org/2000/svg"><g stroke="#222"><line fill="none" stroke-linecap="round" stroke-width="30" x1="300" x2="55" y1="390" y2="390"/><path d="M98 325c-9 10 10 16 25 6l311-156c24-17 35-25 42-50 2-15-46-11-78-7-15 1-34 10-42 16l-56 35 1-1-169-31c-14-3-24-5-37-1-10 5-18 10-27 18l122 72c4 3 5 7 1 9l-44 27-75-15c-10-2-18-4-28 0-8 4-14 9-20 15l74 63z" fill="#222" stroke-linejoin="round" stroke-width="10"/></g></svg></span>
			<span class="sfo">${ticketDTO.flight.toAirport.airport_Id}</span>
			
			<span class="jfk jfkslip">${ticketDTO.flight.fromAirport.airport_Id}</span>
			<span class="plane planeslip"><?xml version="1.0" ?><svg clip-rule="evenodd" fill-rule="evenodd" height="50" width="50" image-rendering="optimizeQuality" shape-rendering="geometricPrecision" text-rendering="geometricPrecision" viewBox="0 0 500 500" xmlns="http://www.w3.org/2000/svg"><g stroke="#222"><line fill="none" stroke-linecap="round" stroke-width="30" x1="300" x2="55" y1="390" y2="390"/><path d="M98 325c-9 10 10 16 25 6l311-156c24-17 35-25 42-50 2-15-46-11-78-7-15 1-34 10-42 16l-56 35 1-1-169-31c-14-3-24-5-37-1-10 5-18 10-27 18l122 72c4 3 5 7 1 9l-44 27-75-15c-10-2-18-4-28 0-8 4-14 9-20 15l74 63z" fill="#222" stroke-linejoin="round" stroke-width="10"/></g></svg></span>
			<span class="sfo sfoslip">${ticketDTO.flight.toAirport.airport_Id}</span>
			<div class="sub-content">
			  <span class="watermark">TomCat</span>
			  <span class="name">PASSENGER NAME<br><span>${ticketDTO.customer.firstName} ${ticketDTO.customer.lastName}</span></span>
			  <span class="flight">FLIGHT<br><span>${ticketDTO.flight.airplane.name}</span></span>
			  <span class="seat">SEAT<br><span>${ticketDTO.seat.seat_Id}</span></span>
			  <span class="boardingtime">BOARDING TIME<br><span></span>${ticketDTO.flight.departureDate}</span>
				  
			   <span class="flight flightslip">FLIGHT<br><span>${ticketDTO.flight.airplane.name}</span></span>
				<span class="seat seatslip">SEAT<br><span>${ticketDTO.seat.seat_Id}</span></span>
			   <span class="name nameslip">PASSENGER NAME<br><span>${ticketDTO.customer.firstName} ${ticketDTO.customer.lastName}</span></span>
			</div>
		  </div>
		  <div class="barcode"></div>
		  <div class="barcode slip"></div>
		</div>
	  </div>

