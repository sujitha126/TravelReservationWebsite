<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<c:set var="adults" value="${sessionScope['adults']}" />
	<c:set var="children" value="${sessionScope['children']}" />
	<c:set value="${adults+children}" var="numberofpassengers"></c:set>

			<h5><c:out value="${requestScope['message']}" /></h5>
			
		<div id="Itinerary">
			<c:set var="flightlist" value="${sessionScope['flightselected']}" />
			<h4>Ticked booked.</h4>
			<table border="1">
				<tr>
					<td>
						<table cellpadding="10">
							<tr>
								<td colspan="2">Itinerary</td>
							</tr>
								<c:forEach var="flight" items="${flightlist}">
								<tr>
								<td><b><c:out value="${flight.flight_num}" /></b></td>
								</tr>
								<tr>
								<td>Departure: </td>
								<td><c:out value="${flight.departure_date}" />
								    <c:out value="${flight.departure_time}" />
								</td>
								</tr>
								<tr>
								<td>Arrival: </td>
								<td><c:out value="${flight.arrival_date}" />
								    <c:out value="${flight.arrival_time}" />
								</td>
								</tr>
								</c:forEach>
						</table>
					</td>
				</tr>
			</table>
		</div>
</body>
</html>