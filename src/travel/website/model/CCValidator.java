package travel.website.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;







import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import travel.website.utilities.ConnectionUtil;

public class CCValidator {
	
	public String validate(HttpServletRequest request) throws SQLException {
		
		HttpSession session = request.getSession();
		
		String strquery;
		String result= "invalid";
		Connection con = null;
		Statement statement =null;
		
		try {
			 con = ConnectionUtil.getConnection();
			 statement = con.createStatement();
			strquery = generateQuery(request);
			ResultSet rs = statement.executeQuery(strquery);
			if(rs.next()) {
				result="valid";
				addReservationAndMiles(con,session);
			} else {
				result="invalid";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			statement.close();
			con.close();
		}
			
		return result;
	}

	

	public void addReservationAndMiles(Connection con, HttpSession session) throws Exception {
		
		String useridstr = (String) session.getAttribute("userid");
		int userid = getUserId(con,useridstr);	
		
		@SuppressWarnings("unchecked")
		List<Flight> flightlist = (List<Flight>) session.getAttribute("flightselected");
		double totalcost = (Double) session.getAttribute("totalcost");
		int miles=0;
		
	
		for(Flight flight:flightlist) {
		
		miles+= flight.getMiles();
		int flightid = flight.getFlight_id();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("insert into Reservation (user_id, confirmation_date,flight_id,total_cost) values(");
		stringBuilder.append("'"+userid+"',now(),'"+flightid+"','"+totalcost+"' )");
		String strquery = stringBuilder.toString();
		ConnectionUtil.executeUpdate(strquery);
		
		}
		
		if(session.getAttribute("redeemed")!=null && !(session.getAttribute("redeemed").equals(""))) {
			int redeemed = (Integer) session.getAttribute("redeemed");
			redeemMiles(redeemed,userid);
		}
		
		StringBuilder stringBuilder2 = new StringBuilder();
		stringBuilder2.append("update user set accumulated_mileage=accumulated_mileage+'"+miles+"' where user_id='"+userid+"' ");
		ConnectionUtil.executeUpdate(stringBuilder2.toString());
		
	}

	private void redeemMiles(int redeemed, int userid) {
		
		
		String strquery = "update user set accumulated_mileage=accumulated_mileage-'"+redeemed+"' where user_id='"+userid+"'";
		try {
			ConnectionUtil.executeUpdate(strquery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private int getUserId(Connection con, String useridstr) throws SQLException {

		String strquery="select user_id from user where user_name='"+useridstr+"'";
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(strquery);
		
		if(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}



	private String generateQuery(HttpServletRequest request) throws ParseException {
		
		//Getting the input parameters
		String ccnumber =request.getParameter("ccnumber");
		String nameoncard =request.getParameter("nameoncard");
		String expdatestr = request.getParameter("expdate");
		int cvv = Integer.parseInt(request.getParameter("securitycode"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse(expdatestr);
		java.sql.Date expdate = new Date(date.getTime());
		
	
		//Building the query
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from cc_details where");
		stringBuilder.append(" cc_num='"+ccnumber+"'");
		stringBuilder.append(" and expiry_date='"+expdate+"'");
		stringBuilder.append(" and name_on_card='"+nameoncard+"'");
		stringBuilder.append(" and cvv='"+cvv+"'");
		
		return stringBuilder.toString();
		
	}
}
