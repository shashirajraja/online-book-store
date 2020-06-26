<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">


<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Login</title>
  
  <!-- Bootstrap Css link -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  

  <!-- Custom styles for this template-->
  <link type="text/css" href="${pageContext.request.contextPath}/css/login-register.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

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
                  <c:choose>
                  	<c:when test="${param.error !=null }">
						<h6 class="h4 text-red-900 mb-4" style="color:red;">Wrong username/password</h6>
					</c:when>
					<c:when test="${param.logout !=null }">
						<h6 class="h4 text-green-900 mb-4" style="color:green;">Logged Out Successfully!</h6>
					</c:when>
					<c:when test="${message != null }">
						<h6 class="h4 text-green-900 mb-4" style="color:green;">${message}</h6>
					</c:when>
					<c:otherwise>
                    	<h3 class="h4 text-grey-900 mb-4">Welcome Back!</h3>
                  	</c:otherwise>
                  	</c:choose>
                  </div>
                  <form:form class="user" action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" placeholder="Enter Email Address or username" name="username">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" placeholder="Enter Password" name="password">
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="customCheck">
                        <label class="custom-control-label" for="customCheck">Remember Me</label>
                      </div>
                    </div>
                    <input type="submit" class="btn btn-primary btn-user btn-block" value="Login" />
                    <hr>
                    <a href="${pageContext.request.contextPath}/login?error" class="btn btn-google btn-user btn-block">
                      <i class="fab fa-google fa-fw"></i> forgot Password?
                    </a>
                    <a href="${pageContext.request.contextPath}/register" class="btn btn-facebook btn-user btn-block">
                      <i class="fab fa-facebook-f fa-fw"></i> Create an Account!
                    </a>
                  </form:form>
                  <hr>
                  <div class="text-center">
                    <a class="small" href="${pageContext.request.contextPath}/register">Register</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>

    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>

</html>
