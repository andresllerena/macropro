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
			<div id="mp-logo" class="horizontal-center"><img src="${pageContext.request.contextPath}/resources/images/logo.jpg" width="200" height="120" /></div>
			<br>
		</div>	
		
		<nav class="navbar navbar-expand-md topbar">
			<div class="container-fluid">
				<ul class="navbar-nav mx-auto">
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/account/">My Home</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/food/diary">Food</a></li>
					<li class="nav-item"><a class="nav-link" href="#">About</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Contact Us</a></li>
				</ul>
			</div>
		</nav>
		
		<nav class="navbar navbar-expand-md subbar">
			<div class="container-fluid">
				<ul class="navbar-nav mx-auto">
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/account/">Home</a></li>
					<c:if test="${principal.submittedGoal == true}"><li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/account/goals">Edit Goal</a></li></c:if>
				</ul>
			</div>
		</nav>
</body>
</html>