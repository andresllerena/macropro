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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/toolbar.js"></script>
</head>
<body>
		<div id="mp-header">
			<div id="mp-logo" class="horizontal-center"><img src="${pageContext.request.contextPath}/resources/images/logo.jpg" width="200" height="120" /></div>
			<br>
		</div>	
		
		<nav class="navbar navbar-expand-md topbar">
			<div class="container-fluid">
				<ul class="navbar-nav mx-auto">
					<li id="account" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/account/">My Home</a></li>
					<li id="food" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/food/diary">Food</a></li>
					<li id="about" class="nav-item"><a class="nav-link" href="#">About</a></li>
					<li id="contactus" class="nav-item"><a class="nav-link" href="#">Contact Us</a></li>
				</ul>
			</div>
		</nav>
		
		<nav class="navbar navbar-expand-md subbar">
			<div class="container-fluid">
				<ul class="navbar-nav mx-auto">
					<li id="diary" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/food/diary">Food Diary</a></li>
					<li id="search-foods" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/food/search-foods">Database</a></li>
					<li id="my-foods" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/food/my-foods">My Foods</a></li>
					<li id="my-meals" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/food/my-meals">My Meals</a></li>
					<li id="diary-settings" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/account/diary-settings">Settings</a></li>
				</ul>
			</div>
		</nav>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>