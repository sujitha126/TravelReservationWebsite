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

import javax.servlet.http.HttpSession;

import travel.website.utilities.ConnectionUtil;

public class FindFlights {

	List<Flight> flightsList = null;
	HttpSession session;

	public List<Flight> listAvailableFlights(String source, String destination,
			String departingstr, int adults, int children) throws Exception {
		
		flightsList=null;
		String strQuery = generateQuery(source,destination,departingstr,adults,children);
		Connection con = ConnectionUtil.getConnection();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(strQuery);
		addResultsToList(rs);
			
		return flightsList;
	}
	
	private String generateQuery(String source, String destination,
			String departingstr, int adults, int children) throws ParseException {
		
		int seatsNeeded = (adults+children);
		
		session.setAttribute("adults",adults);
		session.setAttribute("children", children);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date departingdate = sdf.parse(departingstr);
		java.sql.Date departing = new Date(departingdate.getTime());
		
		
		//Building the query
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from Flight f, FlightSchedule fs where");
		stringBuilder.append(" fs.flight_num=f.flight_num");
		stringBuilder.append(" and f.flying_from='"+source+"'");
		stringBuilder.append(" and f.flying_to='"+destination+"'");
		stringBuilder.append(" and fs.departure_date='"+departing+"'");
		stringBuilder.append(" and (f.total_seats-fs.seats_occupied)>'"+seatsNeeded+"'");
		stringBuilder.append(" order by base_price");
		
		return stringBuilder.toString();
	}
	
	private void addResultsToList(ResultSet rs) throws SQLException {

		while(rs.next()) {
			
			if(flightsList==null) {
				 flightsList = new ArrayList<Flight>();
			}
			Flight flight = new Flight();
			flight.setFlight_id(rs.getInt("flight_id"));
			flight.setFlight_num(rs.getString("flight_num"));
			flight.setAirways_id(rs.getString("airways_id"));
			flight.setFlying_from(rs.getString("flying_from"));
			flight.setFlying_to(rs.getString("flying_to"));
			flight.setDeparture_date(rs.getDate("departure_date"));
			flight.setDeparture_time(rs.getTime("departure_time"));
			flight.setArrival_date(rs.getDate("arrival_date"));
			flight.setArrival_time(rs.getTime("arrival_time"));
			flight.setDuration(rs.getDouble("duration"));
			flight.setBase_price(rs.getDouble("base_price"));
			flight.setStatus(rs.getString("status"));
			flight.setMiles(rs.getInt("miles"));
			flight.setSeats_occupied(rs.getInt("seats_occupied"));
			flight.setTotal_seats(rs.getInt("total_seats"));
			
			flightsList.add(flight);
		}
		
	}


	public Flight getFlight(int flightid) throws Exception {
		
		String strquery = "select * from Flight f, FlightSchedule fs where fs.flight_id='"+flightid+"' and f.flight_num=fs.flight_num";
		Connection con = ConnectionUtil.getConnection();
		Statement statement = con.createStatement();
		try {
			ResultSet rs = statement.executeQuery(strquery);
			Flight flight = addToObject(rs);
			return flight;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Flight addToObject(ResultSet rs) throws SQLException {
		
		if(rs.next()) {
			Flight flight = new Flight();
			flight.setFlight_num(rs.getString("flight_num"));
			flight.setFlight_id(rs.getInt("flight_id"));
			flight.setAirways_id(rs.getString("airways_id"));
			flight.setFlying_from(rs.getString("flying_from"));
			flight.setFlying_to(rs.getString("flying_to"));
			flight.setDeparture_date(rs.getDate("departure_date"));
			flight.setDeparture_time(rs.getTime("departure_time"));
			flight.setArrival_date(rs.getDate("arrival_date"));
			flight.setArrival_time(rs.getTime("arrival_time"));
			flight.setDuration(rs.getDouble("duration"));
			flight.setBase_price(rs.getDouble("base_price"));
			flight.setStatus(rs.getString("status"));
			flight.setMiles(rs.getInt("miles"));
			flight.setSeats_occupied(rs.getInt("seats_occupied"));
			flight.setTotal_seats(rs.getInt("total_seats"));
			
		return flight;
		}
		
		return null;
	}



	public void setSession(HttpSession session) {
		
		this.session=session;
		
	}
	

}
