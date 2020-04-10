<%@ page contentType="text/html;charset=ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="root" value="${pageContext.request.contextPath}"/>

<security:authentication var="principal" property="principal" />
<security:authentication var="goal" property="principal.goal" />

<!DOCTYPE html>
<html lang="en">
<head>
	<style>
		.error {
			color: red
		}
		.horizontal-center{
		  display: table;
		  margin: 0 auto;
		}
		.input-size{
			display: inline-block;
			width: 75px;
		}
		.input-size-text{
			display: inline-block;
			width: 73px;
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
				<li id="home" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/account/">Home</a></li>
				<c:if test="${principal.submittedGoal == true}"><li id="goals" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/account/goals">Edit Goal</a></li></c:if>
			</ul>
		</div>
	</nav>
	
	<br><br>
	<div id="wrapper">
		<div id="header" class="horizontal-center">
			<h4>Your Goal Information</h4>
		</div>
	</div>
	<br>
	
	<div class="horizontal-center">
		<form:form action="${root}/account/addUserGoal" modelAttribute="usergoal" method="POST">
			<table>	
				<col align="left">
				<col align="left">
				<col align="left">
				<tbody>
					<tr>
						<td><label>Gender*:</label></td>
						<td>&emsp;
							<form:select path="gender" cssClass="input-size" >
								<form:option value=" " label="--- Select ---"/>
								<form:option value="M" label="Male"/>
								<form:option value="F" label="Female"/>
							</form:select>
						</td>
						<td>&emsp;<form:errors path="gender" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Age*:</label></td>
						<td>&emsp;&nbsp;<form:input path="age" cssClass="input-size" /></td>
						<td>&emsp;<form:errors path="age" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Goal*:</label></td>
						<td>&emsp;
							<form:select path="goal" cssClass="input-size">
								<form:option value="" label="--- Select ---"/>
								<form:option value="BULKING" label="Bulking"/>
								<form:option value="CUTTING" label="Cutting"/>
							</form:select>
						</td>
						<td>&emsp;<form:errors path="goal" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Activity Level*:</label></td>
						<td>&emsp;
							<form:select path="activityLevel" cssClass="input-size">
								<form:option value="-1" label="--- Select ---"/>
								<form:options items="${activityLevels}" />
							</form:select>
						</td>
						<td>&emsp;<form:errors path="activityLevel" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Height*:</label></td>
						<td>&emsp;&nbsp;<form:input path="heightFeet" cssClass="input-size" />&nbsp;ft</td>
						<td>&emsp;<form:input path="heightInches" cssClass="input-size" />&nbsp;in&emsp;&emsp;<form:errors path="heightFeet" cssClass="error" />&emsp;<form:errors path="heightInches" cssClass="error" /></td>
						<%-- <td><form:errors path="heightFeet" cssClass="error" />&emsp;<form:errors path="heightInches" cssClass="error" /></td> --%>
					</tr>
					<tr>
						<td><label>Weight (lbs)*:</label></td>
						<td>&emsp;&nbsp;<form:input path="weightPounds" cssClass="input-size" /></td>
						<td>&emsp;<form:errors path="weightPounds" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Diet Preference*:</label></td>
						<td>&emsp;
							<form:select path="fatPercentagePreferrence" cssClass="input-size">
								<form:option value="-1" label="--- Select ---"/>
								<form:option value="20" label="I prefer eating more carbs than fats"/>
								<form:option value="25" label="Neutral"/>
								<form:option value="30" label="I prefer eating more fats than carbs"/>
							</form:select>
						<td>&emsp;<form:errors path="fatPercentagePreferrence" cssClass="error" /></td>
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
		<a href="${pageContext.request.contextPath}/account/">Back</a>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>