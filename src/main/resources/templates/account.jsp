<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Account</title>
</head>
<body>
<h1>
	Account
</h1>

<!-- TODO: This page needs to be implemented -->
<P> This is the account page </P>

	<!-- New/add appointment section -->
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<!--  Appointment header -->
				<h1 id="appointmentHeader">Account Page</h1>
				<form action="/newAppointment.pl" th:object="${appointment}"
					onsubmit="return newAppointment()" method="post">
					<div id="#newCancelButtonDiv" class="clearfix">
						<input id="newButton" type="submit" class="btn btn-primary col-md-2 clearfix" value="New" />
						<button id="cancelButton" type="button" class="btn btn-danger col-md-2 pull-left clearfix">Cancel</button>
					</div>
					<div id="inputFields">
						    <label for="date">Date:</label>
						    <input id="appointmentDate" type="text" th:field="*{appointmentDate}" class="form-control" />
						    <label for="time">Time:</label>
						    <input id="appointmentTime" type="text" th:field="*{appointmentTime}" class="form-control" />
						    <label for="description">DESC:</label>
						    <input id="description" type="text" th:field="*{description}" class="form-control" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
