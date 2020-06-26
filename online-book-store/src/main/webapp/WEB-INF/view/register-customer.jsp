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

  <title>Register</title>

  <!-- Bootstrap Css link -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  

  <!-- Custom styles for this template-->
  <link type="text/css" href="${pageContext.request.contextPath}/css/login-register.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

  <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-5 d-none d-lg-block bg-login-image"></div>
          <div class="col-lg-7">
            <div class="p-5">
              <div class="text-center">
              	<c:choose>
              		<c:when test="${message != null }">
						<h6 class="h4 text-green-900 mb-4" style="color:green;">${message}</h6>
					</c:when>
					<c:otherwise>
						<h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
					</c:otherwise>
				</c:choose>
              </div>
              <form:form class="user" action = "${pageContext.request.contextPath}/register/customer" method="POST" modelAttribute="customerData">
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <form:input type="text" class="form-control form-control-user" path="firstName" placeholder="First Name" />
                  </div>
                  <div class="col-sm-6">
                    <form:input type="text" class="form-control form-control-user" path="lastName" placeholder="Last Name" />
                  </div>
                </div>
                
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <form:input type="email" class="form-control form-control-user" path="email" placeholder="Email Id"/>
                  </div>
                  <div class="col-sm-6">
                    <form:input type="phone" class="form-control form-control-user" path="mobile" placeholder="Mobile Number" />
                    <form:input type="hidden" path="role" value="ROLE_CUSTOMER"/>
                  </div>
                </div>
                <div class="form-group">
                  <form:input type="text" class="form-control form-control-user" path="address" placeholder="Address"/>
                </div>
                <div class="form-group">
                  <form:input type="text" class="form-control form-control-user" path="username" placeholder="Username"/>
                </div>
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <form:input type="password" class="form-control form-control-user" path="password" placeholder="Enter Password"/>
                  </div>
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <form:input type="password" class="form-control form-control-user" path="confirmPassword" placeholder="Confirm Password"/>
                  </div>
                </div>
                <input type="submit" value="Register" class="btn btn-primary btn-user btn-block" />
                <hr>
                <a href="${pageContext.request.contextPath}/login" class="btn btn-google btn-user btn-block">
                  <i class="fab fa-google fa-fw"></i> Already a user (Login Here)
                </a>
                
              </form:form>
              <hr>
        
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
