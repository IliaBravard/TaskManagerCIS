<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<!--
	Author: Ilia G. Bravard
-->

<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="icon" href="Image1.png" type="image/x-icon" />

<title>All Users</title>
</head>
<body>
	<h2 style="margin: 25px; text-align: center;">List of Users</h2>
	<div class="container">
		<form method="post" action="usersServlet">
			<table class="table table-striped table-hover">
				<tr>
					<th>User's ID#</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Tasks</th>
				</tr>
				<c:forEach items="${requestScope.allUsers}" var="currentuser">
					<tr>
						<td>${currentuser.userID}</td>
						<td>${currentuser.firstName}</td>
						<td>${currentuser.lastName}</td>
						<td>${currentuser.emailAddress}</td>
						<td>${currentuser.taskID}</td>
					</tr>
				</c:forEach>
				<tr>
					<td><strong>Total Users: ${numOfUsers}</strong></td>
				</tr>
			</table>
			<input type="submit" value="sort" name="doThisToUser"
				class="btn btn-warning"> <input type="submit" value="delete"
				name="doThisToUser" class="btn btn-danger"> <a
				href="exit.html"><input type="button" value="exit"
				name="doThisToUser" class="btn btn-info"></a> <a
				href="index.html"><input type="button" value="add"
				class="btn btn-success"></a>
		</form>

		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
			integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous"></script>
	</div>
</body>
</html>