<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <c:url value="/storage/js/AirportManagement.js" var="jsUrl" />


        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <div class="row mt-3 mb-4">
                        <div class="col-lg-3">
                            <select class="form-control" id="loadCity">
                    </select>
                        </div>
                        <div class="col-lg-2">
                            <input type="text" class="form-control" id="airportCode" placeholder="Airport code" name="airportCode">
                        </div>
                        <div class="col-lg-3">
                            <input type="text" class="form-control" id="airportName" placeholder="Airport name" name="airportName">
                        </div>
                        <div class="col-lg-4">
                            <button id="btnAdd" class="btn btn-success">Add Airport</button>
                        </div>
                    </div>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Airport Code</th>
                                            <th>City Name</th>
                                            <th>Airport Name</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbodyData">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <!-- The Modal -->
                    <div class="modal fade" id="updateAirportModal">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">Edit Airport</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>

                                <!-- Modal body -->
                                <div class="modal-body">
                                    <div class="row">
                                        <input class="form-control mt-1" type="text" disabled id="inpAirportID">
                                        <select class="form-control mt-1" id="loadCityMD">
                                        </select>
                                        <input class="form-control mt-1" type="text" id="inpAirportName">
                                    </div>
                                    <button id="btnUpdate" type="button" class="btn btn-success mt-1 float-right">Update</button>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
                <!-- /.container-fluid -->

            </div>
        </div>
        <!-- End of Main Content -->

        <script src="${jsUrl}"></script>