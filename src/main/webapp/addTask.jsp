<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<!--
	Author: Ilia G. Bravard
-->

<html lang="en">
<head>
<meta charset="ISO-8859-1" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Add A Task!</title>

<link rel="icon" href="Image1.png" type="image/x-icon" />

<style>
body {
	background-image: url("Image10.png");
	background-size: cover;
	background-repeat: no-repeat;
	font-family: "montserrat";
}

p {
	color: white;
	font-size: 20px;
}

.container {
	display: flex;
	justify-content: center;
	width: 300px;
	padding-left: 160px;
	padding-top: 85px;
}

form {
	text-align: center;
}

form input[type="text"] {
	border: 0;
	margin-left: 17px;
	text-align: center;
	border: 2px solid white;
	background: transparent;
	width: 300px;
	color: white;
	font-size: 18px;
	padding: 7px;
	border-radius: 25px;
	transition: 0.25s;
}

form input[type="number"], input[type="date"] {
	margin-left: 17px;
	padding: 7px;
	text-align: center;
	border: 2px solid white;
	background: transparent;
	width: 240px;
	color: white;
	font-size: 18px;
	border-radius: 25px;
	transition: 0.25s;
}

form input[type="text"]:focus, form input[type="number"]:focus {
	width: 320px;
	border-color: rgb(144, 229, 115);
	outline: none;
}

form input[type="date"]:focus, select:focus {
	border-color: rgb(144, 229, 115);
	outline: none;
}

form input[type="submit"] {
	border: 0;
	margin: 15px auto;
	text-align: center;
	border: 2px solid white;
	background: transparent;
	width: 200px;
	padding: 14px 10px;
	color: white;
	font-size: 15px;
	border-radius: 25px;
	cursor: pointer;
}

form input[type="submit"]:hover {
	background: #ac9187;
	border-style: none;
	border-color: rgb(247, 212, 138);
	font-size: 20px;
	transition: all 0.6s ease;
	color: white;
}

form select {
	color: white;
	border: 2px solid white;
	background-color: transparent;
	border-radius: 10px;
	decoration: none;
	width: 165px;
	font-size: 13px;
	overflow-y: auto;
	text-align: center;
}

::-webkit-calendar-picker-indicator {
	filter: invert(1);
}
</style>
</head>
<body>
	<div class="container">
		<form action="usersServlet" method="post" autocomplete="off">
			<p>Task Details:</p>
			<input required type="text" name="details" id="seasonNum" />

			<p>Due Date:</p>
			<input required type="date" name="date" id="date" />

			<p>Available Users:</p>
			<select required name="userSelected" size="5">
				<c:forEach items="${requestScope.allUsers}" var="currentuser">
					<option value="${currentuser.userID}">${currentuser.firstName}
						${currentuser.lastName}</option>
				</c:forEach>
			</select><br> <input type="submit" name="doThisToUser" value="Schedule"
				class="submit" />
		</form>
	</div>

	<!-- Script that handles what due date the user of the application can choose for a given assigned task. 
		 The user can choose only dates that are >= to today's date. 
	-->
	<script>
		const ADJUST_MONTH = 1 // To avoid magic numbers
		let date = new Date();
		let tDate = date.getDate();
		let tMonth = date.getMonth() + ADJUST_MONTH;

		if (tDate < 10) {
			tDate = '0' + tDate;
		}

		if (tMonth < 10) {
			tMonth = '0' + tMonth;
		}

		let tYear = date.getUTCFullYear();
		let minDate = tYear + '-' + tMonth + '-' + tDate;

		document.querySelector("#date").setAttribute('min', minDate);
	</script>
</body>
</html>