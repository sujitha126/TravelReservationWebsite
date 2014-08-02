package travel.website.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import travel.website.utilities.ConnectionUtil;


public class RedeemMiles {
	
	Connection con;
	private static final int REDEEM_PERCENT= 2;
	int redeemedMiles;
	
	
	public int getRedeemedMiles() {
		return redeemedMiles;
	}


	public void redeem(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		String username=(String) session.getAttribute("userid");
		double totalcost = (Double) session.getAttribute("totalcost");
		redeemedMiles=0;
		int accumulated_mileage = getMiles(username);
		double amountForMiles = calcEquivalentAmount(accumulated_mileage);
		
		if(amountForMiles>totalcost) {
			redeemedMiles = (int) ((totalcost*100)/REDEEM_PERCENT);
		} else {
			redeemedMiles = accumulated_mileage;
		}
		
		
	}

	public double calcEquivalentAmount(int miles) {
		
		double amount = miles*(REDEEM_PERCENT)/100;
		return amount;
	}

	private int getMiles(String username) throws Exception {
		
		String strquery="select accumulated_mileage from user where user_name='"+username+"'";
		con = ConnectionUtil.getConnection();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(strquery);
		
		if(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}


}
