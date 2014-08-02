<html>
<head>
<title>Book a flight</title>

<script type="text/javascript">

function displaySelect(objID) {
window.onload = displaySelect;
    switch(objID)
	{
		case 'roundtrip':		
			document.getElementById('returning').style.display = 'block';
			document.getElementById('additionalflights').style.display = 'none';
			document.getElementById('moreflights').style.display = 'none';
			document.getElementById('addflights').style.display = 'none';
		break;
		case 'oneway':
			document.getElementById('returning').style.display = 'none';	
			document.getElementById('additionalflights').style.display = 'none';
			document.getElementById('moreflights').style.display = 'none';
			document.getElementById('addflights').style.display = 'none';
		break;		
		case 'multiple':
			document.getElementById('additionalflights').style.display = 'block';
			document.getElementById('returning').style.display = 'none';
			document.getElementById('moreflights').style.display = 'none';
			document.getElementById('addflights').style.display = 'block';
		break;
		case 'addflights':
			document.getElementById('moreflights').style.display = 'block';
			document.getElementById('additionalflights').style.display = 'block';
			document.getElementById('returning').style.display = 'none';
			document.getElementById('addflights').style.display = 'none';
		break;
		default:
			document.getElementById('returning').style.display = 'none';	
			document.getElementById('additionalflights').style.display = 'none';
			document.getElementById('moreflights').style.display = 'none';
			document.getElementById('addflights').style.display = 'none';
	}
}
</script>

</head>

<body>

	<form method="post" action="BookFlightController">

		<table>

			<tr>
				<td><input id="roundtrip" type="radio" name="triptype"
					value="roundtrip" onclick='displaySelect(this.id);' />RoundTrip <input
					id="oneway" type="radio" name="triptype" value="oneway"
					checked="checked" onclick='displaySelect(this.id);' />OneWay <input
					id="multiple" type="radio" name="triptype" value="multiple"
					onclick='displaySelect(this.id);' />MultiDestination
				<td>
			</tr>
			<tr>
				<td><b>Source </b> <input type="text" name="source"></td>
				<td><b>Destination</b> <input type="text" name="destination">
				</td>
			</tr>
			<tr>
				<td><b>Departing </b><input type="date" name="departing"></td>
				<td id="returning" style='display: none;'><b>Returning </b><input
					type="date" name="returning"></td>
			</tr>
			<tr>
				<td><b>Adults</b> <select name="adults">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
				</select></td>

				<td><b>Children</b><select name="children">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
				</select></td>
			</tr>
		</table>
		<table id='additionalflights' style='display: none;'>
			<tr>
				<td><b>Flight2</b></td>
			</tr>
			<tr>
				<td><b>Source </b> <input type="text" name="sourceFlight2">
				</td>
				<td><b>Destination</b> <input type="text"
					name="destinationFlight2"></td>
				<td><b>Departing </b> <input type="date" name="departingDate2">
				</td>
			</tr>
			<tr>
				<td><b>Flight3</b></td>
			</tr>
			<tr>
				<td><b>Source </b> <input type="text" name="sourceFlight3">
				</td>
				<td><b>Destination</b> <input type="text"
					name="destinationFlight3"></td>
				<td><b>Departing </b> <input type="date" name="departingDate3">
				</td>
			</tr>
			<tr>
				<td><b>Flight4</b></td>
			</tr>
			<tr>
				<td><b>Source </b> <input type="text" name="sourceFlight4">
				</td>
				<td><b>Destination</b> <input type="text"
					name="destinationFlight4"></td>
				<td><b>Departing </b> <input type="date"
					name="destinationDate4"></td>
			</tr>
			<tr>
				<td><input id="addflights" type="button" name="addflights"
					value="Add Flight" onclick='displaySelect(this.id);' /></td>
			</tr>
		</table>
		<table id='moreflights' style='display: none;'>
			<tr>
				<td><b>Flight5</b></td>
			</tr>
			<tr>
				<td><b>Source </b> <input type="text" name="sourceFlight5">
				</td>
				<td><b>Destination</b> <input type="text"
					name="destinationFlight5"></td>
				<td><b>Departing </b> <input type="date"
					name="destinationDate5"></td>
			</tr>
			<tr>
				<td><b>Flight6</b></td>
			</tr>
			<tr>
				<td><b>Source </b> <input type="text" name="sourceFlight6">
				</td>
				<td><b>Destination</b> <input type="text"
					name="destinationFlight6"></td>
				<td><b>Departing </b> <input type="date" name="destinationDate">
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td align="center"><input type="submit" value="Book Flight">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
