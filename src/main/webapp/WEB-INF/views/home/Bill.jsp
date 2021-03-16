<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <link rel="stylesheet" href='<c:url value="/storage/css/Bill.css"></c:url>'>
        <c:url value="/storage/js/Bill.js" var="jsUrl" />
        <c:url value="/book" var="homeUrl" />
        <c:url value="/storage/img/logo.png" var="iconUrl" />
        <input hidden value="${url}" id="url" name="url" />

        <div id="invoice">
            <div class="toolbar hidden-print">
                <div class="text-right">
                    <button id="printInvoice" class="btn btn-info"><i class="fa fa-print"></i> Print</button>
                    <button class="btn btn-info"><i class="fa fa-file-pdf-o"></i> Export as PDF</button>
                </div>
                <hr>
            </div>
            <div class="invoice overflow-auto">
                <div style="min-width: 600px">
                    <header>
                        <div class="row">
                            <div class="col">
                                <a target="_blank" href="${homeUrl}">
                                    <img src="${iconUrl}" width="120px" height="100px" data-holder-rendered="true" />
                                </a>
                            </div>
                            <div class="col company-details">
                                <h2 class="name">
                                    <a target="_blank" href="${homeUrl}">
                                        Tomcat
                                    </a>
                                </h2>
                                <div>455 ABC, XYZ, VN</div>
                                <div>077 5461 753</div>
                                <div>tomcatflight7@gmail.com</div>
                            </div>
                        </div>
                    </header>
                    <main>
                        <div class="row contacts">
                        </div>
                        <table border="0" cellspacing="0" cellpadding="0">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th class="text-left">DESCRIPTION</th>
                                    <th class="text-right">TOTAL</th>
                                </tr>
                            </thead>
                            <tbody id="tbodyDatas">
                                <tr>
                                    <td class="no"></td>
                                    <td>
                                        <div id="ticketData">

                                        </div>
                                    </td>
                                    <td class="total">
                                        <!-- <div class="row" style="padding: 0 15px;"> -->
                                        <!-- <div class="col-sm-12"> -->
                                        <h5 style="text-align: left;">Booking Information</h5>
                                        <table class="table">
                                            <thead>
                                            </thead>
                                            <tbody id="bookingTotalData">

                                            </tbody>
                                        </table>
                                        <!-- </div> -->
                                        <!-- </div> -->
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </main>
                    <footer>
                        Invoice was created on a computer and is valid without the signature and seal.
                    </footer>
                </div>
                <!--DO NOT DELETE THIS div. IT is responsible for showing footer always at the bottom-->
                <div></div>
            </div>
        </div>

        <script src="${jsUrl}"></script>