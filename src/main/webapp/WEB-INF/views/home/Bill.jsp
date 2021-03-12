<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <link rel="stylesheet" href='<c:url value="/storage/css/Bill.css"></c:url>'>
        <c:url value="/storage/js/Bill.js" var="jsUrl" />
        <input hidden value="${url}" id="url" name="url" />
        <!--Author      : @arboshiki-->
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
                                <a target="_blank" href="https://lobianijs.com">
                                    <img src="http://lobianijs.com/lobiadmin/version/1.0/ajax/img/logo/lobiadmin-logo-text-64.png" data-holder-rendered="true" />
                                </a>
                            </div>
                            <div class="col company-details">
                                <h2 class="name">
                                    <a target="_blank" href="https://lobianijs.com">
                            Arboshiki
                            </a>
                                </h2>
                                <div>455 Foggy Heights, AZ 85004, US</div>
                                <div>(123) 456-789</div>
                                <div>company@example.com</div>
                            </div>
                        </div>
                    </header>
                    <main>
                        <div class="row contacts">
                            <!-- <div class="col invoice-to">
                                <div class="text-gray-light">INVOICE TO:</div>
                                <h2 class="to">John Doe</h2>
                                <div class="address">796 Silver Harbour, TX 79273, US</div>
                                <div class="email"><a href="mailto:john@example.com">john@example.com</a></div>
                            </div>
                            <div class="col invoice-details">
                                <h1 class="invoice-id">INVOICE 3-2-1</h1>
                                <div class="date">Date of Invoice: 01/10/2018</div>
                                <div class="date">Due Date: 30/10/2018</div>
                            </div> -->
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
                                    <td class="no">${element.ticket_Id}</td>
                                    <td>
                                        <table class="table">
                                            <tbody>
                                                <tr>
                                                    <td><b>Danang International Airport</b></td>
                                                    <td><b>Noi Bai</b></td>
                                                    <td><b>4-atom 1</b></td>
                                                </tr>
                                                <tr>
                                                    <td><b>2021-03-10 00:00:00</b></td>
                                                    <td><b>2021-03-10 12:00:00</b></td>
                                                    <td><b>A001 - Pho thong 123</b></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <hr>

                                        <div id="ticketData">

                                        </div>
                                    </td>
                                    <td class="total">
                                        <div class="row">
                                            <!-- <div class="col-sm-12"> -->
                                            <h5 style="text-align: left;">Booking Information</h5>
                                            <table class="table">
                                                <tbody>
                                                    <tr>
                                                        <td><b>Booking Date</b></td>
                                                        <td>2000-01-01 00:00:00</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Phone:</b></td>
                                                        <td>011111</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Email:</b></td>
                                                        <td>thuana@gmail.com</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Payment Method:</b></td>
                                                        <td>ONLINE</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Number of Ticket:</b></td>
                                                        <td>1</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Total Booking Price:</b></td>
                                                        <td>2000</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <!-- </div> -->
                                        </div>
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