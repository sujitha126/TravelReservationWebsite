package travel.website.controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import travel.website.model.CCValidator;
import travel.website.model.RedeemMiles;
import travel.website.utilities.ConnectionUtil;



public class RedeemMileageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public RedeemMileageController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		
		RedeemMiles redeemMiles = new RedeemMiles();
		try {
			redeemMiles.redeem(request);
			int miles = redeemMiles.getRedeemedMiles();
			double amountForMiles = redeemMiles.calcEquivalentAmount(miles);
			
			double totalcost = (Double) session.getAttribute("totalcost")-amountForMiles;
			session.setAttribute("redeemed",miles );
			session.setAttribute("redeemedAmount", amountForMiles);
			
			String message="You have redeemed "+miles+" miles for $"+amountForMiles+"";
			
			if(totalcost>1) {
			session.setAttribute("totalcost",totalcost );
			rd = request.getRequestDispatcher("/payUsingCreditCard.jsp");
			request.setAttribute("message", message);
			} else {
				rd = request.getRequestDispatcher("/FlightConfirmation.jsp");
				request.setAttribute(message, "You have redeemed"+miles+" miles");
				Connection con = ConnectionUtil.getConnection();
				CCValidator ccValidator = new CCValidator();
				ccValidator.addReservationAndMiles(con, session);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	
	}



}
