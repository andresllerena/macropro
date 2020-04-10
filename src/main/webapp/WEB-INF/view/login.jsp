<%@ page contentType="text/html;charset=ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>MacroPro | Login</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<!-- Reference Bootstrap files -->
<!-- 	<link rel="stylesheet"
			 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
		<style>
			.horizontal-center{
			  display: table;
			  margin: 0 auto;
			}
			.horizontal-center-two{
			  margin: 0 auto;
			}
			.btn {
			    width:100px;
			}
			.glyphicon.glyphicon-home {
				margin: 0 auto;
			    width: 100px;
			    white-space: nowrap;
			    font-size:25px;
			}
		</style>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" >
		<link href="${pageContext.request.contextPath}/resources/css/login-style.css" rel="stylesheet" >
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
	</head>
	<body>
		<div id="mp-header">
			<div id="mp-logo" class="horizontal-center"><a href="${pageContext.request.contextPath}/" ><img src="${pageContext.request.contextPath}/resources/images/logo.jpg" width="200" height="120" /></a></div>
			<div id="links" class="horizontal-center">
				<p style='padding-top: 20px'>
					<a class="welcome-link" href="${pageContext.request.contextPath}/account/login">Log In</a> &nbsp;&nbsp;|&nbsp;&nbsp;
					<a class="welcome-link" href="${pageContext.request.contextPath}/account/signup">Sign Up</a>
				</p>
			</div>
		</div>
		<div>
		<nav class="navbar navbar-expand-md topbar" >
			<div class="container-fluid">
				<ul class="navbar-nav mx-auto">
					<li class="nav-item"><a class="nav-link" href="#">About</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Contact Us</a></li>
				</ul>
			</div>
		</nav>
		
		<div class="row login-body">
			<div class="container">
			<div class="modal-dialog text-center">
			    <div class="col-sm-9 main-section">
					<div class="modal-content">
					
						<div class="col-12 user-img">
						  <img src="${pageContext.request.contextPath}/resources/images/face.png">
						</div>
					
						<div class="align-items-center">
							<c:if test="${message != null}">	            
								<div class="alert alert-success col-xs-offset-1">
									${message}
								</div>
							</c:if>
							
							<c:if test="${param.error != null}">	            
								<div class="alert alert-danger col-xs-offset-1">
									Invalid username and/or password.
								</div>
							</c:if>
							
							<c:if test="${param.logout != null}">	            
								<div class="alert alert-success col-xs-offset-1">
									You have been logged out.
								</div>
						    </c:if>
						</div>
					
						<div class="col-12 form-input">
							<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST" class="form-horizontal">
							   	<div class="form-group has-feedback has-feedback-left">
							     	<input type="text" name="username" class="form-control" placeholder="Username">
    								<span class="glyphicon glyphicon-calendar form-control-feedback" aria-hidden="true"></span>
							   	</div>
							 	<div class="form-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							    	<input type="password" name="password" class="form-control" placeholder="Password">
							 	</div>
							 	<button type="submit" class="btn btn-success">Login</button>
							 </form:form>
						</div>
						
						<div class="col-12 forgot">
						  <a href="#">Forgot Password?</a>
						</div>
					
					</div>
				</div>
				</div>
			</div>
		</div>
		</div>
<%-- 		<section class="container-fluid">
			<section class="row justify-content-center">
				<section class="col-12 col-sm-6 col-md-3">
					<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST" class="form-horizontal">
						<div class="form-group">
						  <label for="exampleInputEmail1">Email address</label>
						  <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
						  <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
						</div>
						<div class="form-group">
						  <label for="exampleInputPassword1">Password</label>
						  <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
						</div>
						<div class="form-check">
						  <input type="checkbox" class="form-check-input" id="exampleCheck1">
						  <label class="form-check-label" for="exampleCheck1">Check me out</label>
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form:form>
				</section>
			</section>
		</section> --%>
		
<%-- 		<div>
			<div id="loginbox" style="margin-top: 45px;"
				class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2 justify-content-center">
				
				<div><a href="${pageContext.request.contextPath}/"><span title="Home"><i class="glyphicon glyphicon-home"></i></span></a></div>
				<br>
				
				<div class="panel panel-info">
					<div class="panel-heading">
						<center><div class="panel-title">Sign In</div></center>
					</div>
	
					<div style="padding-top: 30px" class="panel-body">
						<!-- Login Form -->
						<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST" class="form-horizontal">
						    <!-- Place for messages: error, alert etc ... -->
						    <div class="form-group">
						        <div class="col-md-15">
						            <div>
										<c:if test="${message != null}">	            
											<div class="alert alert-success col-md-offset-1 col-md-10">
												${message}
											</div>
										</c:if>
										
										<c:if test="${param.error != null}">	            
											<div class="alert alert-danger col-md-offset-1 col-md-10">
												Invalid username and password.
											</div>
										</c:if>
										
										<c:if test="${param.logout != null}">	            
											<div class="alert alert-success col-md-offset-1 col-md-10">
												You have been logged out.
											</div>
									    </c:if>
	
						            </div>
						        </div>
						    </div>
	
							<!-- User name -->
							<div style="margin-bottom: 40px" class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
								
								<input type="text" name="username" placeholder="username" class="form-control">
							</div>
	
							<!-- Password -->
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
								
								<input type="password" name="password" placeholder="password" class="form-control" >
							</div>
	
							<!-- Login/Submit Button -->
							<div class="center-div">
							<div style="margin-top: 10px" class="form-group">						
								<div class="col-sm-6 controls">
									<button type="submit" class="btn btn-success">Login</button>
								</div>
							</div></div>
						</form:form>
						
	    				<div class="center-div"><a href="${pageContext.request.contextPath}/account/signup">Create Account</a></div>
	
					</div>
				</div>
			</div>
		</div> --%>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		
	</body>
</html>