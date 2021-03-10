<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <c:url value="/storage/js/ResultFlight.js" var="jsUrl"></c:url>
        <style>
            .modal-dialog {
                overflow-y: initial !important
            }
            
            .modal-body {
                height: 400px;
                overflow-y: auto;
            }
        </style>
        <div class="container mt-5">
            <h1 id="testt"></h1>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Flight</th>
                        <th>Departure</th>
                        <th>Arrive</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="tbodyData">

                </tbody>
            </table>
            <!-- The Modal -->
            <div class="modal fade" id="myModal">
                <div class="modal-dialog modal-lg" style="overflow-y: scroll; max-height:60%;  margin-top: 50px; margin-bottom:50px;">
                    <div class="modal-content">
                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h2 class="modal-title">BOOK TICKET</h2>

                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <!-- Modal body -->
                        <div class="modal-body">
                            <div id="demo" class="carousel slide" data-ride="carousel">
                                <!-- The slideshow -->
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <h3>Flight Detail</h3>
                                        <table class="table">
                                            <tbody id="tbodyModalData">

                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="carousel-item">

                                        <div class="col-sm-12">

                                            <div class="form-group">
                                                <h6>Contact Info</h6>
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <input class="form-control" type="number" placeholder="Phone Number">
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <input class="form-control" type="email" placeholder="Email">
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="form-group ">
                                                <h4>PASSENGER DETAILS</h4>
                                                <div id="customerData"></div>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="carousel-item">
                                        <div class="row">
                                            <div class="col-2 mx-auto" style="margin-top: 261px;">
                                                <h6>Travel Class Map</h6>
                                            </div>
                                            <div class="col-5 mx-auto">
                                                <jsp:include page="ViewSeat.jsp" />
                                            </div>
                                            <div class="col-5 mx-auto">
                                                <div class="col-10 mx-auto" style="margin-top: 261px; margin-bottom: 50px;">
                                                    <select class="form-control" id="customerListDDL" style="text-align-last: center;">
                                                </select>
                                                </div>
                                                <div id="seatInfo" style="border : 1px solid #ced4da;padding : 10px;height:100px">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" id="btnPrev" href="#demo" data-slide="prev">Prev</button>
                                    <button type="button" class="btn btn-success" id="btnNext" href="#demo" data-slide="next">Next</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <input hidden value="${url}" id="url" name="url" />


                <script src="${jsUrl}">
                </script>