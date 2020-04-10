<%@ page contentType="text/html;charset=ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<security:authentication var="principal" property="principal" />
<security:authentication var="macroplan" property="principal.macroPlan" />

<!DOCTYPE html>
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
					<li id="myhome" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/account/">My Home</a></li>
					<li id="food" class="nav-item"><a class="nav-link" href="#">Food</a></li>
					<li id="about" class="nav-item"><a class="nav-link" href="#">About</a></li>
					<li id="contactus" class="nav-item"><a class="nav-link" href="#">Contact Us</a></li>
				</ul>
			</div>
		</nav>
		
		<nav class="navbar navbar-expand-md subbar">
			<div class="container-fluid">
				<ul class="navbar-nav mx-auto">
					<li id="home" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/account/">Home</a></li>
					<c:if test="${principal.submittedGoal == true}"><li id="goals" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/account/goals">Edit Goal</a></li></c:if>
				</ul>
			</div>
		</nav>
		
		<br><br>
		<div id="main" class="horizontal-center" >
			<div class="horizontal-center">
				<p>
					<b>Welcome ${principal.username}!</b><br>
				</p>
			</div>
			
			<c:if test="${principal.submittedGoal == false}">
				<b>You have not submitted your fitness goals. Please do so 
				<a href="${pageContext.request.contextPath}/account/goals">here</a>!</b>
				<br><br>
			</c:if>
			
			<c:if test="${principal.submittedGoal == true}">
				<b>Below is your daily macro plan:</b><br><br>
				
				<div style="text-align: center;">
					<b><u>Target calories:</u> ${macroplan.targetCalories}</b><br>
					<b><u>Carbs(g):</u> ${macroplan.carbsGrams}</b><br>
					<b><u>Fat(g):</u> ${macroplan.fatGrams}</b><br>
					<b><u>Protein(g):</u> ${macroplan.proteinGrams}</b><br><br>
				</div>
				
				<!-- <div class="horizontal-center">
					<a href="${pageContext.request.contextPath}/account/goals">Edit</a>
				</div> 
				<br> -->
			</c:if>
		</div>
		
		<br>
		<div id="submit-btn" class="horizontal-center" >
			<form:form action="${pageContext.request.contextPath}/logout" method="POST">
				<input type="submit" value="Logout" />
			</form:form>
		</div>
		<!-- <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script> -->
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	</body>
	
</html>