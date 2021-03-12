<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <link rel="stylesheet" href='<c:url value="/storage/css/Book.css"></c:url>'>
        <c:url value="/storage/js/Book.js" var="jsUrl" />
        <c:url value="/storage/img/bookBG.jpg" var="imgUrl" />

        <style>
            body,
            html {
                height: 100%;
            }
            
            body {
                background-image: url("${imgUrl}");
                /* Full height */
                height: 100%;
                /* Center and scale the image nicely */
                background-position: center;
                background-repeat: no-repeat;
                background-size: cover;
            }
        </style>
        <div class="container ">
            <div class="row" style="margin-top:70px;">
                <div class="col-sm-7">

                    <ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a id="ftab" class="nav-link" onclick="$(this).removeClass('tab_no_select_co');$(this).addClass('tab_select_co');$('#stab').addClass('tab_no_select_co');" data-toggle="tab" href="#home" style="border-color: white white transparent white;">Book
                        your trip</a>
                        </li>
                        <li class="nav-item">
                            <a id="stab" class="nav-link" onclick="$(this).removeClass('tab_no_select_co');$(this).addClass('tab_select_co');$('#ftab').addClass('tab_no_select_co');" data-toggle="tab" href="#menu1" style="border-color: white white transparent transparent;">Manage my
                        bookings</a>
                        </li>
                    </ul>





                    <div class="tab-content" style="border: 1px solid #F7F7F7;border-radius: 0 4px 4px 4px;padding: 40px 10px 60px 10px;background-image: linear-gradient(to right bottom, #f7c4ff, #ffaddf, #ff9ba8, #ff9b64, #ffab00);padding-bottom: 20px;">
                        <div id="home" class="tab-pane fade in active">
                            <form id="flightSearchForm">
                                <div class="form-group">

                                    <div class="form-group chieu">
                                        <form id="testli">
                                            <ul data-tag="channelList" style="list-style-type: none">
                                                <li class="selected" data-id="ow">ONE WAY</li>
                                                <li data-id="rt">ROUND TRIP</li>
                                                <li data-id="md">Multiple Destinations</li>
                                            </ul>
                                        </form>
                                    </div>
                                    <div class="fromto">
                                        <div class="row">
                                            <div class="col">
                                                <div class="form-group">
                                                    <select id="from" name="from" class="form-control">

                                        </select>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="form-group">
                                                    <select id="to" name="to" class="form-control">
          
                                        </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="departureDate">
                                        <div class="row">
                                            <div class="form-group col-sm-6">
                                                <input type="date" class="form-control" id="departureDate" name="departureDate">
                                            </div>
                                            <div class="form-group col-sm-6">
                                                <input type="date" class="form-control" id="ngayden">
                                            </div>
                                        </div>

                                    </div>

                                    <div class="option_passenger&class">
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <div class="btn btn-primary col-12 dropdown mb-3" style=" background-image: linear-gradient(to right top, #eca9ff, #c4b1ff, #95b8ff, #5fbeff,
                      #00c1ff);border-color: white;"><span id="PassengersNum">Passengers</span>
                                                    <div class="dropdown-content">
                                                        <div class="form-group">
                                                            <input min="1" max="9" placeholder="Adult" type="number" value="0" class="form-control" id="adult">
                                                        </div>
                                                        <!-- <div class="form-group">
                                                            <input min="0" max="8" placeholder="Child" type="number" value="0" class="form-control" id="child">
                                                        </div> -->

                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>

                                    <!-- <div class="search"> -->
                                    <div class="form-group mb-4">
                                        <button id="search_btn" class="btn float-right" style="background-image: linear-gradient(to right top, #eca9ff, #c4b1ff, #95b8ff, #5fbeff, #00c1ff);border-color: white;">Search</button>
                                    </div>
                                    <!-- </div> -->

                                </div>

                            </form>
                        </div>

                        <div id="menu1" class="tab-pane fade">
                            <h3>Menu 1</h3>
                            <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="${jsUrl}"></script>