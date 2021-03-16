<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <c:url value="/storage/js/ResultFlight.js" var="jsUrl"></c:url>
        <c:url value="/storage/css/ResultFlight.css" var="cssUrl"></c:url>

        <link rel="stylesheet" href="${cssUrl}">
        <style>
            .modal-dialog {
                overflow-y: initial !important
            }
            
            .modal-body {
                height: 400px;
                overflow-y: auto;
            }
        </style>

        <!-- The Modal -->
        <div class="modal fade" id="modalPaymentSuccess">
            <div class="modal-dialog">
                <div class="modal-content" style="width:400px; height:300px;">
                    <!-- Modal Header -->
                    <div class="modal-header text-center">
                        <h4 class="modal-title text-success">Payment success</h4>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body text-center">
                        <h3 class="text-primary">Send email with booking information for you</h3>
                        <h3>Thank you, waiting 3 seconds to redirect to bill page</h3>
                    </div>
                </div>
            </div>
        </div>


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
            <div class="text-center notFound">

            </div>
            <!-- The Modal -->
            <div class="modal fade" id="myModal">
                <div class="modal-dialog modal-lg" style="overflow-y: scroll; max-height:60%; max-width: 80%; margin-top: 50px; margin-bottom:50px;">
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
                                    <div class="firstCarousel carousel-item active">
                                        <h3>Flight Detail</h3>
                                        <table class="table">
                                            <tbody id="tbodyModalData">

                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="secondCarousel carousel-item">
                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <h6>Contact Info</h6>
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <input class="form-control" maxlength="10" id="phone" type="text" placeholder="Phone Number">
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <input class="form-control" id="email" type="email" placeholder="Email">
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
                                    <div class="thirdCarousel carousel-item">
                                        <div class="row">
                                            <div class="col-7 mx-auto">
                                                <jsp:include page="ViewSeat.jsp" />
                                            </div>
                                            <div class="col-5 mx-auto">
                                                <div class="col-10 mx-auto" style="margin-top: 200px; margin-bottom: 50px;">
                                                    <div id="travelClassPriceData"></div>
                                                    <select class="form-control" id="customerListDDL" style="text-align-last: center;">
          
                                                    </select>
                                                </div>
                                                <div id="seatInfo" style="height:100px;border: 1px solid #ced4da;padding: 8px;border-radius: 5px;">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fourCarousel pay carousel-item">
                                        <div></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" hidden class="btn btn-secondary" id="btnPrev" href="#demo" data-slide="prev">Prev</button>
                            <button type="button" hidden class="btn btn-success" id="btnNext" href="#demo" data-slide="next">Next</button>
                            <button type="button" class="btn btn-primary" id="btnPay">Checkout</button>
                            <button type="button" class="btn btn-secondary" id="btnCheckPrev">Prev</button>
                            <button type="button" class="btn btn-success" id="btnCheckNext">Next</button>
                        </div>
                    </div>
                </div>


                <input hidden value="${url}" id="url" name="url" />

                <script src="${jsUrl}"></script>