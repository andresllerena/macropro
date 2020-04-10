<%@ page contentType="text/html;charset=ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<title>MacroPro | Create Account</title>
	
	<style>
		.error {
			color: red
		}
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
	<br><br>
	<div id="wrapper">
		<div id="header" class="horizontal-center">
			<h4>Please fill out the form below</h4>
		</div>
	</div>
	<br>
	<div class="horizontal-center">
		<form:form action="${root}/account/addUser" modelAttribute="user" method="POST">
			<table>
				<tbody>
					<tr>
						<td><label>Username*:</label></td>
						<td>&emsp;<form:input path="username" /></td>
						<c:if test="${username_exists != null}"><td>&emsp;<font color="red">${username_exists}</font></td></c:if>
						<td>&emsp;<form:errors path="username" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Password*:</label></td>
						<td>&emsp;<form:password path="password" /></td>
						<c:if test="${password_mismatch != null}"><td>&emsp;<font color="red">${password_mismatch}</font></td></c:if>
						<td>&emsp;<form:errors path="password" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Confirm Password*:</label></td>
						<td>&emsp;<form:password path="matchingPassword" /></td>
						<td>&emsp;<form:errors path="matchingPassword" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>First Name*:</label></td>
						<td>&emsp;<form:input path="firstName" /></td>
						<td>&emsp;<form:errors path="firstName" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Last Name*:</label></td>
						<td>&emsp;<form:input path="lastName" /></td>
						<td>&emsp;<form:errors path="lastName" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Email Address*:</label></td>
						<td>&emsp;<form:input path="email" /></td>
						<c:if test="${invalid_email != null}"><td>&emsp;<font color="red">${invalid_email}</font></td></c:if>
						<c:if test="${email_exists != null}"><td>&emsp;<font color="red">${email_exists}</font></td></c:if>
						<td>&emsp;<form:errors path="email" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Phone Number:</label></td>
						<td>&emsp;<form:input path="phoneNumber" /></td>
						<td>&emsp;<form:errors path="phoneNumber" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Country:</label></td>
						<td>&emsp;<form:input path="country" /></td>
						<td>&emsp;<form:errors path="country" cssClass="error" /></td>
					</tr>
				</tbody>
			</table>
			
			<br>
			<div class="horizontal-center">
				<input type="submit" value="Submit" />
			</div>
		</form:form>
	</div>
	<br>
	<div class="horizontal-center">
		<a href="${pageContext.request.contextPath}/">Back</a>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</body>

</html>