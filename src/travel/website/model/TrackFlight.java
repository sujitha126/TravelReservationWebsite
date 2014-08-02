package travel.website.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import travel.website.utilities.ConnectionUtil;

public class TrackFlight {
	
	List<Flight> flightsList = new ArrayList<Flight>();
	
public List<Flight> flightTracker(HttpServletRequest request) {
		
		try {
			String strQuery = generateQuery(request);
			Connection con = ConnectionUtil.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(strQuery);
			addResultsToList(rs);
			con.close();
			return flightsList;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

private String generateQuery(HttpServletRequest request) throws ParseException {
	
	//Getting the input parameters
	String airways_id = request.getParameter("airways_id");
	String flight_num = request.getParameter("flight_num");
	String departingStr = request.getParameter("departure_date");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	java.util.Date departingdate = sdf.parse(departingStr);
	java.sql.Date departing = new Date(departingdate.getTime());
	
	//Building the query
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("select * from flight_db where");
	stringBuilder.append(" airways_id='"+airways_id+"'");
	stringBuilder.append(" and flight_num='"+flight_num+"'");
	stringBuilder.append(" and departure_date='"+departing+"'");
	
	return stringBuilder.toString();

}

private void addResultsToList(ResultSet rs) throws SQLException {

	while(rs.next()) {
		
		Flight flight = new Flight();
		flight.setFlight_num(rs.getString("flight_num"));
		flight.setAirways_id(rs.getString("airways_id"));
		flight.setFlying_from(rs.getString("flying_from"));
		flight.setFlying_to(rs.getString("flying_to"));
		flight.setDeparture_date(rs.getDate("departure_date"));
		flight.setDeparture_time(rs.getTime("departure_time"));
		flight.setArrival_date(rs.getDate("arrival_date"));
		flight.setArrival_time(rs.getTime("arrival_time"));
		flight.setDuration(rs.getDouble("duration"));
		flight.setStatus(rs.getString("status"));
		
		flightsList.add(flight);
	}
	
}

}
