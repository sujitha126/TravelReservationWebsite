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

	<form method="post" action="CCPaymentController">
		<div id="passengerForm">
			<p>
				<font color="#ff0000"><c:out
						value="${requestScope['errormessage']}" /></font>
			</p>
			<p>
				<font color="#ff0000"><c:out
						value="${requestScope['message']}" /></font>
			</p>

			<h4>Passenger Information</h4>
			<c:forEach begin="1" end="${numberofpassengers}" var="loop">
				<table border="1">
					<tr>
						<td>
							<table cellpadding="10">
								<tr>
									<td>Passenger <c:out value="${loop}" /></td>
								</tr>
								<tr>
									<td>FirstName <input type="text" name="fname"
										value="<c:out value="${fname}"/>">
									</td>
									<td>LastName <input type="text" name="lname"
										value="<c:out value="${lname}"/>">
									</td>
								</tr>
								<tr>
									<td>Gender <input type="radio" name="gender"
										value="Female"> Female <input type="radio"
										name="gender" value="Male"> Male
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</c:forEach>
		</div>

		<div id="summary">
			<c:set var="flightlist" value="${sessionScope['flightselected']}" />
			<h4>Summary of Charges</h4>
			<table border="1">
				<tr>
					<td>
						<table cellpadding="10">
							<tr>
								<td>Airline Ticket Cost:</td>
							</tr>
								<c:forEach var="flight" items="${flightlist}">
								<tr>
								<td><c:out value="${flight.flight_num}" /> per
									ticket</td>
								<td><c:out value="${flight.base_price}" /> per
									ticket</td>
								</tr>
								</c:forEach>
							<tr>
								<td>Taxes:</td>
								<td>15% of the airline fare</td>
							</tr>
							<tr>
								<td>Number of Tickets:</td>
								<td><c:out value="${numberofpassengers}" /></td>
							</tr>
							<tr>
								<td>Total Trip Cost:</td>
								<td>$<c:out value="${totalcost+redeemedAmount}" /></td>
							</tr>
							<tr>
								<td>Amount for Redeeming miles:</td>
								<td>$<c:out value="${redeemedAmount}" /></td>
							</tr>
							<tr>
								<td>Remaining Amount Cost</td>
								<td>$<c:out value="${totalcost}" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>

		<div id="ccdetails">
			<h4>CreditCard Information</h4>
			<table border="1">
				<tr>
					<td>
						<table cellpadding="10">
							<tr>
								<td>CreditCardType <input type="text" name="cctype"></td>
								<td>CreditCardNumber <input type="text" name="ccnumber"></td>
							</tr>
							<tr>
								<td>Name on card <input type="text" name="nameoncard"></td>
								<td>ExpirationDate <input type="date" name="expdate"></td>
								<td>SecurityCode <input type="text" name="securitycode"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>

		<div id="submitbutton" align="center">
			<input type="submit" value="Buy My Tickets Now" name="buyticketnow">
		</div>
	</form>
</body>
</html>