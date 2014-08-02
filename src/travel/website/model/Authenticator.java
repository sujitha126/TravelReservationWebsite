package travel.website.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import travel.website.utilities.ConnectionUtil;
public class Authenticator {
	
	public String authenticate(String username, String password) throws Exception {
		
		try {
			Connection con = ConnectionUtil.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from user where user_name='"+username+"' and password='"+password+"' ");
			
			if(rs.next()) {
				return "success";
			} else {
				return "failure";
			}
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "failure";
		
    }
}
