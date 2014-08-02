<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<c:set var="adults" value="${sessionScope['adults']}" />
<c:set var="children" value="${sessionScope['children']}" />
<c:set value="${adults+children}" var="numberofpassengers"></c:set>
<body>
	<c:set var="flightlist" value="${requestScope['flightselected']}" />
	<h3>Trip Summary</h3>
	<form method="post" action="ConfirmTripController">
		<c:forEach items="${flightlist}" var="flight">
			<table cellpadding="10">
				<tr>
					<td><h4>
							<c:out value="${flight.flight_num}" />
						</h4></td>
				</tr>
				<tr>
					<td>From: <c:out value="${flight.flying_from}" /></td>
					<td>To: <c:out value="${flight.flying_to}" /></td>
				</tr>
				<tr>
					<td>Departure Date:<c:out value="${flight.departure_date}" /></td>
					<td>Departure Time:<c:out value="${flight.departure_time}" /></td>
				</tr>
				<tr>
					<td>Arrival Date: <c:out value="${flight.arrival_date}" /></td>
					<td>Arrival Time: <c:out value="${flight.arrival_time}" /></td>
				</tr>
			</table>
		</c:forEach>
		<table cellpadding="10">
			<tr>
				<td>Number of travelers: <c:out value="${numberofpassengers}" /></td>
				<td>TotalCost(with taxes):<c:out
						value="${requestScope['totalcost']}" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="paymentmethod"
					value="Redeem mileage" /></td>
				<td><input type="submit" name="paymentmethod"
					value="Purchase through Credit card" /></td>
			</tr>
		</table>
	</form>
</body>
</html>