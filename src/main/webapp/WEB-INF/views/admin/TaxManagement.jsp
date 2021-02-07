<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">
    <!-- Main Content -->
    <div id="content">
        <!-- Begin Page Content -->
        <div class="container-fluid">
            
            <div class="row mt-3 mb-4">
                <div class="col-lg-3">
                    <input type="text" class="form-control" id="taxName" placeholder="Tax name" name="taxName">
                </div>
                <div class="col-lg-5">
                    <input type="text" class="form-control" id="taxPrice" placeholder="Tax price" name="taxPrice">
                </div>
                <div class="col-lg-4">
                    <button class="btn btn-success">Add Tax</button>
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
                                    <th>Tax Code</th>
                                    <th>City Name</th>
                                    <th>Tax Price</th>
                                    <th>Add Price</th>
                                    <th>Action</th>
                                    <th>Day Time</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>VS1</td>
                                    <td>Ve Sinh</td>
                                    <td>300000</td>
                                    <td><button type="button" class="btn btn-info"><i class="fas fa-edit"></i></button></td>
                                    <td><button type="button" class="btn btn-info"><i class="fas fa-edit"></i></button>
                                        <button type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                                    </td>
                                    <td>2/7/2021</td>

                                </tr>
                                <tr>
                                    <td>VS2</td>
                                    <td>Phu phi</td>
                                    <td>30000</td>
                                    <td><button type="button" class="btn btn-info"><i class="fas fa-edit"></i></button></td>
                                    <td><button type="button" class="btn btn-info"><i class="fas fa-edit"></i></button>
                                        <button type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                                    </td>
                                    <td>2/3/2021</td>
                                </tr>
                                <tr>
                                    <td>VAT0</td>
                                    <td>Gia tri gia tang</td>
                                    <td>100000</td>
                                    <td><button type="button" class="btn btn-info"><i class="fas fa-edit"></i></button></td>
                                    <td><button type="button" class="btn btn-info"><i class="fas fa-edit"></i></button>
                                        <button type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                                    </td>
                                    <td>2/5/2021</td>

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