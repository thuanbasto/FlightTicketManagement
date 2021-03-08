<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <!-- Page Wrapper -->
        <div id="wrapper">
            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="dashboard">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Admin</div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item active">
                    <a class="nav-link" href="dashboard">
                        <i class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span>
                    </a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">


                <!-- Heading -->
                <div class="sidebar-heading">Administration</div>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                        aria-expanded="true" aria-controls="collapsePages"> <i class="fas fa-fw fa-folder"></i>
                        <span>Management</span>
                    </a>
                    <div id="collapsePages" class="collapse" aria-labelledby="headingPages"
                        data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Booking:</h6>
                            <a class="collapse-item" href="booking-management" style="color: cornflowerblue">Booking</a>
                            <h6 class="collapse-header">Price:</h6>
                            <a class="collapse-item" href="tax-management" style="color: cornflowerblue">Tax</a>
                            <a class="collapse-item" href="#">Tiket</a>
                            <a class="collapse-item" href="luggage-management" style="color: cornflowerblue">Signed Lugguage</a>
                            <a class="collapse-item" href="flight-management" style="color: cornflowerblue">Flight</a>
                            <a class="collapse-item" href="travelclass-management" style="color: cornflowerblue">Travel Class</a>
                            <div class="collapse-divider"></div>
                            <h6 class="collapse-header">Location:</h6>
                            <a class="collapse-item" href="city-management" style="color: cornflowerblue">City</a>
                            <a class="collapse-item" href="airplane-management" style="color: cornflowerblue">Airplane</a>                          
                            <h6 class="collapse-header">Air plane:</h6>
                            <a class="collapse-item" href="seat-management" style="color: cornflowerblue">Seat</a>
                            <a class="collapse-item" href="airport-management" style="color: cornflowerblue">Airport</a>
                            <h6 class="collapse-header">User and customer:</h6>
                            <a class="collapse-item" href="user-management" style="color: cornflowerblue">User</a>
                            <a class="collapse-item" href="customer-management" style="color: cornflowerblue">Customer</a>
                        </div>
                    </div>
                </li>

                
                <!-- Nav Item - Tables -->
                <li class="nav-item">
                    <a class="nav-link" href="revenue"> <i class="fas fa-fw fa-table"></i> <span>Revenue</span></a>
                </li>
                
                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="#"> <i class="fas fa-fw fa-chart-area"></i> <span>Charts</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>


            </ul>
            <!-- End of Sidebar -->