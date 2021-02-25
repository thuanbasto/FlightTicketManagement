<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <c:url value="/storage/js/AirplaneManagement.js" var="jsUrl" />


        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <div class="row mt-3 mb-4">
                        <div class="col-lg-3">
                            <input type="text" class="form-control" id="airplaneCode" placeholder="Airplane code" name="airplaneCode">
                        </div>
                        <div class="col-lg-5">
                            <input type="text" class="form-control" id="airplaneName" placeholder="Airplane name" name="airplaneCode">
                        </div>
                        <div class="col-lg-4">
                            <button id="btnAdd" class="btn btn-success">Add Airplane</button>
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
                                            <th>Airplane Code</th>
                                            <th>Airplane Name</th>
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
                    <div class="modal fade" id="updateAirplaneModal">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">Edit Airplane</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>

                                <!-- Modal body -->
                                <div class="modal-body">
                                    <div class="row">
                                        <input class="form-control mt-1" type="text" disabled id="inpAirplaneID">
                                        <input class="form-control mt-1" type="text" id="inpAirplaneName">
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