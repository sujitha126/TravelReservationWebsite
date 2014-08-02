<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.selectbutton {
	background: url(images/button2.jpeg) no-repeat;
	width: 150px;
	height: 50px;
}
</style>
</head>
<body>
	<form method="post" action="TripSummaryController">
		<table cellpadding="10">
			<c:forEach var="flighttype"
				items="${requestScope['flightsAvailable']}">
				<tr>
					<td>
						<table border="1">
							<tr>
								<td>
									<table cellpadding="10">
										<tr>
											<td colspan="4" align="center"><c:out
													value="${flighttype.key}" /></td>
										</tr>
										<c:forEach items="${flighttype.value}" var="flight">
											<tr>
												<td>
													<table cellpadding="10">
														<tr>
															<td rowspan="4"><input type="radio"
																value="${flight.flight_id}" name="optionselected"></td>
															<c:set var="flightselected" value="${flight}"
																scope="session" />
															<td>Flight Number: <c:out
																	value="${flight.flight_num}" /></td>
															<td><c:out value="${flight.airways_id}" /></td>

														</tr>
														<tr>
															<td>FROM: <c:out value="${flight.flying_from}" /></td>
															<td>TO: <c:out value="${flight.flying_to}" /></td>
														</tr>
														<tr>
															<td>DepartingTime: <c:out
																	value="${flight.departure_time}" /></td>
															<td>ArrivalTime: <c:out
																	value="${flight.arrival_time}" /></td>
															<td>Price per person: <c:out
																	value="${flight.base_price}" /></td>
														</tr>
													</table>
										</c:forEach>

									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td><input type="submit" name="submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>