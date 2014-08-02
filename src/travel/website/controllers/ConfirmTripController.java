package travel.website.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmTripController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmTripController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		RequestDispatcher rd = null;
		String paymentmethod = (String) request.getParameter("paymentmethod");
		
		if(paymentmethod.equals("Redeem mileage")) {
			rd = request.getRequestDispatcher("RedeemMileageController");
		} else if(paymentmethod.equals("Purchase through Credit card")) {
			rd = request.getRequestDispatcher("/payUsingCreditCard.jsp");
		}
		
		rd.forward(request, response);
	
	}

}

