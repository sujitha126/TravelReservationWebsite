<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="travel.website.model.Flight"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
#selectbutton {
	background: url(images/button2.jpeg) no-repeat;
	width: 150px;
	height: 50px;
}
</style>
</head>

<body>
	<form action="test.jsp" method="post">
		<input type="submit" value="1" name="button" id="selectbutton" /> <input
			type="submit" value="2" name="button" class="selectbutton" />
	</form>
</body>
</html>