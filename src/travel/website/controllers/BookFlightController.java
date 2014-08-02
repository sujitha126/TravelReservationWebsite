package travel.website.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class BookFlightController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	public BookFlightController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		RequestDispatcher rd = null;
		String triptype = request.getParameter("triptype");
		session = request.getSession();
		session.setAttribute("triptype", triptype);
		
		if(triptype.equals("roundtrip")) {
			rd = request.getRequestDispatcher("RoundTripController");
			
		} else if(triptype.equals("oneway")) {
			rd = request.getRequestDispatcher("OneWayController");
		} else if(triptype.equals("multiple")) {
			rd = request.getRequestDispatcher("MultiWayController");
		}
		
		rd.forward(request, response);
	
	}

}
