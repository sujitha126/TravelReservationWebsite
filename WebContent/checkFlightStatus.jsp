<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check flight Status</title>
</head>
<body>
	<h2 align="center">Check Flight Status</h2>
	<form method="post" action="TrackFlightController">
		<br> Airline: <select name="airways_id">
			<option>Delta
			<option>BritishAirways
			<option>United
		</select> Flight#: <input type="text" name="flight_num"> Departure
		Date: <input type="date" name="departure_date"> <input
			type="submit" name="status" value="track">

	</form>
</body>
</html>