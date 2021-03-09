<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <c:url value="/signin" var="url" />
        <c:url value="/storage/img/undraw_profile.svg" var="imgUrl"></c:url>

        <style>
            body,
            html {
                height: 100%;
            }
            
            body {
                /* 	background-color: teal; */
                background-image: linear-gradient(to right top, #eca9ff, #c4b1ff, #95b8ff, #5fbeff, #00c1ff);
            }
        </style>
        <script>
            $('title').html('Login Page');
        </script>
        <div class="container">
            <!-- Outer Row -->
            <div class="row justify-content-center">

                <div class="col-xl-10 col-lg-12 col-md-9">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                                <div class="col-lg-6">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                        </div>
                                        <form class="user" action="${url}" method="POST">
                                            <div class="form-group">
                                                <input class="form-control form-control-user" name="username" placeholder="Enter username" autofocus required />
                                            </div>
                                            <div class="form-group">
                                                <input class="form-control form-control-user" name="password" type="password" placeholder="Password" required />
                                            </div>
                                            <p style="color: red">${msg}</p>
                                            <div class="form-group">
                                                <div class="custom-control custom-checkbox small">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck"> <label class="custom-control-label" for="customCheck">Remember
												Me</label>
                                                </div>
                                            </div>
                                            <button class="btn btn-primary btn-user btn-block" id="submit" type="submit" class="btn btn-primary">Sign in</button>
                                        </form>
                                        <hr>
                                        <div class="text-center">
                                            <a class="small" href="forgot-password.html">Forgot
										Password?</a>
                                        </div>
                                        <div class="text-center">
                                            <a class="small" href="register.html">Create an Account!</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>