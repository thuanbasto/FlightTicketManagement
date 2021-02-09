<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/storage/js/AirportManagement.js" var="jsUrl"/>


<!-- Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">
    <!-- Main Content -->
    <div id="content">
        <!-- Begin Page Content -->
        <div class="container-fluid">

            <div class="row mt-3 mb-4">
                <div class="col-lg-3">
                    <select class="form-control" id="sel1" name="sellist1">
                        <option value="HCM">Ho Chi Minh</option>
                        <option value="HN">Ha Noi</option>
                        <option value="DNg">Da Nang</option>
                        <option value="NT">Nha Trang</option>
                    </select>
                </div>
                <div class="col-lg-5">
                    <input type="text" class="form-control" id="email" placeholder="Airport name" name="airportName">
                </div>
                <div class="col-lg-4">
                    <button class="btn btn-success">Add Airport</button>
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
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Ha Noi</td>
                                    <td>Noi Bai</td>
                                    <td><button type="button" class="btn btn-info"><i class="fas fa-edit"></i></button>
                                        <button type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Ho Chi Minh</td>
                                    <td>Tan Son Nhat</td>
                                    <td><button type="button" class="btn btn-info"><i class="fas fa-edit"></i></button>
                                        <button type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Da Nang(DAd)</td>
                                    <td>Da Nang</td>
                                    <td><button type="button" class="btn btn-info"><i class="fas fa-edit"></i></button>
                                        <button type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container-fluid -->

    </div>
</div>
<!-- End of Main Content -->

<script src="${jsUrl}"></script>