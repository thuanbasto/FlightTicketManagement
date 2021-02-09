<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/storage/js/CityManagement.js" var="jsUrl"/>

<!-- Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">
	<!-- Main Content -->
	<div id="content">
		<!-- Begin Page Content -->
		<div class="container-fluid">

			<div class="row mt-3 mb-4">
				<div class="col-lg-3">
					<input type="text" class="form-control" id="cityCode"
						placeholder="City Code" name="cityCode">
				</div>
				<div class="col-lg-5">
					<input type="text" class="form-control" id="cityName"
						placeholder="City name" name="cityName">
				</div>
				<div class="col-lg-4">
					<button class="btn btn-success">Add City</button>
				</div>
			</div>

			<!-- DataTales Example -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">DataTables
						Example</h6>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>City Code</th>
									<th>City Name</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>HN</td>
									<td>Ha Noi</td>
									<td><button type="button" class="btn btn-info">
											<i class="fas fa-edit"></i>
										</button>
										<button type="button" class="btn btn-danger">
											<i class="fas fa-trash-alt"></i>
										</button></td>
								</tr>
								<tr>
									<td>DNg</td>
									<td>Da Nang</td>
									<td><button type="button" class="btn btn-info">
											<i class="fas fa-edit"></i>
										</button>
										<button type="button" class="btn btn-danger">
											<i class="fas fa-trash-alt"></i>
										</button></td>
								</tr>
								<tr>
									<td>HCM</td>
									<td>Ho Chi Minh</td>
									<td><button type="button" class="btn btn-info">
											<i class="fas fa-edit"></i>
										</button>
										<button type="button" class="btn btn-danger">
											<i class="fas fa-trash-alt"></i>
										</button></td>
								</tr>
								<tr>
									<td>HCM</td>
									<td>Ho Chi Minh</td>
									<td><button type="button" class="btn btn-info">
											<i class="fas fa-edit"></i>
										</button>
										<button type="button" class="btn btn-danger">
											<i class="fas fa-trash-alt"></i>
										</button></td>
								</tr>
								<tr>
									<td>HCM</td>
									<td>Ho Chi Minh</td>
									<td><button type="button" class="btn btn-info">
											<i class="fas fa-edit"></i>
										</button>
										<button type="button" class="btn btn-danger">
											<i class="fas fa-trash-alt"></i>
										</button></td>
								</tr>
								<tr>
									<td>HCM</td>
									<td>Ho Chi Minh</td>
									<td><button type="button" class="btn btn-info">
											<i class="fas fa-edit"></i>
										</button>
										<button type="button" class="btn btn-danger">
											<i class="fas fa-trash-alt"></i>
										</button></td>
								</tr>
								<tr>
									<td>HCM</td>
									<td>Ho Chi Minh</td>
									<td><button type="button" class="btn btn-info">
											<i class="fas fa-edit"></i>
										</button>
										<button type="button" class="btn btn-danger">
											<i class="fas fa-trash-alt"></i>
										</button></td>
								</tr>
								<tr>
									<td>HCM</td>
									<td>Ho Chi Minh</td>
									<td><button type="button" class="btn btn-info">
											<i class="fas fa-edit"></i>
										</button>
										<button type="button" class="btn btn-danger">
											<i class="fas fa-trash-alt"></i>
										</button></td>
								</tr>
								<tr>
									<td>HCM</td>
									<td>Ho Chi Minh</td>
									<td><button type="button" class="btn btn-info">
											<i class="fas fa-edit"></i>
										</button>
										<button type="button" class="btn btn-danger">
											<i class="fas fa-trash-alt"></i>
										</button></td>
								</tr>
								<tr>
									<td>HCM</td>
									<td>Ho Chi Minh</td>
									<td><button type="button" class="btn btn-info">
											<i class="fas fa-edit"></i>
										</button>
										<button type="button" class="btn btn-danger">
											<i class="fas fa-trash-alt"></i>
										</button></td>
								</tr>
								<tr>
									<td>HCM</td>
									<td>Ho Chi Minh</td>
									<td><button type="button" class="btn btn-info">
											<i class="fas fa-edit"></i>
										</button>
										<button type="button" class="btn btn-danger">
											<i class="fas fa-trash-alt"></i>
										</button></td>
								</tr>
								<tr>
									<td>HCM</td>
									<td>Ho Chi Minh</td>
									<td><button type="button" class="btn btn-info">
											<i class="fas fa-edit"></i>
										</button>
										<button type="button" class="btn btn-danger">
											<i class="fas fa-trash-alt"></i>
										</button></td>
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