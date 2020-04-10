<%@ page contentType="text/html;charset=ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
	<title>MacroPro | Getting your nutrition in check!</title>
	<style>
		.horizontal-center{
		  display: table;
		  margin: 0 auto;
		}
	</style>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" >	
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
	<nav class="navbar navbar-expand-md topbar">
		<div class="container-fluid">
			<ul class="navbar-nav mx-auto">
				<li class="nav-item"><a class="nav-link" href="#">About</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Contact Us</a></li>
			</ul>
		</div>
	</nav>
	<br>
	<div style="text-align:center">
		<br>
		<b>MacroPro aims to assist people in their fitness journeys with the best and most accurate macro guides,<br>
		along with our user-friendly daily nutrition logger!</b>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</body>
</html>